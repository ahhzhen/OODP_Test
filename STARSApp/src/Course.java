import java.util.*;

public class Course {
	private String name;
	private String courseCode;
	private String school;
	private int indexPointer;
	private ArrayList<CourseIndex> cIndexList;
	// private static ArrayList<Course> courseList; //StudentCourse will hold
	// the courseList

	public Course() {
	}

	public Course(String courseCode) {
		this.courseCode = courseCode;
	}

	public Course(String name, String courseCode, String school) {
		this.name = name;
		this.courseCode = courseCode;
		this.school = school;
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

	// public static ArrayList<Course> getCourseList() {
	// return courseList; //see above
	// }

	public void updateIndex(int oldIndex, int newIndex) {// , String courseCode)
															// {//can be 2 args
		if (selectIndex(oldIndex)) {
			cIndexList.get(indexPointer).setIndex(newIndex);
			System.out.println("Index updated.");
		} else
			System.out.println("Update failed.");
	}

	public void updateVacancy(int index, int vacancy) {
		if (selectIndex(index)) {
			cIndexList.get(indexPointer).setVacancy(vacancy);
			System.out.println("Vacancy updated.");
		} else
			System.out.println("Update failed");
	}

	public ArrayList<CourseIndex> getcIndexList() {
		return cIndexList;
	}

	public void minusFromIndex(int index) {
		if (selectIndex(index)) {
			cIndexList.get(indexPointer).decrementVacancy();
			System.out.println("Vacancy decremented.");
		} else
			System.out.println("Decrement failed.");
	}

	public void addToIndex(int index) {
		if (selectIndex(index)) {
			cIndexList.get(indexPointer).incrementVacancy();
			System.out.println("Vacancy incremented.");
		} else
			System.out.println("Increment failed.");
	}

	public int retrieveVacancy(int index) {
		if (selectIndex(index))
			return cIndexList.get(indexPointer).getVacancy();
		else {
			System.out.println("Error when retrieving value.");
			return -1;
		}
	}

	public void AddtocIndexList(int index, int vacancy, String gname) {
		this.cIndexList.add(new CourseIndex(index, vacancy, gname));
	}

	public boolean selectIndex(int index) {
		for (int i = 0; i < cIndexList.size(); i++) {
			if (cIndexList.get(i).indexExist(index)) {
				indexPointer = i;
				return true;
			}
		}
		System.out.println("Index not found.");
		return false;
	}

	public static List getCoursedList() {
		return getCoursedList("courselist.dat");
	}

	public static List getCoursedList(String filename) {
		List list = null;
		try {
			list = FileIO.readInFile(filename);
		} catch (Exception e) {
		}
		if (list == null)
			list = new ArrayList();
		return list;
	}

	public static void save(List list) {
		FileIO.writeToFile("courselist.dat", list);
	}

	public static boolean courseExist(String courseCode) {
		List list = getCoursedList();
		// List<Course> courseList = new ArrayList<Course>();

		boolean breakout = false;
		try {
			if (list.size() != 0) {
				for (int i = 0; i < list.size(); i++) {
					Course course = (Course) list.get(i);
					if (course.getCourseCode().toString() == courseCode) {
						breakout = true;
						break;
					}
				}
			}
		} catch (Exception e) {
		}
		return breakout;
	}

	public void addCourse(String coursename, String coursecode, String school) {
		List list = getCoursedList();
		try {
			Course course = new Course(coursename, courseCode, school);
			list.add(course);
			save(list);
			System.out.println("Course added successfully!");
		} catch (Exception e) {
			System.out.println("Error occured. Course is not added.");
		}
	}

	public void editCourseName(String coursename, String courseCode) {
		List list = getCoursedList();
		// List<Course> courseList = new ArrayList<Course>();
		try {
			if (list.size() != 0) {
				for (int i = 0; i < list.size(); i++) {
					Course course = (Course) list.get(i);
					if (course.getCourseCode().toString() == courseCode) {
						course.setName(coursename);
						break;
					}
				}
			}
		} catch (Exception e) {
		}
		save(list);

	}

	public void editSchool(String courseCode, String school) {
		List list = getCoursedList();
		// List<Course> courseList = new ArrayList<Course>();
		try {
			if (list.size() != 0) {
				for (int i = 0; i < list.size(); i++) {
					Course course = (Course) list.get(i);
					if (course.getCourseCode().toString() == courseCode) {
						course.setSchool(school);
						break;
					}
				}
			}
		} catch (Exception e) {
		}
		save(list);
	}

	public static Course getCourse(String coursecode) {
		Course c = null;
		List courselist = getCoursedList();
		for (int i = 0; i < courselist.size(); i++) {
			Course course = (Course) courselist.get(i);
			if (course.getCourseCode().equals(coursecode))
				return course;
		}
		return c;
	}

	public boolean indexExist(int index) {
		for(int i=0; i<cIndexList.size(); i++)
		{
			CourseIndex cIndex=(CourseIndex)cIndexList.get(i);
			if(cIndex.getIndex()==(index))
			{
				return true;
			}
		}
		return false;

	}
}