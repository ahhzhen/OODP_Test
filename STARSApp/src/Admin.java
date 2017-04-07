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
					//adminc.editStudentAccessPeriod();
					System.out.print("Enter matriculation number of student: ");
					String mNo = input.next();
					Student stud = Student.getStudentByMatric(mNo);
					if(stud != null)
						editStudentAccessPeriod(stud);
					break;
				case 2:
					//adminc.addStudent();
					break;
				case 3:
					//adminc.modifyCourse();
					break;
				case 4:
					//adminc.checkVacancies();
					break;
				case 5:
					//adminc.printStudentListByIndex();
					break;
				case 6:
					//adminc.printStudentListByCourse();
					break;
				case 7:
					//adminc.quit();
					break;
				default:
					System.out.println("Wrong input detected, please try again!");
				}
			} catch (Exception e) {
				input.nextLine();
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
	
	public void editStudentAccessPeriod(Student stud)
	{
		Scanner input = new Scanner(System.in);
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
	
	public void addStudent()
	{
		String userid, matricNo, name, nationality;
		Scanner input = new Scanner(System.in);
		System.out.println("--------Add Student--------");
		System.out.print("Enter user id for student: ");
		userid = input.next();
		System.out.print("Enter matriculation number for student: ");
		matricNo = input.next();
		System.out.print("Enter name for student: ");
		name = input.next();
		
	}
}