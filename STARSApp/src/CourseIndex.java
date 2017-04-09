import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CourseIndex implements Serializable {
	private static final long serialVersionUID = 1L;
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
	
	public String getGroupName()
	{
		return groupName;
	}
	
	public void setGroupName(String gName)
	{
		this.groupName = gName;
	}

	public void decrementVacancy() {
		if (vacancy > 0)
			vacancy--;
	}
	
	public void incrementVacancy() {
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
		boolean l = false,t = false;
		if(lab != null)
			l = lab.hasClash(timeSlotList);
		if(tutorial != null)
			t = tutorial.hasClash(timeSlotList);
		return (l || t);
	}

	public void addWaitList(String matricNo) {
		waitList.add(matricNo);
	}
	
	public int getWaitListSize()
	{
		return waitList.size();
	}
	
	public String getWaitListStudent()
	{
		String stud = waitList.get(0);
		waitList.remove(0);
		return stud;
	}
}
