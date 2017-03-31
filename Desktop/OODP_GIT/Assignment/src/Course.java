import java.util.*;

public class Course {
	private String name;
	private String courseCode;
	private String school;
	private int indexPointer;
	private ArrayList<CourseIndex> cIndexList;
	//private ArrayList<Course> cCourseList;

	public Course() {
		name = "";
		courseCode = "";
		school = "";
	}

	public Course(String n, String cc, String s) {
		name = n;
		courseCode = cc;
		school = s;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}
	
	public ArrayList<Course> getcCourseList() {
		return cCourseList;
	}

	public boolean courseExist(String courseCode) {
		/*if (cCourseList != null) {
			for (int i = 0; i < cCourseList.size(); i++) {
				Course course = cCourseList.get(i);
				if (course.getCourseCode() == courseCode)
					return true;
			}
		}*/
		return (courseCode == this.courseCode);		
	}

	public void updateIndex(int oldIndex, int newIndex){//, String courseCode) {//can be 2 args
		if(selectIndex(oldIndex))
		{
			cIndexList.get(indexPointer).setIndex(newIndex);
			System.out.println("Index updated.");
		}
		else
			System.out.println("Update failed.");
	}

	public void updateVacancy(int index, int vacancy)
	{
		if(selectIndex(index))
		{
			cIndexList.get(indexPointer).setVacancy(vacancy);
			System.out.println("Vacancy updated.");
		}
		else
			System.out.println("Update failed");
	}
	
	public ArrayList<CourseIndex> getcIndexList() {
		return cIndexList;
	}

	public void AddtocIndexList(int index, int vacancy, String gname) {//, Lesson tut, Lesson lab) {
		this.cIndexList.add(new CourseIndex(index, vacancy, gname));
	}
	
	public boolean selectIndex(int index)
	{
		for (int i = 0; i<cIndexList.size(); i++)
		{
			if(cIndexList.get(i).indexExist(index))
			{
				indexPointer = i;
				return true;
			}
			else
			{
				//System.out.println("Index not found.");
				return false;
			}
		}
	}
}
