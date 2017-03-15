import java.util.*;
public class Student extends User {
	private String matricNo;
	private String name;
	private char gender;
	private String nationality;
	private Date start;
	private Date end;
	private Course[] cList;
	
	Student(String u, String matric, String n, char c, String nation)
	{
		super(u, matric, "Student");
		matricNo = matric;
		name = n;
		gender = c;
		nationality = nation;
	}
	
	public String getMatricNo(){return matricNo;}
	
	public String getName(){return name;}
	
	public char getGender(){return gender;}
	
	public String getNationality(){return nationality;}
	
	public Date getStartDate(){return start;}
	
	public Date getEndDate(){return end;}
	
}
