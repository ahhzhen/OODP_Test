import java.util.*;
import java.io.*;
import java.text.*;

public class Student extends User implements Serializable {
	private String matricNo;
	private String name;
	private char gender;
	private String nationality;
	private Date start;
	private Date end;
	private CourseIndex[] cList;
    
    Student(){super();}
    
	Student(String username, String password, String matricNo, String name, char gender, String nationality) {
		super(username,password,"Student");
		this.matricNo = matricNo;
		this.name = name;
		this.gender = gender;
		this.nationality = nationality;
		setInitialDate(); 
	}
	
	private void updateStudentToFile() {
		List list = getStudentList();
		for(int i = 0; i < list.size(); i++)
		{
			Student stud = (Student) list.get(i);
			if(stud.getMatricNo().equals(matricNo)){
				list.set(i, this);
			}
		}
		save(list);
	}
	
	public void setInitialDate() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, 3);
        cal.set(Calendar.DATE, 12);
        cal.set(Calendar.YEAR, 2017);
        cal.set(Calendar.HOUR_OF_DAY, 3);
        cal.set(Calendar.MINUTE,00);
        cal.set(Calendar.SECOND,00);
        //cal.set(Calendar.AM_PM, 1);
        this.start = cal.getTime();
        
        cal.add(Calendar.HOUR_OF_DAY, 20);
        this.end = cal.getTime();
	}
	
	public void setStartDate(Date newDate) {
		this.start = newDate;
		updateStudentToFile();
	}
	
	public void setEndDate(Date newDate) {
		this.end = newDate;
		updateStudentToFile();
	}
	
	public String getMatricNo(){return matricNo;}
	
	public String getName(){return name;}
	
	public char getGender(){return gender;}
	
	public String getNationality(){return nationality;}
	
	public Date getStart(){return start;}
	
	public Date getEnd(){return end;}
	
	public String printStartDate() {
		SimpleDateFormat ft = new SimpleDateFormat("E dd/MM/yyyy HH:mm:ss");
		return ft.format(getStart());
	}
	
	public String printEndDate() {
		SimpleDateFormat ft = new SimpleDateFormat("E dd/MM/yyyy HH:mm:ss");
		return ft.format(getEnd());
	}
	
	public void startSession() {
		int choice = -1;
		Scanner input = new Scanner(System.in);
		while(choice!= 8)
		{
			displayMenu();
			System.out.print("Please select choice from menu: ");
			try{
				choice = input.nextInt();
			}
			catch(Exception e)
				{input.next();}
		
			switch(choice)
			{
			case 1: 
				addCourse();
				break;
			case 2:
				dropCourse();
				break;
			case 3:
				checkOrPrintCoursesRegistered();
				break;
			case 4:
				checkVacanciesAvailable();
				break;
			case 5:
				changeIndexNumber();
				break;
			case 6:
				swapIndexNumber();
				break;
			case 7:
				StudentCourse.printTimeTable(matricNo);
				break;
			case 8:
				quit();
				break;
			default:System.out.println("Wrong input detected, please try again!");
			}
		}
	}
	
	public void displayMenu() {
		System.out.println("");
		System.out.println("1. Add Course");
		System.out.println("2. Drop Course");
		System.out.println("3. Check Courses Registered");
		System.out.println("4. Check vacancies available");
		System.out.println("5. Change index number of a course");
		System.out.println("6. Swop index number with another student");
		System.out.println("7. Print Timetable");
		System.out.println("8. Quit");
	}
	
	public static Student getStudentByUsername(String username) {
		Student s = null;
		List studList = getStudentList();
		for(int i = 0; i < studList.size();i++)
		{
			Student stud = (Student)studList.get(i);
			if(stud.getUsername().equals(username))
				return stud;
		}
		return s;
	}
	
	public static Student getStudentByMatric(String matricNo) {
		Student s = null;
		List studList = getStudentList();
		for(int i = 0; i < studList.size();i++)
		{
			Student stud = (Student)studList.get(i);
			if(stud.getMatricNo().equals(matricNo))
				return stud;
		}
		return s;
	}
	
	public static boolean checkStudentExist(String matricNo, String username) {
		Student s1 = getStudentByUsername(username);
		Student s2 = getStudentByMatric(matricNo);
		return (s1 == null && s2 == null);
	}
	
	public boolean checkAccessPeriod() {
		Date currentTime = new Date();
		if(currentTime.after(start) && currentTime.before(end))
			return true;
		else{ 
			System.out.println("You are not allowed to register for course now!");
			return false;
		}
	}
	
	public boolean checkPassword(String password) {
		PasswordHash pHash = new PasswordHash();
		if(pHash.hash(password, getUsername()).equals(getPassword()))
			return true;
		return false;
	}
	
	public void addNewStudentToFile() {
		List list = getStudentList();
		list.add(this);
		save(list);
		User u = (User) this;
		u.addNewUserToFile();
		System.out.println("Student " + name + " successfully registered!");
	}

	public void addCourse() {
		StudentCourse.registerStudent(matricNo); //need to call static
	}
	
	public void dropCourse() {
		//get course code and course index
		//check whether enrolled
		StudentCourse.unregisterStudent(matricNo);
	}
	
	public void checkOrPrintCoursesRegistered() {
		// TODO Auto-generated method stub
		List<StudentCourse> list = StudentCourse.getCoursesRegistered(matricNo);
		if (list.size() == 0)
			System.out.println("No Courses registered");
		else
		{
			System.out.println("Courses registered:");
			System.out.println("------------------------------------------");
			for (int i = 0; i < list.size(); i++) {
				System.out.println("Course code: " + list.get(i).getCourseCode());
				System.out.println("Course index: " + list.get(i).getCourseIndex());
				System.out.println("------------------------------------------");
			}
		}
	}

	public void checkVacanciesAvailable() {

		StudentCourse.checkVacancy();
	}
	
	public void changeIndexNumber() {
		StudentCourse.changeCourseIndex(matricNo);
	}

	public void swapIndexNumber() {
		StudentCourse.swapCourse(matricNo);
	}

	public void quit() {
		System.out.println("Thank you for using STARSApp");
	}
	
	public static List getStudentList() {
		return getStudentList("studentList.dat");
	}
	
	public static List getStudentList(String filename) {
		List list = null;
		try {
			list = FileIO.readInFile(filename);
		} catch (Exception e) {
		}
		if (list == null)
			list = new ArrayList();
		return list;
	}
	
	public void save(List list) {
		FileIO.writeToFile("studentList.dat", list);
	}

}