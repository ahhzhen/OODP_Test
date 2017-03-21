 import java.util.ArrayList;
  
  public class CourseIndex {
  	private int vacancy;
  	private int index;
  	private String courseCode;
  	public int getVacancy() {
		return vacancy;
	}

	private ArrayList<timeSlot> lessons = new ArrayList<timeSlot>();
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
  	public int getIndex()
  	{
  		return index;
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
  	
  	/*public void setTimeSlot(int d, String t, String ltype) {
  		if (ltype == "LEC" || ltype == "TUT" || ltype == "LAB")
  		{
  			lessons.add(new timeSlot(d, t, ltype));
  		}
  		else
  		{
  			System.out.println("Sum Ting Wong");
  		}
  	}
  	
  	public void sendToTimetable()
  	{
  		//send the piece of shit there
  		for(int i=0; i<lessons.size();i++)
  		{
  			//Timetable call here
  			lessons.get(i).print();
  		}
  	}*/
  }