

import java.util.*;
public class Admin extends User
{
	Admin(String u)
	{
		super(u);
	}
	public void addStudent(String u, String p)
	{
		//read write to file the details
	}
	
	public void editStudentAccessPeriod()
	{
		
	}
	
	public void addCourse()
	{
		
	}
	
	public void updateCourse()
	{
		
	}
	
	public void displayMenu()
	{
		System.out.println("");
		System.out.println("1. Edit A Student's Access Period");
		System.out.println("2. Add A Student");
		System.out.println("3. Add/Update Course");
		System.out.println("4. Check vacancies of a course");
		System.out.println("5. Print Student List By Index Number");
		System.out.println("6. Print Student List By Course");
		System.out.println("7. Quit");
	}

}
