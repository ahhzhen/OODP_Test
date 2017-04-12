import java.util.*;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Admin extends User implements Serializable {
	Admin(String u, String pw) {
		super(u, pw, "Admin");
	}

	public void startSession() {
		int choice = -1;
		Scanner input = new Scanner(System.in);
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
					checkVacancies();
					break;
				case 5:
					printStudentListByIndex();
					break;
				case 6:
					printStudentListByCourse();
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
	
	public void editStudentAccessPeriod() {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter matriculation number of student: ");
		String mNo = input.next();
		Student stud = Student.getStudentByMatric(mNo);
		if(stud != null) {
			System.out.println("Acess period of " + stud.getMatricNo() + ":");
			System.out.println(stud.printStartDate() + " - " + stud.printEndDate());
			System.out.print("Enter new access period date for student(DD/MM/YYYY): ");
			String startDate = input.next();
			System.out.println("Enter start time of the access period(HH:MM): ");
			String startTime = input.next();
			System.out.print("Enter new access period date for student(DD/MM/YYYY): ");
			String endDate = input.next();
			System.out.println("Enter start time of the access period(HH:MM): ");
			String endTime = input.next();
			String newStartGiven = startDate + " " + startTime;
			String newEndGiven = endDate + " " + endTime;
			DateFormat d = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			try {
				Date newStartDate = d.parse(newStartGiven);
				Date newEndDate = d.parse(newEndGiven);
				if(newStartDate.before(newEndDate)) {
					stud.setStartDate(newStartDate);
					stud.setEndDate(newEndDate);
					System.out.println("Access time successfully changed.");
				}
				else
					System.out.println("Start daytime must be before end daytime.");
			} catch (ParseException e) {
				System.out.println("Wrong date/time format is entered!");
				//e.printStackTrace();
			}
		}
	}
	
	public void addStudent()
	{
		PasswordHash pwHash = new PasswordHash();
		char gender;
		String userid, matricNo, password, name, nationality;
		Scanner input = new Scanner(System.in);
		System.out.println("--------Add Student--------");
		System.out.print("Enter user id for student: ");
		userid = input.next();
		System.out.print("Enter matriculation number for student: ");
		matricNo = input.next();
		System.out.print("Enter login password for student: ");
		password = pwHash.hash(input.next(), userid);
		if(Student.checkStudentExist(matricNo, userid)) {
			System.out.print("Enter name of student: ");
			input.nextLine();
			name = input.nextLine();
			System.out.print("Enter nationality of student: ");
			nationality = input.next();
			System.out.print("Enter gender of student: ");
			gender = input.next().charAt(0);
			Student stud = new Student(userid, password, matricNo, name, gender, nationality);
			stud.addNewStudentToFile();
		}
		else
			System.out.println("Student already exist!");
	}
	
	public void modifyCourse() {
		Scanner input = new Scanner(System.in);
		System.out.println("1. Add Course");
		System.out.println("2. Update Course");
		int choice = input.nextInt();
		switch (choice) {
		case 1	:
			addCourse();
			break;
		case 2:
			updateCourse();
			break;
		default:
			System.out.println("Please enter either choice 1 or 2");
		}
	}
	
	public String checkCourse()
	{
		Scanner input = new Scanner(System.in);
		System.out.print("Enter Course Code: ");
		String coursecode = input.nextLine();
		if (Course.courseExist(coursecode))
			return coursecode;
		else return "";
	}
	
	public void addCourse() {
		String coursecode = checkCourse();
		if(coursecode!="") {
			Course.addCourse(coursecode);
		}else {
			System.out.println("Course Code exist!");
		}
	}
	
	public void updateCourse() {
		Scanner input = new Scanner(System.in);
		String coursecode = checkCourse();
		if(coursecode!="") {
			try {
				Course c = Course.getCourse(coursecode);
						
				System.out.println("1. Edit Course Name");
				System.out.println("2. Edit School");
				System.out.println("3. Edit Course Index");
				System.out.println("4. Edit Course Vacancy");
				int choice = input.nextInt();
				switch (choice) {
				case 1:
					System.out.println("Current Course Name is : " + c.getName());
					System.out.print("Enter new course name (Enter -1 to cancel) :");
					String nameinput = input.next();
					if (nameinput == "-1")
						break;
					else 
						c.editCourseName(nameinput, c.getCourseCode());
					break;
				case 2:
					System.out.println("Current School is : " + c.getSchool());
					System.out.print("Enter new School (Enter -1 to cancel) :");
					String schoolinput = input.next();
					if (schoolinput == "-1") 
						break;
					else
						c.editSchool(c.getCourseCode(), schoolinput);
					break;
				case 3:
					c.printIndexes();
					System.out.print("Enter Index to change: ");
					int oldIndex = input.nextInt();
					System.out.print("Enter new index for " + oldIndex + " (Enter -1 to cancel) : ");
					int newIndex = input.nextInt();
					if(newIndex != -1) {
						if(c.indexExist(oldIndex) && !c.indexExist(newIndex))
							c.editIndex(oldIndex, newIndex);
					}
					break;
				case 4:
					c.printIndexes();
					System.out.print("Enter which index vacancy to change: ");
					int index = input.nextInt();
					if(c.indexExist(index)) {
						System.out.print("Current total vacancy: " + c.retrieveTotalVacancy(index) + " (Current number of registered students: " + c.retrieveVacancy(index) +")");
						System.out.print("Enter new vacancy: (Enter -1 to cancel)");
						int newVacancy = input.nextInt();
						if(newVacancy != -1) {
							if(newVacancy >= c.retrieveVacancy(index)) {
								c.updateVacancy(index, newVacancy);
								System.out.println("Vacancy successfully updated");
							}
							else
								System.out.println("Invalid vacancy entered!");
						}
					}
					break;
				default:
					System.out.println("Invalid Input");
				}
			} catch (InputMismatchException e) {}
			catch (Exception e) {}
		} else
			System.out.println("Course Code does not exist!");
	}
	
	public void checkVacancies() {
		String coursecode = checkCourse();
		if(coursecode!="") {
			Course c = Course.getCourse(coursecode);
			c.checkVacancy();
		}else {
			System.out.println("Course Code does not exist!");
		}
	}
	
	public void printStudentListByIndex(){
		String coursecode = checkCourse();
		if(coursecode!="") {
			Course c = Course.getCourse(coursecode);
			c.printStudents();
		}else {
			System.out.println("Course Code does not exist!");
		}
	}

	public void printStudentListByCourse() {
		String coursecode = checkCourse();
		if(coursecode!="") {
			Course c = Course.getCourse(coursecode);
			c.printStudents();
		}else {
			System.out.println("Course Code does not exist!");
		}
	}
	
	public void quit() {
		System.out.println("Thank you for using STARSApp");
	}
}