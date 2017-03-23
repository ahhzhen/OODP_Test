import java.util.*;
import java.util.zip.InflaterOutputStream;
public class AdminCourse {

	Course cPointer=new Course();
	static Scanner input = new Scanner(System.in);
	private String name;
	private String courseCode;
	private String school;
	private int nindex;
	private int oindex;
	
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}


	public void setSchool(String school) {
		this.school = school;
	}
	
	
	public void setOIndex(int oindex) {
		this.oindex = oindex;
	}
	public void setNIndex(int nindex) {
		this.nindex = nindex;
	}

	public static List getCourseList() {
		return getCourseList("Course.dat");
	}

	public static List getCourseList(String fileName) {
		List list = null;
		try {
			list = FileIO.readInFile(fileName);
		} catch (Exception e) {
		}
		if (list == null)
			list = new ArrayList();
		return list;
	}

	public void updateCourseIndex(int oldIndex, int newIndex, String courseCode) {
		List list = getCourseList();
		List<Course> courseList = new ArrayList<Course>();
		try {
			for (int i = 0; i < list.size(); i++) {
				//Course c = (Course) list.get(i);
				if (courseList.get(i).getCourseCode().equals(courseCode)) { //there exists a Course in courseList with the given courseCode
					cPointer = courseList.get(i); //cPointer points to current Course
					for (int k = 0; k < cPointer.getcIndexList().size(); k++) { //for the list of CourseIndexes, find the index to update
						if (cPointer.getcIndexList().get(k).getIndex() == oldIndex)
						{
							cPointer.getcIndexList().get(k).setIndex(newIndex);
						}
					}
				}
			}
		} catch (Exception e) {
		}
	}

	public void updateCourse() {
		int choice = 0;
		while (choice != 5) {
			System.out.println("Choose component to update:");
			System.out.println("(1) Course Code");
			System.out.println("(2) School");
			System.out.println("(3) Course Index");
			System.out.println("(4) Vacancy");
			System.out.println("(5) End update");
			try {
				choice = input.nextInt();
			} catch (Exception e) {
				input.nextLine();
			}
			switch (choice) {
			case 1:
				System.out.print("Enter the new course code for " + name);
				this.setCourseCode(input.nextLine());
				if(!cPointer.courseExist(courseCode))
				{
					System.out.print("Course does not exist");
				}
				else
				{
					System.out.print("Enter the current index to be changed: ");
					this.setOIndex(input.nextInt());
					System.out.print("Enter the new course code for: ");
					this.setNIndex(input.nextInt());
					//cPointer.updateCourse(oindex, nindex, courseCode);
				}
				break;
			case 2:
				System.out.print("Enter the new school for " + name);
				this.setSchool(input.nextLine());

				break;
			case 3:
				System.out.print("Enter course code for index: ");
				int oldIndex = input.nextInt();
				if (cPointer.courseExist((courseCode)) {
					System.out.print("Please enter the new index number: ");
					int newIndex = input.nextInt();
					updateCourseIndex(oindex, nindex, courseCode);
				}
				break;
			case 4:
				/*System.out.print("Enter the course code: ");
				this.setCourseCode(input.nextLine());
				if(course.courseExist(courseCode))
				{
				}*/
				break;
			default:
				break;

			}
		}
	}
}
