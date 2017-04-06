import java.util.*;
import java.util.zip.InflaterOutputStream;

public class AdminCourse {
	private String courseCode;
	private String school;
	private String coursename;

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

	public String getCoursename() {
		return coursename;
	}

	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}

	public AdminCourse() {

	}

	public void checkVacancies() {

	}

	public void printStudentListByIndex() {

	}

	public void printStudentListByCourse() {

	}

	public static List getCoursedList() {
		return getCoursedList("courselist.dat");
	}

	public static List getCoursedList(String filename) {
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

	public boolean courseExist(String coursecode) {
		Course course = new Course();
		if (course.courseExist(coursecode)) {
			return true;
		} else {
			return false;
		}
	}


	public void addCourse() {
		Scanner sc = new Scanner(System.in);
		Course course = new Course();
		System.out.println("Enter Course Code: ");
		String coursecode = sc.nextLine();
		if (!courseExist(coursecode)) {
			System.out.println("Course Name: ");
			String coursename = sc.nextLine();
			System.out.println("School: ");
			String school = sc.nextLine();
			course.addCourse(coursename, coursecode, school);
		} else {
			System.out.println("Course Code exist!");
		}
	}

	public void updateCourse() {
		Scanner sc = new Scanner(System.in);
		Course course = new Course();
		System.out.println("Enter Course Code: ");
		String coursecode = sc.nextLine();
		if (courseExist(coursecode)) {

			List list = getCoursedList();
			// List<AdminCourse> courseList = new ArrayList<AdminCourse>();

			try {
				if (list.size() != 0) {
					for (int i = 0; i < list.size(); i++) {
						AdminCourse adminc = (AdminCourse) list.get(i);
						if (adminc.getCourseCode().equals(coursecode)) {
							System.out.println("1. Edit Course Name");
							System.out.println("2. Edit School");
							System.out.println("3. Edit Course Index");
							int input = sc.nextInt();
							switch (input) {
							case 1:
								System.out.println("Current Course Name is : " + adminc.getCoursename());
								System.out.println("Enter new course name (Enter -1 to cancel) :");
								String nameinput = sc.nextLine();
								if (nameinput == "-1") {
									break;
								} else {
									course.editCourseName(nameinput, courseCode);

								}
								break;
							case 2:
								System.out.println("Current School is : " + adminc.getCoursename());
								System.out.println("Enter new School (Enter -1 to cancel) :");
								String schoolinput = sc.nextLine();
								if (schoolinput == "-1") {
									break;
								} else {
									course.editSchool(courseCode, schoolinput);
								}

								break;
							default:
								System.out.println("Invalid Input");

							}
						}

					}
				} else
					System.out.println("No courses have been registered!");
			} catch (Exception e) {
			}
		} else {
			System.out.println("Course Code does not exist!");
		}
	}

	public void printindex() {// getting index through course does not belong
								// here
		Scanner sc = new Scanner(System.in);
		//Course course = new Course();
		System.out.println("Enter Course Code: ");
		String coursecode = sc.nextLine();
		if (courseExist(coursecode)) {
			List list = getCoursedList();
			System.out.println("Enter Index");
			try {
				int indexinput=sc.nextInt();
				Course c = Course.getCourse(coursecode);
				if(c.indexExist(indexinput)){}
			} catch (Exception e) {
			}
		} else {
			System.out.println("Course Code does not exist!");
		}
	}
}
/*
 * static Scanner input = new Scanner(System.in); private String name; private
 * String courseCode; private String school; private int nindex; private int
 * oindex; private Course cPointer = new Course();
 * 
 * public void setCourseCode(String courseCode) { this.courseCode = courseCode;
 * }
 * 
 * 
 * public void setSchool(String school) { this.school = school; }
 * 
 * 
 * public void setOIndex(int oindex) { this.oindex = oindex; } public void
 * setNIndex(int nindex) { this.nindex = nindex; }
 * 
 * public static List getCourseList() { return getCourseList("Course.dat"); }
 * 
 * public static List getCourseList(String fileName) { List list = null; try {
 * list = FileIO.readInFile(fileName); } catch (Exception e) { } if (list ==
 * null) list = new ArrayList(); return list; }
 * 
 * public boolean searchCourse(String courseCode) { List list = getCourseList();
 * List<Course> courseList= new ArrayList<Course>(); try{ for (int i = 0;
 * i<list.size(); i++) if (courseList.get(i).courseExist(courseCode)) { cPointer
 * = courseList.get(i); return true; } } catch (Exception e) {
 * 
 * } return false; }
 * 
 * public boolean searchIndex(int index) { if(cPointer != null) { return
 * cPointer.selectIndex(index); } return false; }
 * 
 * public void updateCourseIndex(int oldIndex, int newIndex, String courseCode)
 * { List list = getCourseList(); List<Course> courseList = new
 * ArrayList<Course>(); try { for (int i = 0; i < list.size(); i++) { //Course c
 * = (Course) list.get(i); if (courseList.get(i).courseExist(courseCode)) {
 * //there exists a Course in courseList with the given courseCode
 * courseList.get(i).updateIndex(oldIndex, newIndex); //selected Course } } }
 * catch (Exception e) { } }
 * 
 * public static void retrieveCourseVacancy(String courseCode) { //obtain
 * vacancy from each class and sum them up together int courseVacancy=0;
 * ArrayList<CourseIndex> cIndexList = new ArrayList<CourseIndex>(); List list =
 * getCourseList(); List<Course> courseList = new ArrayList<Course>(); try { for
 * (int i = 0; i < list.size(); i++) { //Course c = (Course) list.get(i); if
 * (courseList.get(i).courseExist(courseCode)) { cIndexList =
 * courseList.get(i).getcIndexList(); } } for(int j=0;j<cIndexList.size();j++){
 * courseVacancy+=cIndexList.get(j).getVacancy(); } } catch (Exception e) { } }
 * 
 * 
 * public void updateVacancy(String courseCode,int index,int vacancy) { List
 * list = getCourseList(); List<Course> courseList = new ArrayList<Course>();
 * try{ for (int i = 0;i < list.size(); i++) {
 * if(courseList.get(i).courseExist(courseCode)) {
 * courseList.get(i).updateVacancy(index, vacancy); } } }catch (Exception e) {
 * 
 * } }
 * 
 * public void updateCourse() { int choice = 0; while (choice != 5) {
 * System.out.println("Choose component to update:");
 * System.out.println("(1) Course Code"); System.out.println("(2) School");
 * System.out.println("(3) Course Index"); System.out.println("(4) Vacancy");
 * System.out.println("(5) End update"); try { choice = input.nextInt(); } catch
 * (Exception e) { input.nextLine(); } switch (choice) { case 1:
 * System.out.print("Enter the new course code for " + name);
 * this.setCourseCode(input.nextLine()); if(!searchCourse(courseCode)) {
 * System.out.print("Course does not exist"); } else {
 * System.out.print("Enter the current index to be changed: ");
 * this.setOIndex(input.nextInt());
 * System.out.print("Enter the new course code for: ");
 * this.setNIndex(input.nextInt()); //cPointer.updateCourse(oindex, nindex,
 * courseCode); } break; case 2: System.out.print("Enter the new school for " +
 * name); this.setSchool(input.nextLine());
 * 
 * break; case 3: System.out.print("Enter course code for index: "); int
 * oldIndex = input.nextInt(); if (searchCourse(courseCode)) {
 * System.out.print("Please enter the new index number: "); int newIndex =
 * input.nextInt(); updateCourseIndex(oindex, nindex, courseCode); } break; case
 * 4: /*System.out.print("Enter the course code: ");
 * this.setCourseCode(input.nextLine()); if(searchCourse(courseCode)) {
 * System.out.print("Enter the index: "); int index = input.nextInt();
 * if(searchIndex(index)) { System.out.print("Enter the vacancy to be set: ");
 * int vac = input.nextInt(); this.updateVacancy(courseCode,index,vac); } }
 * break; default: break;
 * 
 * } } }
 * 
 * public static void saveCourseList(List list){
 * FileIO.writeToFile("course.dat", list); }
 * 
 * public static void addCourse() { String courseCode; String courseSchool; int
 * totalIndex; //ArrayList<CourseIndex> cIndexList = new
 * ArrayList<CourseIndex>(); Course newCourse = new Course(); int vacancy; List
 * list = AdminCourse.getCourseList(); Scanner sc = new Scanner(System.in); try{
 * System.out.println("Add Course"); System.out.println("===========");
 * System.out.println("Please enter:"); System.out.println("(1) Course Code");
 * courseCode=sc.nextLine(); System.out.println("(2) School:");
 * courseSchool=sc.nextLine();
 * System.out.println("(3a) Input total no. of indexes:");
 * totalIndex=sc.nextInt();
 * System.out.println("(3b) Input all course indexes:"); //Store all course
 * indexes input for(int k=0;k<totalIndex;k++){ int a = sc.nextInt();
 * newCourse.AddtocIndexList(a,0,""); } //Update Vacancy for(int
 * i=0;i<newCourse.getcIndexList().size();i++){
 * System.out.println("(4) Input vacancy for the index "+newCourse.getcIndexList
 * ().get(i).getIndex()+":"); int b=sc.nextInt();
 * newCourse.getcIndexList().get(i).setVacancy(b); }
 * newCourse.setCourseCode(courseCode); newCourse.setSchool(courseSchool);
 * list.add(newCourse); AdminCourse.saveCourseList(list); //Update course.dat }
 * catch(Exception e){System.out.println(e.getMessage());}
 * System.out.println("Course added!"); } public static List getRegisteredList()
 * { return getRegisteredList("studentRecords.dat"); }
 * 
 * public static List getRegisteredList(String fileName) { List list = null; try
 * { list = FileIO.readInFile(fileName); } catch (Exception e) { } if (list ==
 * null) list = new ArrayList(); return list; }
 * 
 * public void addStudent() { String u; String p; String name; String matricNo;
 * char gender; String nationality; Scanner sc=new Scanner(System.in);
 * System.out.println("Add Student (user,pw,name,matricNo,gender,nationality");
 * u=sc.next(); p=sc.next(); name=sc.next(); matricNo=sc.next();
 * gender=sc.next().charAt(0); nationality=sc.next();
 * 
 * /*add to DB }
 * 
 * public void editStudentAccessPeriod() {
 * 
 * }
 * 
 * public void addCourse() { AdminCourse.addCourse(); }
 * 
 * public void updateCourse() { AdminCourse courseUpdater = new AdminCourse();
 * courseUpdater.updateCourse(); }
 * 
 * public static void checkVacancies(){ Scanner sc=new Scanner(System.in);
 * System.out.println("Please enter Index Number:"); String courseCode =
 * sc.nextLine(); AdminCourse.retrieveCourseVacancy(courseCode); }
 * 
 * public void printStudentListByIndex(){ Scanner sc=new Scanner(System.in);
 * System.out.println("Please enter Index Number:"); int indexNo = sc.nextInt();
 * for(int i=0;i<studentList.size();i++){
 * if(Integer.toString(indexNo).equals(studentList.get(i).get(6))){
 * System.out.println(studentList.get(i).get(0)); } }
 * 
 * //Query "studentRecords.dat", print students registered for specified index
 * List list = getRegisteredList();
 * System.out.println("Students registered in Index "+indexNo); try {; for (int
 * i = 0; i < list.size(); i++) { StudentCourse studC = (StudentCourse)
 * list.get(i); if (studC.getCourseIndex()==indexNo){
 * System.out.println(studC.getMatricNumber()); } } } catch (Exception e) { } }
 * 
 * public void printStudentListByCourse() { Scanner sc=new Scanner(System.in);
 * System.out.println("Please enter Course Code:"); String courseCode =
 * sc.next();
 * 
 * //Query "studentRecords.dat", print students registered for specified course
 * List list = getRegisteredList();
 * System.out.println("Students registered in Course "+courseCode); try {; for
 * (int i = 0; i < list.size(); i++) { StudentCourse studC = (StudentCourse)
 * list.get(i); if (studC.getCourseCode().equals(courseCode)){
 * System.out.println(studC.getMatricNumber()); } } } catch (Exception e) { } }
 * 
 * }
 */