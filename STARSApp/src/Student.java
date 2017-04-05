import java.util.*;
import java.io.*;

public class Student extends User implements Serializable {
	private String matricNo;
	private String name;
	private char gender;
	private String nationality;
	private Date start;
	private Date end;
	private CourseIndex[] cList;
	
	
	 //create linked list
    LinkedList<studentRecords> list = new LinkedList<studentRecords>();    
    String inFile = "studentRecords.txt";
    //inititate readers to null
    FileReader fr = null;
    BufferedReader br = null;
    
	Student(String username, String password, String matricNo, String name, char gender, String nationality)
	{
		super(username,password,"Student");
		this.matricNo = matricNo;
		this.name = name;
		this.gender = gender;
		this.nationality = nationality;
	}
	
	public String getMatricNo(){return matricNo;}
	
	public String getName(){return name;}
	
	public char getGender(){return gender;}
	
	public String getNationality(){return nationality;}
	
	public Date getStartDate(){return start;}
	
	public Date getEndDate(){return end;}
	
	public void startSession()
	{
		int choice = -1;
		Scanner input = new Scanner(System.in);
		while(choice!= 0)
		{
			displayMenu();
			System.out.println("Please select choice from menu: ");
			try{
				choice = input.nextInt();
			}
			catch(Exception e)
				{input.next();}
		
			switch(choice)
			{
			case 1: 
				addCourse();
				break;
			case 2:
				dropCourse();
				break;
			case 3:
				checkOrPrintCoursesRegistered();
				break;
			case 4:
				checkVacanciesAvailable();
				break;
			case 5:
				changeIndexNumber();
				break;
			case 6:
				swopIndexNumber();
				break;
			case 7:
				quit();
				break;
			case 0:
				System.out.println("Program exiting....");
				break;
			default:System.out.println("Wrong input detected, please try again!");
			}
		}
	}
	
	public void displayMenu()
	{
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
		StudentCourse.registerStudent(matricNo);
	}
	
	public static Student getStudentByUsername(String username)
	{
		Student s = null;
		List studList = getStudentList();
		for(int i = 0; i < studList.size();i++)
		{
			Student stud = (Student)studList.get(i);
			if(stud.getUsername().equals(username))
				return stud;
		}
		return s;
	}
	
	public static Student getStudentByMatric(String matricNo)
	{
		Student s = null;
		List studList = getStudentList();
		for(int i = 0; i < studList.size();i++)
		{
			Student stud = (Student)studList.get(i);
			if(stud.getMatricNo().equals(matricNo))
				return stud;
		}
		return s;
	}
	
/*	public static boolean checkPassword(String password)
	{
		PasswordHash pHash = new PasswordHash();
		if(pHash.hash(password).equals(getPassword()))
			return true;
		return false;
	}*/
	
	public void dropCourse() {
		StudentCourse.unregisterStudent(matricNo);
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
		StudentCourse.changeCourseIndex(matricNo);
	}

	public void swopIndexNumber() {
		StudentCourse.swapCourse(matricNo);
	}

	public void quit() {
		System.out.println("Thank you for using STARSApp");
	}
	
	public static List getStudentList()
	{
		return getStudentList("studentList.dat");
	}
	
	public static List getStudentList(String filename)
	{
		List list = null;
		try {
			list = FileIO.readInFile(filename);
		} catch (Exception e) {
		}
		if (list == null)
			list = new ArrayList();
		return list;
	}
	
	public void save(List list)
	{
		FileIO.writeToFile("studentList.dat", list);
	}

}
