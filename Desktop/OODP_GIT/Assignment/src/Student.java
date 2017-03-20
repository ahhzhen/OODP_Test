import java.util.*;
import java.io.*;

public class Student extends User {
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
    
	Student(String username, String matricNo, String name, char gender, String nationality)
	{
		super(username);
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
	
	
}
