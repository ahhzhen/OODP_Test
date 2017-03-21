

public class courseRecords implements Comparable<courseRecords>{
	private String courseCode;
	private String courseGroup;
	private int vacancies;
	
    public courseRecords(String courseCode, String courseGroup, int vacancies)
    {
    	this.courseCode=courseCode;
    	this.courseGroup=courseGroup;
    	this.vacancies=vacancies;
    }
    
    public int getVacancies() {
		return vacancies;
	}

	public void setVacancies(int vacancies) {
		this.vacancies = vacancies;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public void setCourseGroup(String courseGroup) {
		this.courseGroup = courseGroup;
	}

	//create a toString method to return string in the same delimited format as the input record
    public String toString()
    {
        return  courseCode + "|" + courseGroup + "|"+vacancies;
    }
    

	public String getCourseCode() {
		return courseCode;
	}

	public String getCourseGroup() {
		return courseGroup;
	}

	@Override
	public int compareTo(courseRecords arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
}

