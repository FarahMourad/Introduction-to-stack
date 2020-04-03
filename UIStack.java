package eg.edu.alexu.csd.datastructure.stack.cs54;
import java.util.Scanner;
public class UIStack 
{
	public static void main(String[] args)
	{
		try(Scanner scan = new Scanner(System.in))
		{
			Stack stack = new Stack();
			char choice; 
			boolean flag = true;
			while(flag)
			{
				System.out.println("choose an action\n"+"1-push \n"+"2-pop\n"+"3-peek \n"+"4-isEmpty\n"+"5-get size\n"+"6-Exit");
				choice = scan.next().charAt(0);
				switch (choice)
				{
				case '1':
					System.out.println("insert an element");
					String S;
					scan.nextLine();
					S = scan.nextLine();
					stack.push(S);
					break;
				case '2':
					if(stack.isEmpty())
					{
						System.out.println("Empty stack");
					}
					else
					{
						System.out.println(stack.pop());
					}
					break;
				case '3':
					if(stack.isEmpty())
					{
						System.out.println("Empty stack");
					}
					else
					{
						System.out.println(stack.peek());
					}
					break;
				case '4':
					if(stack.isEmpty())
					{
						System.out.println("yes, Empty stack");
					}
					else
					{
						System.out.println("The stack isn't empty");
					}
					break;
				case '5':
					int size = stack.size();
					System.out.println("The size of the stack is " + size );
					break;
				case '6':
					flag = false;
					break;
				default:
					System.out.println("Invalid choice");	
				}
			}
		}
	}
}
