import java.util.*;

public class Course {
	// test???
	private String name;
	private String courseCode;
	private String school;
	private ArrayList<CourseIndex> cIndexList;

	public Course(String courseCode) {
		this.courseCode = courseCode;
	}

	public Course() {

	}

	public String getCourseCode() {
		return courseCode;
	}

}