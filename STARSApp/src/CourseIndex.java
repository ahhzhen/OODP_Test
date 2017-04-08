import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CourseIndex implements Serializable {
	private int index;
	private int vacancy;
	private String groupName;
	private TimeSlot tutorial;
	private TimeSlot lab;
	private ArrayList<String> waitList;
	
	CourseIndex() {}
	
	CourseIndex(int index, int vacancy, String gname) {
		this.index = index;
		this.vacancy = vacancy;
		this.groupName = gname;
	}
	
	CourseIndex(int index, int vacancy, String gname, TimeSlot tut, TimeSlot lab) {//, Lesson tut, Lesson lab) {
		this.index = index;
		this.vacancy = vacancy;
		this.groupName = gname;
		this.tutorial = tut;
		this.lab = lab;
		this.waitList = new ArrayList<String>();
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getVacancy() {
		return vacancy;
	}

	public void setVacancy(int vacancy) {
		this.vacancy = vacancy;
	}

	public void decrementVacancy() {
		if (vacancy > 0)
			vacancy--;
	}
	
	public void incrementVacancy() {
		if(waitList.size() > 0)
		{
			//grab first entry and notify
			waitList.remove(0);
		}
		else
			vacancy++;
		
	}
	
	public TimeSlot getLab()
	{
		return lab;
	}
	
	public TimeSlot getTutorial()
	{
		return tutorial;
	}

	public boolean indexExist(int index) {
		return (index == this.index);
	}
	
	public boolean checkClash(List<TimeSlot> timeSlotList)
	{
		return (lab.hasClash(timeSlotList) || tutorial.hasClash(timeSlotList));
	}
}
