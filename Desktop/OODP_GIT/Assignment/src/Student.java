import java.util.*;
import java.util.ArrayList;
import java.io.*;

public class Student extends User {
	private String username;
	private String matricNo;
	private String name;
	private char gender;
	private String nationality;
	private Date start;
	private Date end;
	private CourseIndex[] cList;

	// create linked list
	static LinkedList<studentRecords> studList = new LinkedList<studentRecords>();
	static LinkedList<courseRecords> courseList = new LinkedList<courseRecords>();
	static String studentRecordFile = "studentRecords.txt";
	String courseRecordFile = "testStudentFile.txt";
	// inititate readers to null
	static FileReader fr = null;
	BufferedReader br = null;

	Student(String username, String matricNo, String name, char gender,
			String nationality) {
		super(username);
		this.matricNo = matricNo;
		this.name = name;
		this.gender = gender;
		this.nationality = nationality;

		// ArrayList<String> courseList = new ArrayList<String>();
		// courseList.add("CZ2002;FSP5;10");
		// courseList.add("CZ2002;FSP4;10");
		// courseList.add("CZ2004;FSP3;10");
		// courseList.add("CZ2004;FSP2;10");
	}

	public String getMatricNo() {
		return matricNo;
	}

	public String getName() {
		return name;
	}

	public char getGender() {
		return gender;
	}

	public String getNationality() {
		return nationality;
	}

	public Date getStartDate() {
		return start;
	}

	public Date getEndDate() {
		return end;
	}

	public void displayMenu() {
		System.out.println("");
		System.out.println("1. Add Course");
		System.out.println("2. Drop Course");
		System.out.println("3. Check Courses Registered");
		System.out.println("4. Check vacancies available");
		System.out.println("5. Change index number of a course");
		System.out.println("6. Swop index number with another student");
		System.out.println("7. Quit");
	}

	public void addCourse() {
		String line = null;
		boolean append = true;
		try {
			String matricNumber;
			String courseCode;
			String courseGroup;

			// create reader to read from user input
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));

			FileWriter fw1 = new FileWriter(studentRecordFile, append);
			BufferedWriter bw1 = new BufferedWriter(fw1);
			PrintWriter pw1 = new PrintWriter(bw1);

			System.out.println("Please enter record in the following format:");
			System.out.println("<matricNo> | <CourseCode> | <CourseGroup>");
			line = br.readLine();

			// tokenize the records
			StringTokenizer tokenizer = new StringTokenizer(line, "|");
			// store all the tokenized fields of the record
			matricNumber = tokenizer.nextToken();
			courseCode = tokenizer.nextToken();
			courseGroup = tokenizer.nextToken();

			// add this item object into arraylist
			studentRecords newRecord = new studentRecords(matricNumber,
					courseCode, courseGroup);
			studList.add(newRecord);
			// printing empty to start on a new line
			pw1.println();
			pw1.print(newRecord);

			System.out.println("Record has been recorded");
			pw1.close();
			// close reader
			// br.close();

		} catch (FileNotFoundException e) {
			System.out.println("The file " + studentRecordFile
					+ " was not found.");
		} catch (IOException e) {
			System.out.println("Reading error!");
		} finally {
			if (fr != null) {
				try {
					fr.close();
				} catch (IOException e) {
					System.out.println("Error closing file!");
				}
			}
		}

		// retrieve testStudentFile to edit vacancy
		studentRecords sr;
		StringTokenizer tokenizer;
		String courseCode;
		String courseGroup;
		int vacancies;

		try {
			// File & BufferedReader
			fr = new FileReader(courseRecordFile);
			br = new BufferedReader(fr);
			
			FileWriter fw2 = new FileWriter(courseRecordFile, append);

			BufferedWriter bw2 = new BufferedWriter(fw2);
			PrintWriter pw2 = new PrintWriter(bw2);

			// readline
			line = br.readLine();
			// while line is not null
			while (line != null) {
				// Apply StringTokenizer
				tokenizer = new StringTokenizer(line, "|");
				// Instantiate newPatient object
				courseCode = tokenizer.nextToken();
				courseGroup = tokenizer.nextToken();
				vacancies = Integer.parseInt(tokenizer.nextToken());
				
				// readLine
				line = br.readLine();
				
				// create patientItem object
				courseRecords studentItem = new courseRecords(courseCode,courseGroup, vacancies);

				// Add to linkedList
				courseList.add(studentItem);
				
				//edit vacancies
				courseRecords newRecord = new courseRecords(courseCode,courseGroup, vacancies-1);
				courseList.add(newRecord);
				pw2.println();
				pw2.print(newRecord);

				

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void dropCourse() {

	}

	public static void checkOrPrintCoursesRegistered() {

	}

	public static int checkVacanciesAvailable() {
		return -1;
	}

	public static void changeIndexNumber() {

	}

	public static void swopIndexNumber() {

	}
}
