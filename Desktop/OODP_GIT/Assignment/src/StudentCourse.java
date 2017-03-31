import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StudentCourse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String matricNumber;
	private String courseCode;
	private int courseIndex;

	public StudentCourse(String matricNumber, String courseCode,
			int courseIndex) {
		super();
		this.matricNumber = matricNumber;
		this.courseCode = courseCode;
		this.courseIndex = courseIndex;
	}

	public String getMatricNumber() {
		return matricNumber;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public int getCourseIndex() {
		return courseIndex;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public void setCourseIndex(int courseIndex) {
		this.courseIndex = courseIndex;
	}

	public static List getRegisteredList() {
		return getRegisteredList("studentRecords.dat");
	}

	public static List getRegisteredList(String fileName) {
		List list = null;
		try {
			list = FileIO.readInFile(fileName);
		} catch (Exception e) {
		}
		if (list == null)
			list = new ArrayList();
		return list;
	}

	public static List getCoursesRegistered(String matric) {
		List list = getRegisteredList();
		List<StudentCourse> courseList = new ArrayList<StudentCourse>();
		try {
			for (int i = 0; i < list.size(); i++) {
				StudentCourse studC = (StudentCourse) list.get(i);
				if (studC.getMatricNumber().equals(matric)) {
					courseList.add(studC);
					saveStudent(courseList);
				}
			}
		} catch (Exception e) {
		}
		return courseList;
	}

	// add a new entry of student record
	public static boolean registerStudent(String matricNumber,String courseCode, int courseIndex) {
		List<StudentCourse> list = getRegisteredList();
		List<Course> courseList = Course.getCourseList();
		List<CourseIndex>courseIndexList = Course.getcIndexList();
		try {

			// *******Check that such a courseCode exist & it has vacancy******
			
//			for (int i = 0; i < courseList.size(); i++) 
//			{
//				Course course = (Course) courseList.get(i);
//				CourseIndex cIndex = (CourseIndex)courseIndexList.get(i);
//				if (course.getCourseCode().equals(courseCode))  //check if courseCode exist
//				{
//					
//					if(cIndex.getVacancy() >0) //Check if it has vacancy
//					{
						if (!studentExist(matricNumber, courseCode)) {
							StudentCourse studC = new StudentCourse(matricNumber,
									courseCode, courseIndex);
							list.add(studC);
							saveStudent(list);
							System.out.println("Course " + courseCode + "," + courseIndex
									+ " has been registered.");

						}
//						course.updateVacancy(courseIndex,(cIndex.getVacancy()-1));
//					}
//					else if(cIndex.getVacancy()<=0)
//					{
//						System.out.println("No vacancy");
//						return false;
//					}
//				}
//				else
//				{
//					System.out.println("Incorrect courseIndex.");
//					return false;
//				}
//			}

			
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	// delete a entry of student record
	public static void unregisterStudent(String matricNumber,
			String courseCode, int courseIndex) {
		List<StudentCourse> list = getCoursesRegistered(matricNumber);

		try {
			for (int i = 0; i < list.size(); i++) {
				StudentCourse studC = (StudentCourse) list.get(i);

				if (studC.getMatricNumber().equals(matricNumber)
						&& studC.getCourseCode().equals(courseCode)
						&& studC.getCourseIndex() == courseIndex) {
					list.remove(studC);
					saveStudent(list);
					System.out.println("Course " + courseCode + ","
							+ courseIndex + " has been dropped.");

					// ********* ADD VACANCY TO THE COURSE********

				}
			}
		} catch (Exception e) {
			System.out.println("Fail to drop.");
		}
	}

	// check if a student exist
	public static boolean studentExist(String matric, String courseCode) {
		List list = getRegisteredList();
		for (int i = 0; i < list.size(); i++) {
			StudentCourse studC = (StudentCourse) list.get(i);

			if (studC.getMatricNumber().equals(matric)
					&& studC.getCourseCode().equals(courseCode))
				return true; // Student exist
		}
		return false; // Student does not exist
	}

	private static void saveStudent(List list) {
		FileIO.writeToFile("studentRecords.dat", list);
	}

	private static void saveCourse(List list) {
		FileIO.writeToFile("courseRecords.dat", list);
	}

	public static void updateCourseIndex(String courseCode, int oldIndex,
			int newIndex) {
		List<StudentCourse> list = getRegisteredList();
		int found = 0;

		for (int i = 0; i < list.size(); i++) {
			StudentCourse studC = (StudentCourse) list.get(i);
			if (studC.getCourseCode().equals(courseCode)) {
				if (studC.getCourseIndex() == oldIndex) {
					found++;
					studC.setCourseIndex(newIndex);
					saveStudent(list);
					System.out.println("Index for course " + courseCode
							+ " has been changed from " + oldIndex + " to "
							+ newIndex + " .");
				}

			}
		}
		if (found == 0)
			System.out.println("Course code/course index does not exist.");

	}

}