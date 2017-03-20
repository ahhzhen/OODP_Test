
public class IndexNo {
	private int vacancy;
	
	public void Increase()
	{
		vacancy++;
	}
	public void Decrease()
	{
		if(vacancy!=0)
		{
			vacancy--;
		}
		else
		{
			System.out.println("Index is full");
		}
	}
	

}
