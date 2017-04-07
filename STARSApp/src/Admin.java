import java.util.*;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Admin extends User {
	Admin(String u) {
		super(u);
	}

	public void startSession() {
		int choice = -1;
		Scanner input = new Scanner(System.in);
		AdminCourse adminc = new AdminCourse();
		while (choice != 7) {
			displayMenu();
			System.out.println("Please select choice from menu: ");
			int in = input.nextInt();
			try {
				choice = in;
				switch (choice) {
				case 1:
					editStudentAccessPeriod();
					break;
				case 2:
					addStudent();
					break;
				case 3:
					modifyCourse();
					break;
				case 4:
					//adminc.checkVacancies();
					break;
				case 5:
					printStudentListByIndex();
					break;
				case 6:
					//adminc.printStudentListByCourse();
					break;
				case 7:
					quit();
					break;
				default:
					System.out.println("Wrong input detected, please try again!");
				}
			} catch (Exception e) {
				input.nextLine();
				e.printStackTrace();
			}
		}
	}

	public void displayMenu() {
		System.out.println("");
		System.out.println("1. Edit A Student's Access Period");
		System.out.println("2. Add A Student");
		System.out.println("3. Add/Update Course");
		System.out.println("4. Check vacancies of a course");
		System.out.println("5. Print Student List By Index Number");
		System.out.println("6. Print Student List By Course");
		System.out.println("7. Quit");
	}
	
	public void editStudentAccessPeriod()
	{
		Scanner input = new Scanner(System.in);
		System.out.print("Enter matriculation number of student: ");
		String mNo = input.next();
		Student stud = Student.getStudentByMatric(mNo);
		if(stud != null)
		{
			System.out.println("Acess period of " + stud.getMatricNo() + ":");
			System.out.println(stud.printStartDate() + " - " + stud.printEndDate());
			System.out.print("Enter new access period date for student(DD/MM/YYYY): ");
			String date = input.next();
			System.out.println("Enter start time of the access period(HH:MM): ");
			String time = input.next();
			String newDateGiven = date + " " + time;
			DateFormat d = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			try {
				Date newDate = d.parse(newDateGiven);
				stud.setStartDate(newDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void addStudent()
	{
		char gender;
		String userid, matricNo, name, nationality;
		Scanner input = new Scanner(System.in);
		System.out.println("--------Add Student--------");
		System.out.print("Enter user id for student: ");
		userid = input.next();
		System.out.print("Enter matriculation number for student: ");
		matricNo = input.next();
		if(Student.getStudentByMatric(matricNo) == null && Student.getStudentByUsername(userid) == null)
		{
			System.out.print("Enter name of student: ");
			name = input.next();
			System.out.print("Enter nationality of student: ");
			nationality = input.next();
			System.out.print("Enter gender of student: ");
			gender = input.next().charAt(0);
			Student stud = new Student(userid, "", matricNo, name, gender, nationality);
			stud.addNewStudentToFile();
		}
		else
			System.out.println("Student already exist!");
	}
	
	public void modifyCourse() {
		Scanner sc = new Scanner(System.in);
		AdminCourse adminc = new AdminCourse();
		System.out.println("1. Add Course");
		System.out.println("2. Update Course");
		int input = sc.nextInt();
		switch (input) {
		case 1:
			adminc.addCourse();
			break;
		case 2:
			adminc.updateCourse();
		default:
			System.out.println("Please enter either choice 1 or 2");
		}
	}
	public void printStudentListByIndex(){
		AdminCourse adminc=new AdminCourse();
		adminc.printStudentListByIndex();
	}
	
	public void quit() {
		System.out.println("Thank you for using STARSApp");
	}
}