import java.util.*;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Admin extends User implements Serializable {
	
	/**
	 * Constructor for admin class. It initalises username and password by calling the
	 * super class. 
	 * @param  u  the username of admin.
	 * @param  pw the password of admin.
	 */
	Admin(String u, String pw) {
		super(u, pw, "Admin");
	}
	/**
	 * Display a menu which the admin user can select actions to
	 * perform.
	 */
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
	/**
	 * This method always returns display the available options
	 * that the admin can choose from.
	 */
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
	
	/**
	 * Edit the access period of the student based on input
	 * provided by the user. It sets and amend the period
	 * of time that a student can access the program.
	 */
	public void editStudentAccessPeriod() {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter matriculation number of student: ");
		String mNo = input.next();
		Student stud = Student.getStudentByMatric(mNo);
		if(stud != null) {
			System.out.println("Acess period of " + stud.getMatricNo() + ":");
			System.out.println(stud.printStartDate() + " - " + stud.printEndDate());
			System.out.print("Enter new access period start date for student(DD/MM/YYYY): ");
			String startDate = input.next();
			System.out.print("Enter start time of the access period(HH:MM): ");
			String startTime = input.next();
			System.out.print("Enter new access period end date for student(DD/MM/YYYY): ");
			String endDate = input.next();
			System.out.print("Enter start time of the access period(HH:MM): ");
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
		else
			System.out.println("Student does not exist.");
	}
	/**
	 * This method adds a student based on the input given.
	 */
	
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
	/**
	 * This methods is able to add a new course or update a course
	 * based on the option chosen by the user and the valid input given
	 * by the user.
	 */
	public void modifyCourse() {
		Scanner input = new Scanner(System.in);
		System.out.println("1. Add Course");
		System.out.println("2. Update Course");
		System.out.print("Choice: ");
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
	/**
	 * This method will always return a course code if the course code given is valid.
	 * Otherwise, it will return a "". 
	 * @return      course code, if it exist.
	 * @return		"", if it does not exist.
	 */
	public String checkCourse()
	{
		Scanner input = new Scanner(System.in);
		System.out.print("Enter Course Code: ");
		String coursecode = input.nextLine().toUpperCase();
		if (Course.courseExist(coursecode))
			return coursecode;
		else return "";
	}
	/**
	 * This method adds a new course based on the input provided
	 * by the user.
	 */
	
	public void addCourse() {
		System.out.println("---------------Add Course---------------");
		String coursecode = checkCourse();
		if(coursecode=="") {
			Course.addCourse(coursecode);
		}else {
			System.out.println("Course Code exist!");
		}
	}
	/**
	 * updateCoourse uses checkCourse to prompt the user to enter the course code.
	 * Upon receiving a valid course code, updateCourse will prompt the user to either edit:
	 * 1. Course name : prompts for new course name and then calls the editCourseName method 
	 * under Course
	 * 2. School : prompts for the new school name, then calls the editSchool method under Course
	 * 3. Course index : prompts user to input the index to be changed, as well as the new index to change into. Returns an error 
	 * if an invalid input is detected.
	 * 4. Course vacancy : prompts user to input the index for which the vacancy will be changed, then the new vacancy. Returns
	 * an error if the new vacancy is less than the number of students in the course. Also returns an error if there are invalid inputs
	 * or the course index entered does not exist.
	 */
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
						c.editCourseName(nameinput);
					break;
				case 2:
					System.out.println("Current School is : " + c.getSchool());
					System.out.print("Enter new School (Enter -1 to cancel) :");
					String schoolinput = input.next();
					if (schoolinput == "-1") 
						break;
					else
						c.editSchool(schoolinput);
					break;
				case 3:
					c.printIndexes();
					try{
						System.out.print("Enter Index to change: ");
						int oldIndex = input.nextInt();
						System.out.print("Enter new index for " + oldIndex + " (Enter -1 to cancel) : ");
						int newIndex = input.nextInt();
						if(newIndex != -1) {
							if(c.indexExist(oldIndex) && !c.indexExist(newIndex))
								c.editIndex(oldIndex, newIndex);
							else
								System.out.println("Error occured. Invalid course index entered");
						}
					}
					catch(InputMismatchException e) {
						System.out.println("Error occured. Invalid course index entered");
					}
					break;
				case 4:
					c.printIndexes();
					try{
						System.out.print("Enter which index vacancy to change: ");
						int index = input.nextInt();
						if(c.indexExist(index)) {
							System.out.println("Current total vacancy: " + c.retrieveTotalVacancy(index) + " (Current number of registered students: " + c.retrieveVacancy(index) +")");
							System.out.print("Enter new vacancy (Enter -1 to cancel): ");
							int newVacancy = input.nextInt();
							if(newVacancy != -1) {
								if(newVacancy >= c.retrieveVacancy(index)) {
									c.updateVacancy(index, newVacancy);
									System.out.println("Vacancy successfully updated");
								}
								else
									System.out.println("Invalid vacancy entered!");
							}
							else
								System.out.println("New vacancy cannot be lesser than current number of registered students.");
						}
						else 
							System.out.println("Course index does not exist");
					}
					catch (InputMismatchException e) {
						System.out.println("Error occured. Please key in numeric values");
					}
					break;
				default:
					System.out.println("Invalid Input");
				}
			} catch (InputMismatchException e) {System.out.println("Invalid input"); input.next();}
			catch (Exception e) {}
		} else
			System.out.println("Course Code does not exist!");
	}
	/**
	 * checkVacancies checks for the number of empty slots available 
	 * for a given course and index. The course index is prompted by the Course class.
	 * An error message is printed when there are no/invalid inputs.
	 */
	public void checkVacancies() {
		String coursecode = checkCourse();
		if(coursecode!="") {
			Course c = Course.getCourse(coursecode);
			c.checkVacancy();
		}else {
			System.out.println("Course Code does not exist!");
		}
	}
	/**
	 * printStudentListByIndex prompts the user to input the course code and course index, then calls 
	 * printStudentsByIndex in Course in order to print the students based on index.
	 * Generates an error message for invalid/empty inputs.
	 */
	public void printStudentListByIndex(){
		String coursecode = checkCourse();
		Scanner input = new Scanner(System.in);
		if(coursecode!="") {
			Course c = Course.getCourse(coursecode);
			System.out.print("Enter course index: ");
			int courseIndex = input.nextInt();
			c.printStudentsByIndex(courseIndex);
		}else {
			System.out.println("Course Code does not exist!");
		}
	}
	/**
	 * printStudentListByCourse prompts the user to input a course code. The method will then proceed to retrieve the data
	 * for the particular Course, then call printStudents in Course to print the students within the particular Course.
	 * Error messages will be generated for non-existing/invalid course codes.
	 */
	public void printStudentListByCourse() {
		String coursecode = checkCourse();
		if(coursecode!="") {
			Course c = Course.getCourse(coursecode);
			c.printStudents();
		}else {
			System.out.println("Course Code does not exist!");
		}
	}
	/**
	 * The user chose to quit the program. The method will print a line indicating that the user has quit the program.
	 */
	public void quit() {
		System.out.println("Thank you for using STARSApp");
	}
}