import java.util.List;
import java.util.Scanner;
import java.io.Serializable;
import java.util.ArrayList;

public class StudentCourse implements Serializable {

	private static final long serialVersionUID = 1L;
	private String stud;
	private String crse;
	private int indx;

	// constructors
	StudentCourse() {
	}

	StudentCourse(String s, String c, int i) {
		stud = s;
		crse = c;
		indx = i;
	}

	// getter and setters
	public String getMatricNumber() {
		return stud;
	}

	public void setStudent(String s) {
		this.stud = s;
	}

	public String getCourseCode() {
		return crse;
	}

	public void setCourseCode(String c) {
		this.crse = c;
	}

	public int getCourseIndex() {
		return indx;
	}

	public void setCourseIndex(int i) {
		this.indx = i;
	}

	// other operations

	public static boolean courseExist(String courseCode) {
		return Course.courseExist(courseCode);
	}
	
	public static Course getCourse(String courseCode) {
		return Course.getCourse(courseCode);
	}

	public static boolean enrolled(String matricNo, String courseCode) {
		List list = getRegisteredList();
		for (int i = 0; i < list.size(); i++) {
			StudentCourse sc = (StudentCourse) list.get(i);
			if (sc.getMatricNumber().equals(matricNo) && sc.getCourseCode().equals(courseCode))
				return true;
		}
		return false;
	}

	public static boolean enrolled(String matricNo, String courseCode, int courseIndex) {
		List list = getRegisteredList();
		for (int i = 0; i < list.size(); i++) {
			StudentCourse sc = (StudentCourse) list.get(i);
			if (sc.getMatricNumber().equals(matricNo) && sc.getCourseCode().equals(courseCode)
					&& sc.getCourseIndex() == courseIndex)
				return true;
		}
		return false;
	}

	public static void registerStudent(String matricNo) {
		if (matricNo != null) {

			Scanner input = new Scanner(System.in);
			System.out.print("Enter the course code: ");
			String courseCode = input.nextLine();
			if (courseExist(courseCode))// check if course Exist
			{
				if (!enrolled(matricNo, courseCode)) 
				{
					System.out.print("Enter the course index: ");
					int courseIndex = input.nextInt();
					Course course = getCourse(courseCode);
					if(course.indexExist(courseIndex))// check if index exist
					{	
						//check for vacancies
						if(course.retrieveVacancy(courseIndex) > 0)
						{
							//checkForClash
							List studentRegisteredList = getCoursesRegistered(matricNo);
							List<TimeSlot> tsList = getTimeSlots(studentRegisteredList);
							if(!course.checkClash(tsList, courseIndex))
							{
								List list = getRegisteredList();
								try {
									StudentCourse sc = new StudentCourse(matricNo, courseCode, courseIndex);
									list.add(sc);
									save(list);
									System.out.println("Course registered successfully!");
								} catch (Exception e) {
									System.out.println("Error occured. Course not registered.");
								}
							}
						}
					}
				} else
					System.out.println("Course has been registered previously.");
			}
		}
	}

	public static List getCoursesRegistered(String matricNumber) {
		List list = getRegisteredList();
		List<StudentCourse> courseList = new ArrayList<StudentCourse>();

		try {
			if (list.size() != 0) {
				for (int i = 0; i < list.size(); i++) {
					StudentCourse studC = (StudentCourse) list.get(i);
					if (studC.getMatricNumber().equals(matricNumber))
						courseList.add(studC);
				}
			} else
				System.out.println("No courses have been registered!");
		} catch (Exception e) {
		}
		return courseList;
	}

	public static void unregisterStudent(String matricNumber) {
		List<StudentCourse> list = getCoursesRegistered(matricNumber);
		String courseCode;
		int courseIndex;

		if (list.size() == 0) {
			System.out.println("No Courses registered");
		} else {
			// print list of courses registered
			System.out.println("Courses registered:");
			System.out.println("------------------------------------------");
			for (int i = 0; i < list.size(); i++) {
				System.out.println("CourseCode: " + list.get(i).getCourseCode());
				System.out.println("CourseIndex: " + list.get(i).getCourseIndex());
				System.out.println("------------------------------------------");
			}

			Scanner scannerInput = new Scanner(System.in);
			System.out.println("Please enter course code you wish to drop:");
			courseCode = scannerInput.nextLine();
			System.out.println("Please enter course index you wish to drop:");
			courseIndex = scannerInput.nextInt();
			if (removeStudentCourseEntry(matricNumber, courseCode, courseIndex)) // statement is true when entry has been removed from list
				System.out.println("Course " + courseCode + "," + courseIndex + " has been dropped.");
			else
				System.out.println("Something went wrong ):");
		}
	}

	public static boolean removeStudentCourseEntry(String matricNumber, String courseCode, int courseIndex) {
		List<StudentCourse> list = getRegisteredList();
		try {
			for (int i = 0; i < list.size(); i++) {
				StudentCourse studC = (StudentCourse) list.get(i);
				if (studC.getMatricNumber().equals(matricNumber) && studC.getCourseCode().equals(courseCode)
						&& studC.getCourseIndex() == courseIndex) {
					list.remove(studC);
					save(list);
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	public static void checkVacancy()
	{
		String courseCode;
		int courseIndex;
		Scanner input = new Scanner(System.in);
		System.out.print("Enter course code: ");
		courseCode = input.nextLine();
		System.out.print("Enter course index: ");
		courseIndex = input.nextInt();
		
		if(Course.courseExist(courseCode))
		{
			Course c = Course.getCourse(courseCode);
			if(c.indexExist(courseIndex))
				System.out.println("Vacancy for index " + courseIndex + ": " + c.retrieveVacancy(courseIndex));
		}
	}
	
	public static void changeCourseIndex(String matricNumber) {
		List registeredCourselist = getCoursesRegistered(matricNumber);

		String courseCode;
		int newIndex, oldIndex;
		Scanner scanInput = new Scanner(System.in);

		System.out.print("Enter course code:");
		courseCode = scanInput.nextLine();
		System.out.print("Enter old course index:");
		oldIndex = scanInput.nextInt();
		System.out.print("Enter new course index:");
		newIndex = scanInput.nextInt();

		// check if course and courseIndex exists
		if(courseExist(courseCode))
		{
			Course c = getCourse(courseCode);
			if(c.indexExist(oldIndex) && c.indexExist(newIndex)){ 
				List tsList = getTimeSlots(registeredCourselist, oldIndex);
				if(!c.checkClash(tsList, newIndex))//check if new index clashes with registered courses
				{
					//check vacancy for newIndex
					if(c.retrieveVacancy(newIndex)>0)
					{
						List list = getRegisteredList();
						for (int i = 0; i < list.size(); i++) {
							StudentCourse studC = (StudentCourse) registeredCourselist.get(i);
							if (studC.getCourseCode().equals(courseCode) && studC.getCourseIndex() == oldIndex 
									&& studC.getMatricNumber().equals(matricNumber)) {
								studC.setCourseIndex(newIndex);
								c.addToIndex(oldIndex); //increase vacancy for old index
								c.minusFromIndex(newIndex); //decrease vacancy for new index
								System.out.println("Index for course " + courseCode + " has been changed from " + oldIndex + " to "
										+ newIndex + " .");
							}
						save(list);
						}
					}
					else
						System.out.println("There are no vacancies for index " + newIndex);
				}
				else
					System.out.println("The new index have clashes with your current registered courses!");
			}
			else
			System.out.println("Error in course index!");
		}
		else
		System.out.println("Course code/course index does not exist.");
	}

	public static void adminUpdateCourseIndex(String courseCode, int oldIndex, int newIndex) {
		List list = getRegisteredList();
		int found = 0;

		for (int i = 0; i < list.size(); i++) {
			StudentCourse studC = (StudentCourse) list.get(i);
			if (studC.getCourseCode() == courseCode && studC.getCourseIndex() == oldIndex) {
				found++;
				studC.setCourseIndex(newIndex);
			}
		}
		save(list);
		// if(found==0)
	}

	public static void swapCourse(String matricNumber) {
		String courseCode, studBMatric, password;
		int swopIndex, oldIndex;
		Scanner scanInput = new Scanner(System.in);

		System.out.println("Enter course code:");
		courseCode = scanInput.nextLine();
		System.out.println("Enter index:");
		oldIndex = scanInput.nextInt();

		if (enrolled(matricNumber, courseCode, oldIndex))// check if current student have registered for the old index
		{
			System.out.println("Enter student matriculation number to swap with:");
			studBMatric = scanInput.nextLine();
			System.out.println("Enter password of student:");
			password = scanInput.nextLine();
			System.out.println("Enter index number to swap with:");
			swopIndex = scanInput.nextInt();
			if (enrolled(studBMatric, courseCode, swopIndex))// check if studentB have registered for the swop index
			{
				//check for clash
				List studAList = getTimeSlots(getCoursesRegistered(matricNumber), oldIndex);
				List studBList = getTimeSlots(getCoursesRegistered(matricNumber), swopIndex);
				Course c = Course.getCourse(courseCode);
				if(!c.checkClash(studAList, swopIndex) && !c.checkClash(studBList, oldIndex))
				{
					Student studB = Student.getStudentByMatric(studBMatric);// getStudentObject
					if (studB.getPassword().equals(Integer.toString(password.hashCode()))) {// check if password matches
						List<StudentCourse> list = getRegisteredList();// get registered list
						for (int i = 0; i < list.size(); i++) {
							StudentCourse studC = (StudentCourse) list.get(i);
							if (studC.getMatricNumber().equals(matricNumber) && studC.getCourseCode().equals(courseCode)
									&& studC.getCourseIndex() == oldIndex)
								studC.setCourseIndex(swopIndex);
							if (studC.getMatricNumber().equals(studBMatric) && studC.getCourseCode().equals(courseCode)
									&& studC.getCourseIndex() == swopIndex)
								studC.setCourseIndex(oldIndex);
						}
						save(list);
						System.out.println("Indexes have been swapped successfully!");
					}
				}
			}
		}
	}
	
	public static void printStudents(int courseIndex)
	{
		int found = 0;
		List list = getRegisteredList();
		for(int i = 0; i < list.size(); i++)
		{
			StudentCourse sc = (StudentCourse)list.get(i);
			if(sc.getCourseCode().equals(Integer.toString(courseIndex)))
			{
				found++;
				if(found ==1)
					System.out.println("Name\tGender\tNationality");
				Student stud = Student.getStudentByMatric(sc.getMatricNumber());
				System.out.println(stud.getName() + "\t" + stud.getGender() + "\t" + stud.getNationality());
			}
		}
		if (found == 0)
			System.out.println("No student have registered for the course");
	}

	public static void printStudents(String courseCode)
	{
		int found = 0;
		List list = getRegisteredList();
		for(int i = 0; i < list.size(); i++)
		{
			StudentCourse sc = (StudentCourse)list.get(i);
			if(sc.getCourseCode().equals(courseCode))
			{
				found++;
				if(found ==1)
					System.out.println("Name\tGender\tNationality");
				Student stud = Student.getStudentByMatric(sc.getMatricNumber());
				System.out.println(stud.getName() + "\t" + stud.getGender() + "\t" + stud.getNationality());
			}
		}
		if (found == 0)
			System.out.println("No student have registered for the course");
	}
	
	public static List getTimeSlots(List<StudentCourse> studentList)
	{
		List<TimeSlot> consolidatedList = new ArrayList();
		for(int i = 0; i < studentList.size(); i++)
		{
			StudentCourse sc = studentList.get(i);
			Course c = Course.getCourse(sc.getCourseCode());
			int courseIndex = (sc.getCourseIndex());
			List timeList = c.getTimeSlots(courseIndex);
			for(int j = 0; j<timeList.size();j++)
			{
				consolidatedList.add((TimeSlot)timeList.get(i));
			}
		}
		return consolidatedList;
	}
	
	public static List getTimeSlots(List<StudentCourse> studentList, int index)
	{
		List<TimeSlot> consolidatedList = new ArrayList();
		for(int i = 0; i < studentList.size(); i++)
		{
			StudentCourse sc = studentList.get(i);
			if(sc.getCourseIndex()==index)
			{
				studentList.remove(sc);
				return getTimeSlots(studentList);
			}
		}
		return consolidatedList;
	}

	public static List getRegisteredList() {
		return getRegisteredList("testfile.dat");
	}
	
	public static List getRegisteredList(String filename) {
		List list = null;
		try {
			list = FileIO.readInFile(filename);
		} catch (Exception e) {
		}
		if (list == null)
			list = new ArrayList();
		return list;
	}

	public static void save(List list) {
		FileIO.writeToFile("studentRecords.dat", list);
	}

}