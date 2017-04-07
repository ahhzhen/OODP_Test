import java.util.ArrayList;
import java.util.List;

public class CourseIndex extends Course {
	private int index;
	private int vacancy;
	private String groupName;
	//private Lesson tutorial;
	//private Lesson lab;
	
	//private String name;
	//private String courseCode;
	//private String school;
	private ArrayList<CourseIndex> cIndexList;
	//private ArrayList<CourseIndex> coursesList;
	
	public CourseIndex(int index, int vacancy, String gname) {//, Lesson tut, Lesson lab) {
		this.index = index;
		this.vacancy = vacancy;
		this.groupName = gname;
		//this.tutorial = tut;
		//this.lab = lab;
	}
	
	public CourseIndex(String courseCode, int index) {//, Lesson tut, Lesson lab) {
		super(courseCode);
		this.index = index;
	}
	
	
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getVacancy() {
		return vacancy;
	}

	public void setVacancy(int vacancy) {
		this.vacancy = vacancy;
	}

	public void decrementVacancy() {
		if (vacancy > 0)
			vacancy--;
	}
	
	public void incrementVacancy() {
		vacancy++;
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

	/*public String getCourseCode() {
		return courseCode;
	}*/

	public boolean indexExist(int index) {
		/*if (cIndexList != null) {
			for (int i = 0; i < cIndexList.size(); i++) {
				CourseIndex cIndex = cIndexList.get(i);
				if (cIndex.getIndex() == index) {
					return true;
				}
			}
		}*/
		return (index == this.index);
	}
}
