 import java.util.ArrayList;
  
  public class CourseIndex {
  	private int vacancy;
  	private int index;
  	private String courseCode;
  	public int getVacancy() {
		return vacancy;
	}

	//private ArrayList<timeSlot> lessons = new ArrayList<timeSlot>();
  	public CourseIndex(int in, int vac)
  	{
  		vacancy = vac;
  		index = in;
  	}
  	public CourseIndex(String cCode, int in, int vac)
  	{
  		courseCode=cCode;
  		vacancy=vac;
  		index=in;
  	}
  	public CourseIndex() {
		// TODO Auto-generated constructor stub
	}
	public int getIndex()
  	{
  		return index;
  	}
  	
  	public void setIndex(int index) {
		this.index = index;
	}
	public void UpdateIndex(int index)
  	{
  		
  	}
  	public void UpdateVacancy(int vacancy)
  	{
  		
  	}
  	public void Increase() {
  		vacancy++;
  	}
  
  	public void Decrease() {
  		if (vacancy != 0) {
  			vacancy--;
  		} else {
  			System.out.println("Index is full");
  		}
  	}
  }