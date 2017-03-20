

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
    
    //create a toString method to return string in the same delimited format as the input record
    public String toString()
    {
        return  courseCode + "|" + courseGroup + "|"+vacancies;
    }
    

	@Override
	public int compareTo(courseRecords arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
}

