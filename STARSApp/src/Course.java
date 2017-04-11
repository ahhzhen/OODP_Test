import java.util.*;
import java.io.*;
public class Course implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String courseCode;
	private String school;
	private int indexPointer;
	private ArrayList<CourseIndex> cIndexList;
	// private static ArrayList<Course> courseList; //StudentCourse will hold
	// the courseList
	private ArrayList<TimeSlot> lectureList;

	public Course() {
	}

	public Course(String courseCode) {
		this.courseCode = courseCode;
	}

	public Course(String name, String courseCode, String school, ArrayList<TimeSlot> lect) {
		this.name = name;
		this.courseCode = courseCode;
		this.school = school;
		this.lectureList = lect;
		this.cIndexList = new ArrayList<CourseIndex>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public void updateIndex(int oldIndex, int newIndex) {//  String courseCode can be 2 args
		if (selectIndex(oldIndex)) {
			cIndexList.get(indexPointer).setIndex(newIndex);
			System.out.println("Index updated.");
		} else
			System.out.println("Update failed.");
	}

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

	public ArrayList<CourseIndex> getcIndexList() {
		return cIndexList;
	}
	
	public CourseIndex getcIndex(int index) {
		CourseIndex cIn = null;
		for(int i = 0; i < cIndexList.size(); i++) {
			CourseIndex cIndex = cIndexList.get(i);
			if(cIndex.getIndex() == index)
				return cIndex;
		}
		return cIn;
	}

	public void minusFromIndex(int index) {
		if (selectIndex(index)) {
			cIndexList.get(indexPointer).decrementVacancy();
			System.out.println("Vacancy decremented.");
		} else
			System.out.println("Decrement failed.");
	}

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
	
	public int retrieveVacancy(int index) {
		if (selectIndex(index))
			return cIndexList.get(indexPointer).getVacancy();
		else {
			System.out.println("Error when retrieving value.");
			return -1;
		}
	}
	
	public int retrieveTotalVacancy(int index) {
		if (selectIndex(index))
			return cIndexList.get(indexPointer).getTotalVacancy();
		else {
			System.out.println("Error when retrieving value.");
			return -1;
		}
	}

	public void AddtocIndexList(int index, int vacancy, int tVacancy, String gname, TimeSlot t, TimeSlot l) {
		this.cIndexList.add(new CourseIndex(index, vacancy, tVacancy, gname, t,l));
	}

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
			lectList.add(createTimeSlot("lecture"));
			while(addLect != 2) {
				System.out.println("Do you wish to add more lecture sessions to the course? 1-Yes 2-No");
				addLect = input.nextInt();
				if(addLect == 1)
					lectList.add(createTimeSlot("lecture"));
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
		} catch (Exception e) {
			System.out.println("Error occured. Course is not added.");
		}
	}
	
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
			if(input.nextInt() == 1)
				lab = createTimeSlot("lab");
			System.out.print("Do you wish to add tutorial? 1-Yes 2-No");
			if(input.nextInt() == 1)
				lab = createTimeSlot("tutorial");
			AddtocIndexList(indexNo, vacancy, vacancy, groupName, tut, lab);
		}
	}
	
	public static TimeSlot createTimeSlot(String classType)
	{
		Scanner input = new Scanner(System.in);
		TimeSlot ts = null;
		try {
			System.out.print("Enter day for " + classType + " (1-Monday 5-Friday): ");
			int day = input.nextInt();
			System.out.print("Enter start time for " + classType + " (HH:MM): ");
			String start = input.next();
			System.out.print("Enter end time for " + classType + " (HH:MM): ");
			String end = input.next();
			System.out.print("Enter venue for "+ classType +": ");
			String venue = input.next();
			ts = new TimeSlot(day, start, end, venue);
			return ts;
		}
		catch(Exception e){return ts;}
	}

	public void editCourseName(String coursename, String courseCode) {
		List list = getCourseList();
		try {
			if (list.size() != 0) {
				for (int i = 0; i < list.size(); i++) {
					Course course = (Course) list.get(i);
					if (course.getCourseCode().toString() == courseCode) {
						course.setName(coursename);
						break;
					}
				}
			}
		} catch (Exception e) {
		}
		save(list);
	}

	public void editSchool(String courseCode, String school) {
		List list = getCourseList();
		// List<Course> courseList = new ArrayList<Course>();
		try {
			if (list.size() != 0) {
				for (int i = 0; i < list.size(); i++) {
					Course course = (Course) list.get(i);
					if (course.getCourseCode().toString() == courseCode) {
						course.setSchool(school);
						break;
					}
				}
			}
		} catch (Exception e) {
		}
		save(list);
	}
	
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
		catch(Exception e)
		{}
	}

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
	
	public void addToWaitList(int courseIndex, String matricNo) {
		for(int i = 0; i<cIndexList.size(); i++) {
			CourseIndex cIndex = cIndexList.get(i);
			if(cIndex.getIndex() == courseIndex) {
				cIndex.addWaitList(matricNo);
				save();
			}
		}
	}
	
	public boolean existInWaitList(int courseIndex, String matricNo)
	{
		for(int i = 0; i<cIndexList.size(); i++) {
			CourseIndex cIndex = cIndexList.get(i);
			if(cIndex.getIndex() == courseIndex) 
				return cIndex.existInWaitList(matricNo);
		}
		return false;
	}
	
	public void printIndexes()
	{
		for(int i = 0; i < cIndexList.size(); i++) {
			CourseIndex cIndex = cIndexList.get(i);
			System.out.println( i+1 + "\t" + cIndex.getIndex() + "\t" + cIndex.getGroupName());
		}
	}
	
	public void printStudents()
	{
		StudentCourse.printStudents(courseCode);
	}
	
	public void printStudentsByIndex(int courseIndex)
	{
		Scanner input = new Scanner(System.in);
		System.out.print("Enter course index: ");
		int cIndex = input.nextInt();
		if(indexExist(cIndex)) {
			StudentCourse.printStudents(courseIndex);
		}
	}

	public static List getCourseList() {
		return getCourseList("courselist.dat");
	}

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

	public static void save(List list) {
		FileIO.writeToFile("courselist.dat", list);
	}
	
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