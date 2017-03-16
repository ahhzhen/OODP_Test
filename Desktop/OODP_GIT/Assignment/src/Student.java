import java.util.*;
public class Student extends User {
	private String matricNo;
	private String name;
	private char gender;
	private String nationality;
	private Date start;
	private Date end;
	private Course[] cList;
	
	Student(String u, String matric, String n, char c, String nation)
	{
		super(u);
		matricNo = matric;
		name = n;
		gender = c;
		nationality = nation;
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
	
/*	public void startSession()
	{
		int choice = 0;
		Scanner input = new Scanner(System.in);
		while(choice != 0)
		{
			displayMenu();
			System.out.println("Please select choice from menu: ");
			try{
			choice = input.nextInt();}
			catch(Exception e){}
		switch(choice)
		{
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
		case 6:
			break;
		case 7:
			break;
			default:System.out.println("Wrong input detected, please try again!");
		}*/
	
}
