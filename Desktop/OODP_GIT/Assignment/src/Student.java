import java.util.*;
import java.io.*;

public class Student extends User {
	private static String matricNo;
	private String name;
	private char gender;
	private String nationality;
	private Date start;
	private Date end;

	public Student(String username, String matricNumber, String name,
			char gender, String nationality) {
		super(username);
		this.matricNo = matricNumber;
		this.name = name;
		this.gender = gender;
		this.nationality = nationality;
	}

	public static String getMatricNo() {
		return matricNo;
	}

	public String getName() {
		return name;
	}

	public char getGender() {
		return gender;
	}

	public String getNationality() {
		return nationality;
	}

	public Date getStartDate() {
		return start;
	}

	public Date getEndDate() {
		return end;
	}

	public void displayMenu() {
		System.out.println("");
		System.out.println("1. Add Course");
		System.out.println("2. Drop Course");
		System.out.println("3. Check Courses Registered");
		System.out.println("4. Check vacancies available");
		System.out.println("5. Change index number of a course");
		System.out.println("6. Swop index number with another student");
		System.out.println("7. Quit");
	}

	public void addCourse() {
		String courseCode;
		int courseIndex;
		Scanner scannerInput = new Scanner(System.in);
		List<Course> cList=Course.getCourseList();
		
//		//print a list of available courses & it's code.
//		for(int i=0;i<cList.size();i++)
//		{
//			Course course = (Course) cList.get(i);
//			System.out.println(course);
//		}
		
		System.out.println("Please enter course code you wish to add:");
		courseCode = scannerInput.nextLine();
		System.out.println("Please enter course index you wish to add:");
		courseIndex = scannerInput.nextInt();

		StudentCourse.registerStudent(matricNo, courseCode, courseIndex);
	}

	public void dropCourse() {
		List<StudentCourse> list = StudentCourse.getCoursesRegistered(getMatricNo());
		
		if (list.size() == 0)
			System.out.println("No Courses registered");
		else
		{
			System.out.println("Courses registered:");
			System.out.println("------------------------------------------");
			for (int i = 0; i < list.size(); i++) {
				System.out.println("CourseCode: " + list.get(i).getCourseCode());
				System.out.println("CourseIndex: " + list.get(i).getCourseIndex());
				System.out.println("------------------------------------------");
			}
		}

		String courseCode;
		int courseIndex;
		Scanner scannerInput = new Scanner(System.in);
		System.out.println("Please enter course code you wish to drop:");
		courseCode = scannerInput.nextLine();
		System.out.println("Please enter course index you wish to drop:");
		courseIndex = scannerInput.nextInt();

		StudentCourse.unregisterStudent(matricNo, courseCode, courseIndex);
	}

	public void checkOrPrintCoursesRegistered() {
		// TODO Auto-generated method stub
		List<StudentCourse> list = StudentCourse.getCoursesRegistered(matricNo);
		if (list.size() == 0)
			System.out.println("No Courses registered");
		else
		{
			System.out.println("Courses registered:");
			System.out.println("------------------------------------------");
			for (int i = 0; i < list.size(); i++) {
				System.out.println("Course code: " + list.get(i).getCourseCode());
				System.out.println("Course index: " + list.get(i).getCourseIndex());
				System.out.println("------------------------------------------");
			}
		}

	}

	public int checkVacanciesAvailable() {

		return -1;
	}

	public void changeIndexNumber() {
		String courseCode;
		int newIndex,oldIndex;
		Scanner scanInput = new Scanner(System.in);
		
		System.out.println("Enter course code:");
		courseCode = scanInput.nextLine();
		System.out.println("Enter old course index:");
		oldIndex=scanInput.nextInt();
		System.out.println("Enter new course index:");
		newIndex=scanInput.nextInt();
		
		StudentCourse.updateCourseIndex(courseCode,oldIndex,newIndex);
		
	}

	public void swopIndexNumber() {

	}

	public void quit() {
		System.out.println("Thank you for using STARSApp");
	}
}