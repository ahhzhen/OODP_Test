import java.util.*;

public class Course {
	// test???
	private String name;
	private String courseCode;
	private String school;
	private int cindex;
	private ArrayList<CourseIndex> cIndexList;

	public Course(String courseCode) {
		this.courseCode = courseCode;
	}

	public Course() {

	}

	public String getCourseCode() {
		return courseCode;
	}
	public String getSchool()
	{
		return school;
		
	}

	public int getCourseIndexes(int courseindex)
	{
		cindex=courseindex;
		return cindex;
	}
	public void UpdateIndex(int cindex, int nindex)
	{
		//check arraylist for matching index'
		//switch with new index
	}
	public void UpdateVacancy(int cindex, int nindex)
	{
		
	}
	
}