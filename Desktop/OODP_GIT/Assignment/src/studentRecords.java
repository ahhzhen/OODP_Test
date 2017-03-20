import java.util.Date;


public class studentRecords implements Comparable<studentRecords>{
	private String matricNo;
	private String name;
	private char gender;
	private String nationality;
	private Date start;
	private Date end;
	private Course[] cList;
    
    public studentRecords(String matricNo, String name, char gender, String nationality, Date start, Date end)
    {
    	this.matricNo=matricNo;
    	this.name=name;
    	this.gender=gender;
    	this.nationality=nationality;
    	this.start=start;
    	this.end=end;
    }
    
    //create a toString method to return string in the same delimited format as the input record
    public String toString()
    {
        return matricNo + "|" + name + "|" + gender +"|" + nationality + "|" + start +"|" + end;
    }
    

	@Override
	public int compareTo(studentRecords arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
}

