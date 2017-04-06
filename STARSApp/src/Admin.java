import java.util.*;
import java.io.*;

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
			try {
				int in = input.nextInt();
				choice = in;
				switch (choice) {
				case 1:
					// adminc.editStudentAccessPeriod();
					break;
				case 2:
					// adminc.addStudent();
					break;
				case 3:
					modifyCourse();
					break;
				case 4:
					adminc.checkVacancies();
					break;
				case 5:
					printStudentListByIndex();
					break;
				case 6:
					adminc.printStudentListByCourse();
					break;
				case 7:
					// adminc.quit();
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
}