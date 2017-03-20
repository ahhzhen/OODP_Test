

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.*;
public class Admin extends User
{
	private ArrayList<ArrayList> studentList = new ArrayList<ArrayList>();
	private ArrayList<ArrayList> indexList = new ArrayList<ArrayList>();
	Admin(String u)
	{
		super(u);
	}

	private void establishDB(){
		try {
			//Retrieve from db StudentList
			Scanner scStream = new Scanner(new File("studentInfo.txt")).useDelimiter("\\s*,\\s*");
			String input;
			int i=0;
			while(scStream.hasNext()){
				ArrayList<String> eachStudent=new ArrayList<String>();
				for(i=0;i<7;i++){
					input=scStream.next();
					System.out.println(i);
					eachStudent.add(i,input);
				}
				studentList.add(eachStudent);
			}
			scStream.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Retrieve from db indexInfo
		try {
			Scanner scStream = new Scanner(new File("indexInfo.txt")).useDelimiter("\\s*,\\s*");
			String input;
			int i=0;
			while(scStream.hasNext()){
				ArrayList<String> eachIndex=new ArrayList<String>();
				for(i=0;i<4;i++){
					input=scStream.next();
					System.out.println(i);
					eachIndex.add(i,input);
				}
				indexList.add(eachIndex);
			}
			scStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int x=0;x<studentList.size();x++){
			for(int y=0;y<studentList.get(x).size();y++){
				System.out.println(studentList.get(x).get(y));
			}
			System.out.println();
		}
		for(int x=0;x<indexList.size();x++){
			for(int y=0;y<indexList.get(x).size();y++){
				System.out.println(indexList.get(x).get(y));
			}
			System.out.println();
		}


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
				break;
			case 3:
				/*print all existing indexes from DB*/
				System.out.println("Key in existing index to remove, new index to add:");
				int courseIndex=sc.nextInt();
				/*add/remove index from db*/
				break;
			case 4:
				System.out.println("Please pick Course Index:");
				int selectCourseIndex=sc.nextInt();
				System.out.println("Update Vacancies:");
				String newVacancies=sc.next();
				/*Update DB with course index and new vacancy*/
				break;
			default:
				break;
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
		for(int i=0;i<studentList.size();i++){
			if(Integer.toString(indexNo).equals(studentList.get(i).get(6))){
				System.out.println(studentList.get(i).get(0));
			}
		}
	}

	public void printStudentListByCourse(){
		Scanner sc=new Scanner(System.in);
		System.out.println("Please enter Course Code:");
		String courseCode = sc.next();
		/*query db*/
		/*print list of students*/
	}

	public void displayMenu()
	{
		Scanner s=new Scanner(System.in);
		int choice=0;
		establishDB();
		while(choice!=7){
			System.out.println("");
			System.out.println("1. Edit A Student's Access Period");
			System.out.println("2. Add A Student");
			System.out.println("3. Add/Update Course");
			System.out.println("4. Check vacancies of a course");
			System.out.println("5. Print Student List By Index Number");
			System.out.println("6. Print Student List By Course");
			System.out.println("7. Quit");
			choice=s.nextInt();
			switch(choice){
			case 1:
				editStudentAccessPeriod();
				break;
			case 2:
				addStudent();
				break;
			case 3:
				addCourse();
				break;
			case 4:
				checkVacancies();
				break;
			case 5:
				printStudentListByIndex();
				break;
			case 6:
				printStudentListByCourse();
				break;
			default:
				break;
			}
		}


	}

}
