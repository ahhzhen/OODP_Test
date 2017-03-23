import java.util.*;

public class Course {
	private String name;
	private String courseCode;
	private String school;
	private ArrayList<CourseIndex> cIndexList;
	private ArrayList<Course> cCourseList;

	public Course() {
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

	public void updateIndex(int oldIndex, int newIndex, String courseCode) {//can be 2 args
		CourseIndex ci = new CourseIndex();
		ci.updateIndex(oldIndex, newIndex, courseCode);
	}

	public ArrayList<CourseIndex> getcIndexList() {
		return cIndexList;
	}

	public void AddtocIndexList(int index, int vacancy, String gname) {//, Lesson tut, Lesson lab) {
		this.cIndexList.add(new CourseIndex(index, vacancy, gname));
	}
}
