package eg.edu.alexu.csd.datastructure.stack.cs54;
public class Stack implements IStack {
	private class Node
	{
		Object element;
		Node next;
		Node(Object element) //constructor 
		{
			this.element = element;
		}
	}
	private Node top; //automatic allocation
	int size;
	public Stack()//constructor for this class
	{
		//initial values
		this.top=null;
		this.size=0;   
	}
	public Object pop()
	{
		Object temp = null;
		if(isEmpty())
		{
			throw new RuntimeException("the stack is empty");
		}
		else
		{
			temp = this.top.element; //the last value in the stack
			this.top = this.top.next; //same as top--
			this.size--;
		}
		return temp;
	}
	public Object peek()
	{
		Object temp = this.top.element;	
		return temp;
	}
	public void push(Object element)
	{
		//dynamic allocation
		Node Node1 = new Node(element); //make the element node
		Node1.next = this.top; // the next will be the new top
		this.top = Node1;//the current top is the node we created
		this.size++;
	}
	public boolean isEmpty() 
	{
		return this.size==0;
	}
	public int size()
	{
		return this.size;
	}
}
