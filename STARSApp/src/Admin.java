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
	
	public boolean courseExist(String coursecode) {
		if (Course.courseExist(coursecode)) {
			return true;
		} else {
			return false;
		}
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
			String date = input.next();
			System.out.println("Enter start time of the access period(HH:MM): ");
			String time = input.next();
			String newDateGiven = date + " " + time;
			DateFormat d = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			try {
				Date newDate = d.parse(newDateGiven);
				stud.setStartDate(newDate);
			} catch (ParseException e) {
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
		if(Student.checkStudentExist(matricNo, userid)) {
			System.out.print("Enter name of student: ");
			name = input.next();
			System.out.print("Enter nationality of student: ");
			nationality = input.next();
			System.out.print("Enter gender of student: ");
			gender = input.next().charAt(0);
			Student stud = new Student(userid, "123456", matricNo, name, gender, nationality);
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
	
	public void addCourse() {
		Scanner input = new Scanner(System.in);
		Course course = new Course();
		System.out.print("Enter Course Code: ");
		String coursecode = input.nextLine();
		if (!courseExist(coursecode)) {
			System.out.print("Course Name: ");
			String coursename = input.nextLine();
			System.out.print("School: ");
			String school = input.nextLine();
			course.addCourse(coursename, coursecode, school);
		} else {
			System.out.println("Course Code exist!");
		}
	}
	
	public void updateCourse() {
		Scanner input = new Scanner(System.in);
		Course course = new Course();
		System.out.println("Enter Course Code: ");
		String coursecode = input.nextLine();
		if (courseExist(coursecode)) {
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
			} catch (Exception e) {}
		} else
			System.out.println("Course Code does not exist!");
	}
	
	public void checkVacancies() {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter course code: ");
		String cCode = input.next();
		System.out.print("Enter course index: ");
		int cIndex = input.nextInt();
		if(Course.courseExist(cCode)) {
			Course c = Course.getCourse(cCode);
			if(c.indexExist(cIndex))
				System.out.println("Vacancy for " + cIndex + " = " + c.retrieveVacancy(cIndex) + "/" + c.retrieveTotalVacancy(cIndex));
		}
	}
	
	public void printStudentListByIndex(){
		Scanner input = new Scanner(System.in);
		System.out.print("Enter course code: ");
		String cCode = input.next();
		System.out.print("Enter course index: ");
		int cIndex = input.nextInt();
		if(Course.courseExist(cCode)) {
			Course c = Course.getCourse(cCode);
			if(c.indexExist(cIndex)) {
				c.printStudents(cIndex);
			}
		}
	}

	public void printStudentListByCourse() {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter course code: ");
		String course = input.next();
		if(Course.courseExist(course)) {
			Course c = Course.getCourse(course);
			c.printStudents();
		}
	}
	
	public void quit() {
		System.out.println("Thank you for using STARSApp");
	}
}