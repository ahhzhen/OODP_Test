import java.util.Date;


public class studentRecords implements Comparable<studentRecords>{
	private String matricNo;
	private String courseCode;
	private String courseGroup;
    
    public studentRecords(String matricNo, String courseCode, String courseGroup)
    {
    	this.matricNo=matricNo;
    	this.courseCode=courseCode;
    	this.courseGroup=courseGroup;
    }
    
    //create a toString method to return string in the same delimited format as the input record
    public String toString()
    {
        return matricNo + "|" + courseCode + "|" + courseGroup;
    }
    

	@Override
	public int compareTo(studentRecords arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
}

