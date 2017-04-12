import java.util.*;
import java.util.regex.Pattern;
import java.io.*;
public class Course implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String courseCode;
	private String school;
	private int indexPointer;
	private ArrayList<CourseIndex> cIndexList;
	private ArrayList<TimeSlot> lectureList;
	/**
	 * This method is the constructor for course class. It does nothing.
	 */
	public Course() {
	}
	/**
	 * This method is an overloading constructor with 1 parameter for course class. 
	 * It initalise the course code given by the user.
	 *
	 * @param  courseCode  the course code given by the user.
	 */
	public Course(String courseCode) {
		this.courseCode = courseCode;
	}
	/**
	 * This method is an overloading constructor with 4 parameter for course class.
	 * It initalises name, courseCode, school and an arrayList of timeslots.
	 * @param	name		the name of the course
	 * @param	courseCode	the courseCode of the course
	 * @param	school		the school of the course
	 * @param	lect		the list of lecture,lab and tutorial timing
	 */
	public Course(String name, String courseCode, String school, ArrayList<TimeSlot> lect) {
		this.name = name;
		this.courseCode = courseCode;
		this.school = school;
		this.lectureList = lect;
		this.cIndexList = new ArrayList<CourseIndex>();
	}
	/**
	 * This method returns the name of the course.
	 *
	 * @return      the name of the course
	 */

	public String getName() {
		return name;
	}
	/**
	 * This method initalise the name of the course.
	 * @param  name		the name of the course
	 */

	public void setName(String name) {
		this.name = name;
	}
	/**
	 * This method returns the course code.
	 *
	 * @return      the courseCode of the course
	 */
	public String getCourseCode() {
		return courseCode;
	}
	/**
	 * This method initalise the course code of the course.
	 *
	 * @param  courseCode  the courseCode of the course
	 */
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	/**
	 * This method retuns the school of the course
	 *
	 * @return      the school of the course
	 */
	public String getSchool() {
		return school;
	}
	/**
	 * This method initalise the school of the course.
	 *
	 * @param  school  the school of the course
	 */
	public void setSchool(String school) {
		this.school = school;
	}
	/**
	 * This method update the index of the course.
	 *
	 * @param  oldIndex		the index to be changed
	 * @param  newIndex		the index that is to be changed to
	 */
	public void updateIndex(int oldIndex, int newIndex) {//  String courseCode can be 2 args
		if (selectIndex(oldIndex)) {
			cIndexList.get(indexPointer).setIndex(newIndex);
			System.out.println("Index updated.");
		} else
			System.out.println("Update failed.");
	}
	/**
	 * This method updates the vacancy of the course.
	 *
	 * @param  index	the index of a course
	 * @param  vacancy	the number of vacancy in a course.
	 */
	public void updateVacancy(int index, int vacancy) {
		if (selectIndex(index)) {
			CourseIndex cIndex = cIndexList.get(indexPointer);
			int difference = vacancy - cIndex.getTotalVacancy();
			cIndex.setVacancy(cIndex.getVacancy()+difference);
			cIndex.setTotalVacancy(vacancy);
			save();
			System.out.println("Vacancy updated.");
		} else
			System.out.println("Update failed");
	}
	/**
	 * This method returns an arrayList of index numbers.
	 * @return      the arrayList of courseIndex
	 * @see         Image
	 */
	public ArrayList<CourseIndex> getcIndexList() {
		return cIndexList;
	}
	/**
	 * This method initalise courseIndex based on the index provided
	 * by the user.
	 *
	 * @param  index	the courseIndex of the course
	 * @return      the courseIndex of the course, if found
	 * @return		null, if courseIndex is not found
	 * @see         Image
	 */
	public CourseIndex getcIndex(int index) {
		CourseIndex cIn = null;
		for(int i = 0; i < cIndexList.size(); i++) {
			CourseIndex cIndex = cIndexList.get(i);
			if(cIndex.getIndex() == index)
				return cIndex;
		}
		return cIn;
	}
	/**
	 * This method decrements the method vacancy of a course.
	 *
	 * @param  index  the index of course to be decremented.
	 */
	public void minusFromIndex(int index) {
		if (selectIndex(index)) {
			cIndexList.get(indexPointer).decrementVacancy();
			System.out.println("Vacancy decremented.");
		} else
			System.out.println("Decrement failed.");
	}
	/**
	 * This method adds index to a course.
	 *
	 * @param  index  index of course to be added
	 * @return      the course returns the course
	 */
	public Course addToIndex(int index) {
		if (selectIndex(index)) {
			CourseIndex cind = cIndexList.get(indexPointer);
			cind.incrementVacancy();
			if(cind.getWaitListSize()>0) {
				String mNo = cind.getWaitListStudent();
				StudentCourse.addFromWaitList(mNo, courseCode, cind.getIndex());
				cind.decrementVacancy();
				save();
			}
			else
				System.out.println("Vacancy incremented.");
		} else
			System.out.println("Increment failed.");
		return this;
	}
	/**
	 * This method checks the vacancy of a course.
	 */
	public void checkVacancy()
	{
		Scanner input = new Scanner(System.in);
		System.out.print("Enter course index: ");
		int cIndex = input.nextInt();
		if(indexExist(cIndex))
			System.out.println("Vacancy for " + cIndex + " = " + retrieveVacancy(cIndex) + "/" + retrieveTotalVacancy(cIndex));
		else
			System.out.println("Index does not exist.");
	}
	/**
	 * This method retrieve vacancy of course.
	 *
	 * @param  index  the index to retrieve the number of vacancy.
	 * @return      the number of vacancies, if index is found
	 * @return		-1, if index is not found.
	 */
	public int retrieveVacancy(int index) {
		if (selectIndex(index))
			return cIndexList.get(indexPointer).getVacancy();
		else {
			System.out.println("Error when retrieving value.");
			return -1;
		}
	}
	/**
	 * This method returns the total number of vacancy of an index.
	 *
	 * @param  int  the index to retrieve the total number of vacancy
	 * @return      the total number of vacancy, if index is found
	 * @return		-1, if index is not found
	 */
	public int retrieveTotalVacancy(int index) {
		if (selectIndex(index))
			return cIndexList.get(indexPointer).getTotalVacancy();
		else {
			System.out.println("Error when retrieving value.");
			return -1;
		}
	}
	/**
	 * This method adds a new entry to the index of course created.
	 *
	 * @param	index		the index of the course
	 * @param	vacancy		the number of available vacancies
	 * @param	tVacancy	the total number of vacancies
	 * @param	gname		the name of the group
	 * @param	t			the timeslot of tutorials
	 * @param	l			the timeslot of labs
	 */
	public void AddtocIndexList(int index, int vacancy, int tVacancy, String gname, TimeSlot t, TimeSlot l) {
		this.cIndexList.add(new CourseIndex(index, vacancy, tVacancy, gname, t,l));
	}
	/**
	 * Sets an integer within a specific Course for use
	 * in other methods requiring the particular index of
	 * the course index list, cIndexList.
	 * @param int index
	 * @return a boolean, indicating success or failure.
	 * This prevents a method from attempting to access beyond 
	 * the scope of cIndexList.
	 */

	public boolean selectIndex(int index) {
		for (int i = 0; i < cIndexList.size(); i++) {
			if (cIndexList.get(i).indexExist(index)) {
				indexPointer = i;
				return true;
			}
		}
		System.out.println("Index not found.");
		return false;
	}
	/**
	 * Checks if the course exists with the specified coursecode.
	 * The method checks against the list produced by getCourseList()
	 * and returns true if the list contains a Course with the matching course code.
	 * The method will return false if otherwise.
	 * @param String courseCode
	 * @return boolean
	 */
	public static boolean courseExist(String courseCode) {
		List list = getCourseList();

		boolean breakout = false;
		try {
			if (list.size() != 0) {
				for (int i = 0; i < list.size(); i++) {
					Course course = (Course) list.get(i);
					if (course.getCourseCode().toString().equals(courseCode)) {
						breakout = true;
						break;
					}
				}
			}
		} catch (Exception e) {
		}
		return breakout;
	}
	/**
	 * This method returns the course.
	 *
	 * @param  courseCode  the course code of the course
	 * @return      the course, if course code is found
	 * @return		null, if course code is not found
	 */
	public static Course getCourse(String coursecode) {
		Course c = null;
		List courselist = getCourseList();
		for (int i = 0; i < courselist.size(); i++) {
			Course course = (Course) courselist.get(i);
			if (course.getCourseCode().equals(coursecode))
				return course;
		}
		return c;
	}
	/**
	 * This method adds a new course.
	 *
	 * @param  courseCode  the course code of the course
	 */
	public static void addCourse(String coursecode) {
		Scanner input = new Scanner(System.in);
		Course course = new Course();
		System.out.print("Course Name: ");
		String coursename = input.nextLine();
		System.out.print("School: ");
		String school = input.nextLine();
		List list = getCourseList();
		try {
			int choice = 0, addLect =0;
			ArrayList<TimeSlot> lectList = new ArrayList();
			TimeSlot lect = createTimeSlot("lecture");
			if(lect != null) {
				lectList.add(lect);
				while(addLect != 2) {
					System.out.println("Do you wish to add more lecture sessions to the course? 1-Yes 2-No");
					addLect = input.nextInt();
					if(addLect == 1)
					{
						lect = createTimeSlot("lecture");
						if(lect!= null)
							lectList.add(lect);
						else break;
					}
				}
				Course course1 = new Course(coursename, coursecode, school, lectList);
				while(choice != 2) {
					System.out.print("Do you wish to add index to the course? 1-Yes 2-No");
					choice = input.nextInt();
					switch(choice) {
					case 1:
						course1.addNewIndex();
						break;
					case 2:
						break;
					default:
					}		
				}
				list.add(course1);
				save(list);
				System.out.println("Course added successfully!");
			}
	
		} catch (Exception e) {
			System.out.println("Error occured. Course is not added.");
		}
	}
	/**
	 * This method adds a new index to the course created.
	 */
	public void addNewIndex()
	{
		Scanner input = new Scanner(System.in);
		TimeSlot tut = null, lab = null;
		System.out.print("Enter index no: ");
		int indexNo = input.nextInt();
		if(!indexExist(indexNo)) {
			System.out.print("Enter group name: ");
			String groupName = input.next();
			System.out.print("Enter number of vacancies: ");
			int vacancy = input.nextInt();
			System.out.print("Do you wish to add lab? 1-Yes 2-No");
			if(input.nextInt() == 1) {
				lab = createTimeSlot("lab");
				if(lab== null) 
					System.out.println("");
					return;
			}
			System.out.print("Do you wish to add tutorial? 1-Yes 2-No");
			if(input.nextInt() == 1) {
				tut = createTimeSlot("tutorial");
				if(tut == null) 
					return;
			}
			AddtocIndexList(indexNo, vacancy, vacancy, groupName, tut, lab);
		}
	}
	/**
	 * This method creates a new time slot which allows the user to enter
	 * and choose the day based on the classType.
	 *
	 * @param  classType  the type of the class.
	 * @return      the timeSlot
	 */
	public static TimeSlot createTimeSlot(String classType)
	{
		Scanner input = new Scanner(System.in);
		TimeSlot ts = null;
		try {
			System.out.print("Enter day for " + classType + " (1-Monday 5-Friday): ");
			int day = input.nextInt();
			if(day > 0 && day < 6)
			{
				Pattern p = Pattern.compile("^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$");
				System.out.print("Enter start time for " + classType + " (HH:MM): ");
				String start = input.next();
				System.out.print("Enter end time for " + classType + " (HH:MM): ");
				String end = input.next();
				if((p.matcher(start)).matches() && (p.matcher(end)).matches())
				{
					System.out.print("Enter venue for "+ classType +": ");
					String venue = input.next();
					ts = new TimeSlot(day, start, end, venue);
				}
				else
					System.out.println("Error occured. Wrong time format found.");
			}
			else
				System.out.println("Invalid input found. Only input 1-5.");
			return ts;
		}
		catch(InputMismatchException e) {
			System.out.println("Error occured. Please key in numerical value");
			return ts;
		}
	}
	/**
	 * This method edits the name of the course.
	 *
	 * @param  courseName	the name of the course
	 */

	public void editCourseName(String coursename) {
		try {
			setName(coursename);
			save();
		} catch (Exception e) {
			System.out.println("Error occurred. Name of course is not changed");
		}
	}

	/**
	 * This method edit the school which the course belongs to. 
	 *
	 * @param  school  the school  of which the course belongs to.
	 */
	public void editSchool(String school) {
		try {
			setSchool(school);
			save();
		} catch (Exception e) {
			System.out.println("Error occured. School of course is not changed.");
		}
	}
	/**
	 * This method edits the index of the course. 
	 *
	 * @param  oldIndex		the index to be changed
	 * @param  newIndex		the index to be changed to
	 */
	
	public void editIndex(int oldIndex, int newIndex) {
		try{
			for(int i = 0; i<cIndexList.size();i++) {
				CourseIndex cIndex = cIndexList.get(i);
				if(cIndex.getIndex() == oldIndex) {
					cIndex.setIndex(newIndex);
					StudentCourse.adminUpdateCourseIndex(courseCode, oldIndex, newIndex);
				}
			}
			save();
		}
		catch(Exception e) {
			System.out.println("Error occured. ");
		}
	}
	
	/**
	 * This method checks if a index number of a course exist. 
	 *
	 * @param  index  the index to be checked
	 * @return      true, if course is found
	 * @reutrn		false, if course is not found
	 */

	public boolean indexExist(int index) {
		if(cIndexList.size()>0) {
			for(int i=0; i<cIndexList.size(); i++) {
				CourseIndex cIndex=(CourseIndex)cIndexList.get(i);
				if(cIndex.getIndex()==(index))
					return true;
			}
			return false;
		}
		else return false;
	}
	/**
	 * This method gets the timeslots of lessons. 
	 *
	 * @param  courseIndex  the index to be taken
	 * @return      the list contain timeslots of the index
	 */
	public List getTimeSlots(int courseIndex) {
		List<TimeSlot> list = new ArrayList();
		for(int j = 0; j<lectureList.size(); j++) {
			list.add(lectureList.get(j));	
		}
		for(int i = 0; i < cIndexList.size(); i++) {
			CourseIndex cIndex = cIndexList.get(i);
			if(cIndex.getIndex() == courseIndex) {
				list.add(cIndex.getLab());
				list.add(cIndex.getTutorial());
			}
		}
		return list;
	}
	/**
	 * This method checks if there are any clashes of lessons when a student register
	 * for a course.
	 *
	 * @param  tsList  		the list of timeSlots of a courseIndex
	 * @param  courseIndex the courseIndex of timeslots be return
	 * @return      false, if there are not clashes
	 * @return		true, if there are clashes
	 */
	
	public boolean checkClash(List<TimeSlot> tsList, int courseIndex) {
		boolean clash = false;
		for(int j = 0; j < lectureList.size(); j++) {
			if(!clash) {
				clash = lectureList.get(j).hasClash(tsList);
			}
		}
		for(int i = 0; i < cIndexList.size(); i++) {
			CourseIndex cIndex = cIndexList.get(i);
			if(courseIndex == cIndex.getIndex()) {
				return cIndex.checkClash(tsList) || clash;
			}
		}
		return false;
	}
	/**
	 * This method adds a student to the waitList if there are no vacancies
	 * for a courseIndex.
	 *
	 * @param  courseIndex  the courseIndex which the students want to add
	 * @param  matricNo the matricNo of the student
	 */
	public void addToWaitList(int courseIndex, String matricNo) {
		for(int i = 0; i<cIndexList.size(); i++) {
			CourseIndex cIndex = cIndexList.get(i);
			if(cIndex.getIndex() == courseIndex) {
				cIndex.addWaitList(matricNo);
				save();
			}
		}
	}
	/**
	 * This method is to check if a student exist in the waitlist.
	 *
	 * @param  courseIndex	the index which the student want to add to
	 * @param  matricNo		the matricNo of the student
	 * @return      true, if the student exist in the waitlist
	 * @return		false, if the student does not exist in the waitlist.
	 */
	public boolean existInWaitList(int courseIndex, String matricNo)
	{
		for(int i = 0; i<cIndexList.size(); i++) {
			CourseIndex cIndex = cIndexList.get(i);
			if(cIndex.getIndex() == courseIndex) 
				return cIndex.existInWaitList(matricNo);
		}
		return false;
	}
	/**
	 * This method prints the indexes.
	 */
	public void printIndexes()
	{
		for(int i = 0; i < cIndexList.size(); i++) {
			CourseIndex cIndex = cIndexList.get(i);
			System.out.println( i+1 + "\t" + cIndex.getIndex() + "\t" + cIndex.getGroupName());
		}
	}
	/**
	 * This method prints a list of students.
	 */
	public void printStudents()
	{
		StudentCourse.printStudents(courseCode);
	}
	/**
	 * This method prints a list ordered by courseIndex.
	 *
	 * @param  courseIndex  the index of the course.
	 */
	public void printStudentsByIndex(int courseIndex)
	{
		if(indexExist(courseIndex)) {
			StudentCourse.printStudents(courseIndex);
		}
		else
			System.out.println("Index does not exist.");
	}
	/**
	 * This method returns the a list of courses.
	 * @return	a list of courses
	 */
	public static List getCourseList() {
		return getCourseList("courselist.dat");
	}
	/**
	 * This method returns a list of courses. 
	 *
	 *@return	A list of courses
	 */
	public static List getCourseList(String filename) {
		List list = null;
		try {
			list = FileIO.readInFile(filename);
		} catch (Exception e) {
		}
		if (list == null)
			list = new ArrayList();
		return list;
	}
	/**
	 * Save amendmends made to text file.
	 *
	 * @param  list  the list which contains the details
	 */
	public static void save(List list) {
		FileIO.writeToFile("courselist.dat", list);
	}
	/**
	 * Save amendments made.
	 */
	public void save()
	{
		List list = getCourseList();
		for(int i=0;i<list.size();i++)
		{
			Course c = (Course)list.get(i);
			if(c.getCourseCode().equals(courseCode))
			{
				list.set(i, this);
				save(list);
				break;
			}
		}
	}
}