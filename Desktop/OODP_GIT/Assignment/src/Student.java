import java.util.*;
import java.io.*;

public class Student extends User {
	private String username;
	private static String matricNo;
	private String name;
	private char gender;
	private String nationality;
	private Date start;
	private Date end;
	private CourseIndex[] cList;


	public Student(String username, String matricNo, String name, char gender,String nationality) {
		super(username);
		this.matricNo = matricNo;
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
		String courseCode,courseIndex;
		Scanner scannerInput = new Scanner(System.in);
		System.out.println("Please enter courseCode:");
		courseCode = scannerInput.nextLine();
		System.out.println("Please enter courseIndex:");
		courseIndex = scannerInput.nextLine();
		
		StudentCourse.registerStudent(matricNo,courseCode,courseIndex);
	}

	public void dropCourse() {
		System.out.println("Courses registered:");
		StudentCourse.getCoursesRegistered(matricNo);
		
		String courseCode,courseIndex;
		Scanner scannerInput = new Scanner(System.in);
		System.out.println("Please enter courseCode:");
		courseCode = scannerInput.nextLine();
		System.out.println("Please enter courseGroup:");
		courseIndex = scannerInput.nextLine();
		
		StudentCourse.unregisterStudent(matricNo, courseCode,courseIndex);
	}



	public void checkOrPrintCoursesRegistered() {
		// TODO Auto-generated method stub
		List<StudentCourse> list = StudentCourse.getCoursesRegistered(getMatricNo());
		
		System.out.println("------------------------------------------");
		for(int i =0;i<list.size();i++)
		{
			System.out.println("CourseCode: "+list.get(i).getCourseCode());
			System.out.println("CourseIndex: "+list.get(i).getCourseIndex());
			System.out.println("------------------------------------------");
		}
		
	}


	public static int checkVacanciesAvailable() {
		return -1;
	}

	public static void changeIndexNumber() {

	}

	public static void swopIndexNumber() {

	}
	
	public static void quit(){
		System.out.println("Thank you for using STARSApp");
	}
}
