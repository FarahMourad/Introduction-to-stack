package eg.edu.alexu.csd.datastructure.stack.cs54;
import java.util.Scanner;
public class UIApp
{
	public static void main(String[] args)
	{
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		ExpressionEvaluator exeval = new ExpressionEvaluator();
		char choice;
		boolean flag = true;
		String infix="";
		String postfix = "";
		while(flag)
		{
			System.out.println("choose an action\n"+"1-Enter an Expression \n"+"2-Convert to postfix\n"+"3-Evaluate \n"+"4-Exit");
			choice = scan.next().charAt(0);
			switch(choice)
			{
			case '1':
				System.out.println("write an expression\n");
				scan.nextLine();
				infix = scan.nextLine();
				break;
			case '2':
				postfix = exeval.infixToPostfix(infix);
				System.out.println("postfix expression is =" + postfix);
				break;
			case '3':
				boolean findletter = false;
				int result;
				try
				{
					for(int i=0;i<infix.length(); i++)
					{
						if(exeval.checkletters(infix.charAt(i)))
						{
							findletter= true;
							break;
						}
					}
					if(findletter)
					{
						System.out.println("Enter digits in expression");
					}
					else
					{
						result = exeval.evaluate(postfix);
						System.out.println("the result is" + result);
					}
					break;
				}
				catch(Exception e )
				{
					System.out.println("Enter an expression" );
					break;
				}
			case '4':
				flag = false;
				break;
			default:
				System.out.println("Invalid choice");
				
				
			}
		}
	}
}
