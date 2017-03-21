import java.util.*;
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
		String courseCode;
		String courseGroup;
		int vacancies;

		String userCourseCode = null;
		String userCourseGroup = null;
		String matricNumber;
		int i;
		courseRecords courseItem;

		// read from courseRecords to check for vacancies

		studentRecords sr;
		StringTokenizer tokenizer;

		try {
			// File & BufferedReader
			fr = new FileReader(courseRecordFile);
			br = new BufferedReader(fr);

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

				// create patientItem object
				courseItem = new courseRecords(courseCode, courseGroup,
						vacancies);

				// Add to linkedList
				courseList.add(courseItem);

				// readLine
				line = br.readLine();

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));
			matricNumber=getMatricNo();
			// *******search for user's choice of courseCode
			System.out.println("Please key in courseCode:");
			try {
				// create reader to read from user input
				// read one line from user input
				userCourseCode = br.readLine();
				// print the format that records will be shown
				System.out
						.println("<CourseCode> | <CourseGroup> | <Vacancies>");

				// Loop through all the records in the LinkedList
				for (i = 0; i < courseList.size(); i++) {
					// if the records is the same as the input from user
					// (Hint: use contains() in String class to check whether
					// search word is found in the records

					if (courseList.get(i).getCourseCode()
							.equalsIgnoreCase(userCourseCode)) {
						// print out the information of the patient
						System.out.println(courseList.get(i));

					}
				}

			} catch (Exception e) {
				System.out.println("CourseCode error!");
			}
			
			System.out.println("Please key in courseGroup:");
			try {
				// create reader to read from user input
				// read one line from user input
				userCourseGroup = br.readLine();

				// Loop through all the records in the LinkedList
				for (i = 0; i < courseList.size(); i++) {
					// if the records is the same as the input from user
					// (Hint: use contains() in String class to check
					// whether
					// search word is found in the records
					if (courseList.get(i).getCourseGroup()
							.equalsIgnoreCase(userCourseGroup)) {

						vacancies = courseList.get(i).getVacancies();
						if (vacancies >= 1) {

							// *****WRITE TO STUDENTRECORDS

							try {

								FileWriter fw1 = new FileWriter(
										studentRecordFile, append);
								BufferedWriter bw1 = new BufferedWriter(fw1);
								PrintWriter pw1 = new PrintWriter(bw1);

								// add this item object into arraylist
								studentRecords newRecord = new studentRecords(
										matricNumber.toUpperCase(),
										userCourseCode.toUpperCase(),
										userCourseGroup.toUpperCase());
								studList.add(newRecord);
								// printing empty to start on a new line
								pw1.println(newRecord);

								pw1.close();
								// close reader
								// br.close();
								

								// *******DELETE RECORD FROM COURSE RECORD

								 FileWriter fw2 = new FileWriter(courseRecordFile);
						            BufferedWriter bw2 = new BufferedWriter(fw2);
						            PrintWriter pw2 = new PrintWriter(bw2);
						            
						            for(i=0; i< courseList.size(); i++)
						             {
						                 if(courseList.get(i).getCourseCode().equalsIgnoreCase(userCourseCode) && courseList.get(i).getCourseGroup().equalsIgnoreCase(userCourseGroup))
						                 {
						                     courseList.remove(i);                
						                 }
						               //  pw2.println(courseList.get(i));
						             }
						              pw2.close();
								

								// ****** UPDATE VACANCY OF COURSERECORD

								FileWriter fw3 = new FileWriter(
										courseRecordFile, append);
								BufferedWriter bw3 = new BufferedWriter(fw3);
								PrintWriter pw3 = new PrintWriter(bw3);

								// add this item object into arraylist
								vacancies = vacancies - 1;
								courseRecords courseItem1 = new courseRecords(
										userCourseCode.toUpperCase(),
										userCourseGroup.toUpperCase(),
										vacancies);
								courseList.add(courseItem1);
								// printing empty to start on a new line
								pw3.println(courseItem1);

								System.out.println("Record has been recorded");
								pw3.close();
								// close reader
								// br.close();
								
								for(i=0;i<courseList.size();i++)
									System.out.println(courseList.get(i));

							} catch (FileNotFoundException e) {
								System.out
										.println("The file "
												+ studentRecordFile
												+ " was not found.");
							} catch (IOException e) {
								System.out.println("Reading error!");
							} finally {
								if (fr != null) {
									try {
										fr.close();
									} catch (IOException e) {
										System.out
												.println("Error closing file!");
									}
								}
							}

						}

						else
							System.out.println(courseList.get(i)
									.getCourseCode()
									+ ","
									+ courseList.get(i).getCourseGroup()
									+ "has no vacancies");
					}
				}
				// COURSECODE NOT FOUND ERROR HERE

			} catch (Exception e) {
				System.out.println("CourseCode error!");
			}
		} catch (Exception e) {
			System.out.println("CourseCode error!");
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
