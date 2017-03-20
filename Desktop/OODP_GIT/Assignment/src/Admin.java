

import java.util.*;
public class Admin extends User
{
	Admin(String u)
	{
		super(u);
	}
	public void addStudent()
	{
		String u; 
		String p;
		String name;
		String matricNo;
		char gender;
		String nationality;
		Scanner sc=new Scanner(System.in);
		System.out.println("Add Student (user,pw,name,matricNo,gender,nationality");
		u=sc.next();
		p=sc.next();
		name=sc.next();
		matricNo=sc.next();
		gender=sc.next().charAt(0);
		nationality=sc.next();
		
		/*add to DB*/
	}

	public void editStudentAccessPeriod()
	{

	}

	public void addCourse()
	{
		String courseCode;
		String courseSchool;
		ArrayList<Integer> courseIndex = new ArrayList<Integer>();
		int courseVacancy;
		System.out.println("Course Code:");
		courseCode=sc.next();
		System.out.println("School:");
		courseSchool=sc.next();
		System.out.println("Course Indexes:");
		while(sc.hasNext()){
			courseIndex.add(sc.nextInt());
		}
		System.out.println("Number of Vacancies:");
		courseVacancy=sc.nextInt();

		/*Add to DB
		 Course code, school, course indexes, vacancy

		 */

	}

	public void updateCourse()
	{
		String courseCode;
		int option=0;
		Scanner sc=new Scanner(System.in);
		/*Obtain course code from db*/
		while (option!=5){
			System.out.println("What do you want to update?");
			System.out.println("1.Course Code");
			System.out.println("2.School");
			System.out.println("3. Course Indexes");
			System.out.println("4. Vacancy");
			option=sc.nextInt();
			switch(option){
			case 1:
				System.out.println("Update Course Code:");
				String newCourseCode=sc.next();
				/*Update DB*/
				break;
			case 2:
				System.out.println("Update School:");
				String newCourseSchool=sc.next();
				/*Update DB*/
			case 3:
				/*print all existing indexes from DB*/
				System.out.println("Key in existing index to remove, new index to add:");
				int courseIndex=sc.nextInt();
				/*add/remove index from db*/
			case 4:
				System.out.println("Update Vacancies:");
				String newVacancies=sc.next();
				/*Update DB*/
			}
		}
		
	}
	
	public void checkVacancies(){
		Scanner sc=new Scanner(System.in);
		System.out.println("Please enter Course Code:");
		String courseCode=sc.next();
		/*query DB with coursecode*/
		/*print vacancies left*/
	}
	
	public void printStudentListByIndex(){
		Scanner sc=new Scanner(System.in);
		System.out.println("Please enter Index Number:");
		int indexNo = sc.nextInt();
		/*query db*/
		/*print list of students*/
	}

	public void printCourseListByCourse(){
		Scanner sc=new Scanner(System.in);
		System.out.println("Please enter Course Code:");
		String courseCode = sc.next();
		/*query db*/
		/*print list of students*/
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
