package eg.edu.alexu.csd.datastructure.stack.cs54;
public class ExpressionEvaluator implements IExpressionEvaluator {
	public String infixToPostfix(String expression)
	{
		StringBuffer str = new StringBuffer(expression); //take the expression
		for (int i=0;i<str.length();i++) //loop over the expression
		{
			//check negative number
			if (str.charAt(i)=='-') 
			{
				int j=i-1;
				while (j>=0&&str.charAt(j)==' ') 
					{
					j--;
					}
				if ((j>=0&&!Character.isDigit(str.charAt(j))&&!checkletters(str.charAt(j))&&str.charAt(j)!=')')||i==0) //absolutly -ve
				{
					str.insert(i,"(0");
					i+=3;
					while (str.charAt(i)==' ')  
					{
						i++;
					}
					while (i<str.length()&&(Character.isDigit(str.charAt(i))||checkletters(str.charAt(i))))i++;
					str.insert(i,')');
					i++;
				}
			}
			if (i<str.length()&&(str.charAt(i)==')'||checkletters(str.charAt(i)))) //if we have () or ab or b( or )a we put the astric
			{
				int j=i+1;
				while (j<str.length()&&str.charAt(j)==' ')j++; //jump
				if (j<str.length()&&(str.charAt(j)=='('||checkletters(str.charAt(j)))) 
				{
					str.insert(j,'*');
				}
			}
		}
		String S = "";
		Stack sta = new Stack(); //stack for operators
		Stack parenthesis = new Stack(); //stack for parenthesis
		for (int i=0;i<str.length();i++) 
		{
			if (Character.isDigit(str.charAt(i))||checkletters(str.charAt(i))|| str.charAt(i)==' ' || str.charAt(i)=='.')
			{
				S+=str.charAt(i);
			}
			else if (str.charAt(i)=='(')
			{
				parenthesis.push('(');
				int temp=++i;
				while (i<str.length()&&!parenthesis.isEmpty()) // check how many parenthesis
				{
					if (str.charAt(i)=='(') 
						parenthesis.push('(');
					else if (str.charAt(i)==')') 
						parenthesis.pop();
					i++;
				} //break only if all the parenthesis are closed
				if (parenthesis.isEmpty())
					S += infixToPostfix(str.substring(temp, --i)); // inside the parenthesis repeat 
				else
					throw new RuntimeException ("close the parenthesis");
			}
			else if (checkop(str.charAt(i)))
			{
				S += ' ';
				while (!sta.isEmpty()&&!precedence(str.charAt(i),(char)sta.peek())) //pop the operator with high precedence
				{
					S += sta.pop();
					S += ' ';
				}
				sta.push(str.charAt(i)); //push the new operator
			}
			else
				throw new RuntimeException("the input have invalid parameters");
		}
		while (!sta.isEmpty()) //pop the final operators
		{
			S+=" " +sta.pop();
		}
		return S;
	}
	public int evaluate(String expression)
	{
		Stack nums = new Stack();
		float num;
		for(int i=0;i<expression.length();i++)
		{
			if(Character.isDigit(expression.charAt(i)))
			{
				num = expression.charAt(i)-'0'; //make the character a number
				while(Character.isDigit(expression.charAt(i))&&Character.isDigit(expression.charAt(i+1))) //2 digit number or more
				{
					num*=10;
					i++;
					num=num+expression.charAt(i)-'0'; //make it decimal 
				}
				if(expression.charAt(i+1)=='.'|| expression.charAt(i+1)==',')
				{
					i=i+2;
					int temp = 10;
					while(Character.isDigit(expression.charAt(i))) //check floating nums
					{
						num+=(float)(expression.charAt(i)-'0')/temp; //convert to decimal
						i++;
						temp*=10;
					}
				}
				nums.push(num);
			}
			else
			{
				if(checkop(expression.charAt(i)))
				{
					float num2 = (float)nums.pop();
					float num1 = (float)nums.pop();
					nums.push(res(num1,num2,expression.charAt(i)));
				}
				else if(expression.charAt(i)!=' ') //if the user entered space as an operator
				{
					throw new RuntimeException("invalid parameters");
				}
				if(nums.size()!=1) 
				{
					throw new RuntimeException("invalid");
				}
			}
		}
		return (int)((float)nums.pop());
	}
	private boolean precedence(char first , char last)
	{
		return ((first=='*'||first=='/')&&(last=='+'||last =='-'));
	}
	private boolean checkop(char s)
	{
		return (s=='+'||s=='-'||s=='*'||s=='/');
	}
	private float res(float num1,float num2,char s) 
	{
		switch(s)
		{
		case '+':
			return num1+num2;
		case '-':
			return num1-num2;
		case '*':
			return num1*num2;
		case '/':
			if(num2==0)
			{
				throw new RuntimeException("not allowed");
			}
			return num1/num2;
		default:
			return 0;
		}
	}
	public boolean checkletters(char s)
	{
		return (s >= 'A' && s <= 'z');
	}
}
