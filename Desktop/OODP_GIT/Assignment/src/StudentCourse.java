import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StudentCourse implements Serializable {
	private String matricNumber;
	private String courseCode;
	private String courseIndex;

	public StudentCourse(String matricNumber, String courseCode,
			String courseIndex) {
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

	public String getCourseIndex() {
		return courseIndex;
	}

	public static List getRegisteredList() {
		return getRegisteredList("studentRecords.txt");
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

	public List getCoursesRegistered(String matric) {
		List list = getRegisteredList();
		List<StudentCourse> courseList = new ArrayList<StudentCourse>();

		try {
			for (int i = 0; i < list.size(); i++) {
				StudentCourse studC = (StudentCourse) list.get(i);
				if (studC.getMatricNumber().equals(matric))
					courseList.add(studC);
			}
		} catch (Exception e) {
		}
		return courseList;
	}

	// add a new entry of student record
	public static boolean registerStudent(String matricNumber,
			String courseCode, String courseGroup) {
		List list = getRegisteredList();
		List<StudentCourse> studentList = new ArrayList<StudentCourse>();

		try {
			if (!studentExist(matricNumber, courseCode)) {
				StudentCourse studC = new StudentCourse(matricNumber,
						courseCode, courseGroup);
				studentList.add(studC);
				System.out.println("Course " + courseCode + "," + courseGroup
						+ " has been registered.");
			}
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	// delete a entry of student record
	public static boolean unregisterStudent(String matricNumber,
			String courseCode, String courseIndex) {
	//	List list = getRegisteredList();
		List<StudentCourse> studentList = new ArrayList<StudentCourse>();

		try {
			for (int i = 0; i < studentList.size(); i++) {
				StudentCourse studC = (StudentCourse) studentList.get(i);
				
					if (studC.getMatricNumber().equals(matricNumber)&& studC.getCourseCode().equals(courseCode)
							&& studC.getCourseIndex().equals(courseIndex)) {
						studentList.remove(studC);
						System.out.println("Course " + courseCode + "," + courseIndex + " has been dropped.");
						}

			} 
		} catch (Exception e) {
			return false;
		}

		return true;
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

}
