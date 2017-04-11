import java.util.List;
import java.util.Scanner;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Collections;

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
			String courseCode = input.next();
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
								if(addStudentCourseEntry(matricNo, courseCode, courseIndex))
								{
									Course c = Course.getCourse(courseCode);
									c.minusFromIndex(courseIndex);
									c.save();
									System.out.println("Course registered successfully!");
								}
								else
									System.out.println("Error occured. Course not registered.");
							}
						}
						else
						{
							System.out.println("No available vacancies for index " + courseIndex);
							if(!course.existInWaitList(courseIndex,matricNo))
							{
								course.addToWaitList(courseIndex, matricNo);
								System.out.println("You have been added onto the waitlist!");
							}
							else
								System.out.println("You are on the waitlist already");
						}
					}
				} else
					System.out.println("Course has been registered previously.");
			}
			else
				System.out.println("Course code does not exist.");
		}
	}

	public static boolean addStudentCourseEntry(String matricNo, String courseCode, int courseIndex) {
		List list = getRegisteredList();
		try {
			StudentCourse sc = new StudentCourse(matricNo, courseCode, courseIndex);
			list.add(sc);
			save(list);
			return true;
		}
		catch(Exception e)
		{return false;}
	}
	
	public static void addFromWaitList(String matricNo, String courseCode, int courseIndex) {
		if(addStudentCourseEntry(matricNo, courseCode, courseIndex))
		{
			//email student
			String studentId="ZCHEN035";
			String mailTo = studentId +"@e.ntu.edu.sg";
			String password="cz2002oodp";
			String mailFrom = "oodp2002@gmail.com";
			String mailSubject = "Course Registered";
			String mailText = "Dear " + matricNo + ",\nYou have been successfully registered to " + courseCode + " - " + courseIndex + ". You may login to STARS to see.";
			EmailTest.send(mailFrom,password,mailTo,mailSubject,mailText);
		}
		else
			System.out.println("Error occured, student not registered to course");
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

			Scanner input = new Scanner(System.in);
			System.out.println("Please enter course code you wish to drop:");
			courseCode = input.nextLine();
			System.out.println("Please enter course index you wish to drop:");
			courseIndex = input.nextInt();
			if (removeStudentCourseEntry(matricNumber, courseCode, courseIndex)) // statement is true when entry has been removed from list
				System.out.println("Course " + courseCode + "," + courseIndex + " has been dropped.");
			else
				System.out.println("Invalid course code/course index.");
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
					Course c = Course.getCourse(courseCode);
					c.addToIndex(courseIndex);
					c.save();
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	public static void checkVacancy() {//trycatch
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
		Scanner input = new Scanner(System.in);

		System.out.print("Enter course code:");
		courseCode = input.nextLine();
		System.out.print("Enter old course index:");
		oldIndex = input.nextInt();
		System.out.print("Enter new course index:");
		newIndex = input.nextInt();

		// check if course and courseIndex exists
		if(courseExist(courseCode))
		{
			Course c = getCourse(courseCode);
			//check if registered for oldIndex
			if(c.indexExist(oldIndex) && c.indexExist(newIndex)){ 
				if(enrolled(matricNumber, courseCode, oldIndex)){
					List tsList = getTimeSlots(registeredCourselist, oldIndex);
					if(!c.checkClash(tsList, newIndex))//check if new index clashes with registered courses
					{
						//check vacancy for newIndex
						if(c.retrieveVacancy(newIndex)>0)
						{
							List list = getRegisteredList();
							for (int i = 0; i < list.size(); i++) {
								StudentCourse studC = (StudentCourse) list.get(i);
								if (studC.getCourseCode().equals(courseCode) && studC.getCourseIndex() == oldIndex 
										&& studC.getMatricNumber().equals(matricNumber)) {
									try{
									studC.setCourseIndex(newIndex);
									c.minusFromIndex(newIndex); //decrease vacancy for new index
									c = c.addToIndex(oldIndex); //increase vacancy for old index
									c.save();
									System.out.println("Index for course " + courseCode + " has been changed from " + oldIndex + " to "
											+ newIndex + " .");									
									}
									catch(Exception e)
									{
										System.out.println("Error occued. ):");
									}
								}
							save(list);
							}
						}
						else
							System.out.println("There are no vacancies for index " + newIndex);
					}
					else
						System.out.println("The new index have clashes with your current registered courses!");
				}else
					System.out.println("You have not registered for index " + oldIndex + " before!");
			}
			else
			System.out.println("Error in course index!");
		}
		else
		System.out.println("Course code/course index does not exist.");
	}

	public static void adminUpdateCourseIndex(String courseCode, int oldIndex, int newIndex) { //trycatch
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
		Scanner input = new Scanner(System.in);

		System.out.println("Enter course code:");
		courseCode = input.nextLine();
		System.out.println("Enter index:");
		oldIndex = input.nextInt();

		if (enrolled(matricNumber, courseCode, oldIndex))// check if current student have registered for the old index
		{
			System.out.println("Enter student matriculation number to swap with:");
			studBMatric = input.nextLine();
			System.out.println("Enter password of student:");
			password = input.nextLine();
			System.out.println("Enter index number to swap with:");
			swopIndex = input.nextInt();
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
	
	public static void printTimeTable(String matricNumber) {
		List list = getCoursesRegistered(matricNumber);
		List tsList = getTimeSlots(list);
		List cInfoList = getCourseInfoListWithTimeSlots(list);
		sortTimeSlots(tsList, cInfoList);
		DayOfWeek d = DayOfWeek.SUNDAY;
		//Collections.sort(tsList);
		System.out.println("TIMETABLE");
		for(int i = 0; i<tsList.size();i++)
		{
			TimeSlot ts = (TimeSlot)tsList.get(i);
			StudentCourse sc = (StudentCourse)cInfoList.get(i);
			if(ts.getDay().getValue()!=d.getValue())
			{
				d = ts.getDay();
				System.out.println("");
				System.out.println("-----------------------------------------");
				System.out.println(d.toString());
				System.out.println("-----------------------------------------");
			}
			System.out.println(sc.getCourseCode() + "\t" + sc.getCourseIndex() + "\t" + ts.getStart().toString() + " - " + ts.getEnd().toString() +"\t" + ts.getVenue());
		}
	}
	
	public static void sortTimeSlots(List<Comparable> tsList, List<StudentCourse> cInfoList) {
		int min;
		Comparable temp;
		StudentCourse scTemp;
		for (int index = 0; index < tsList.size()-1; index++) {
			min = index;
			for (int scan = index+1; scan < tsList.size(); scan++)
				if (tsList.get(scan).compareTo(tsList.get(min)) > 0)
					min = scan;
			// Swap the values
			temp = tsList.get(min);
			tsList.set(min, tsList.get(index));
			tsList.set(index,temp);
			scTemp = cInfoList.get(min);
			cInfoList.set(min, cInfoList.get(index));
			cInfoList.set(index,scTemp);
		}
	}
	
	public static void printStudents(int courseIndex) { //errormessage
		int found = 0;
		List list = getRegisteredList();
		for(int i = 0; i < list.size(); i++)
		{
			StudentCourse sc = (StudentCourse)list.get(i);
			if(sc.getCourseIndex()==courseIndex)
			{
				found++;
				if(found ==1)
					System.out.println("Name\t\tGender\tNationality");
				Student stud = Student.getStudentByMatric(sc.getMatricNumber());
				System.out.println(stud.getName() + "\t" + stud.getGender() + "\t" + stud.getNationality());
			}
		}
		if (found == 0)
			System.out.println("No student have registered for the course");
	}

	public static void printStudents(String courseCode) { //errormessage
		int found = 0;
		List list = getRegisteredList();
		for(int i = 0; i < list.size(); i++)
		{
			StudentCourse sc = (StudentCourse)list.get(i);
			if(sc.getCourseCode().equals(courseCode))
			{
				found++;
				if(found ==1)
					System.out.println("Name\t\tGender\tNationality");
				Student stud = Student.getStudentByMatric(sc.getMatricNumber());
				System.out.println(stud.getName() + "\t" + stud.getGender() + "\t" + stud.getNationality());
			}
		}
		if (found == 0)
			System.out.println("No student have registered for the course");
	}
	
	public static List getCourseInfoListWithTimeSlots(List<StudentCourse> studentList) { //errormessage
		List<StudentCourse> consolidatedList = new ArrayList();
		for(int i = 0; i < studentList.size(); i++)
		{
			StudentCourse sc = studentList.get(i);
			Course c = Course.getCourse(sc.getCourseCode());
			int courseIndex = (sc.getCourseIndex());
			List timeList = c.getTimeSlots(courseIndex);
			for(int j = 0; j<timeList.size();j++)
			{
				consolidatedList.add((StudentCourse)studentList.get(i));
			}
		}
		return consolidatedList;
	}
	
	public static List getTimeSlots(List<StudentCourse> studentList) { //errormessage
		List<TimeSlot> consolidatedList = new ArrayList();
		for(int i = 0; i < studentList.size(); i++) {
			StudentCourse sc = studentList.get(i);
			Course c = Course.getCourse(sc.getCourseCode());
			int courseIndex = (sc.getCourseIndex());
			List timeList = c.getTimeSlots(courseIndex);
			for(int j = 0; j<timeList.size();j++) {
				consolidatedList.add((TimeSlot)timeList.get(j));
			}
		}
		return consolidatedList;
	}
	
	public static List getTimeSlots(List<StudentCourse> studentList, int index) {
		List<TimeSlot> consolidatedList = new ArrayList();
		for(int i = 0; i < studentList.size(); i++) {
			StudentCourse sc = studentList.get(i);
			if(sc.getCourseIndex()==index) {
				studentList.remove(sc);
				return getTimeSlots(studentList);
			}
		}
		return consolidatedList;
	}

	public static List getRegisteredList() {
		return getRegisteredList("studentRecords.dat");
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