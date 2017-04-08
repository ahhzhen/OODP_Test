import java.time.*;
import java.util.ArrayList;
import java.util.List;

public class InitFiles {
	
	public static void init()
	{	
		List studList = new ArrayList();
		Student StudentA = new Student("aaa074", Integer.toString("123456".hashCode()), "U1620736G", "Student1", 'F', "Singaporean");
		Student StudentB = new Student("bbb123", Integer.toString("654321".hashCode()), "U1622603D", "Student2", 'F', "Singaporean");
		studList.add(StudentA);
		studList.add(StudentB);
		FileIO.writeToFile("studentList.dat", studList);
		
		List courseList = new ArrayList();
		TimeSlot ts1 = new TimeSlot(DayOfWeek.WEDNESDAY,LocalTime.parse("13:30"),LocalTime.parse("14:30"), "TR+37");
		TimeSlot ts2 = new TimeSlot(DayOfWeek.WEDNESDAY,LocalTime.parse("14:30"),LocalTime.parse("16:30"), "HWLAB1");
		TimeSlot ts3 = new TimeSlot(DayOfWeek.WEDNESDAY,LocalTime.parse("11:30"),LocalTime.parse("12:30"), "TR+35");
		TimeSlot ts4 = new TimeSlot(DayOfWeek.WEDNESDAY,LocalTime.parse("14:30"),LocalTime.parse("16:30"), "HWLAB1");
		TimeSlot ts5 = new TimeSlot(DayOfWeek.MONDAY,LocalTime.parse("13:30"),LocalTime.parse("14:30"), "TR+37");
		TimeSlot ts6 = new TimeSlot(DayOfWeek.THURSDAY,LocalTime.parse("12:30"),LocalTime.parse("14:30"), "HWLAB3");
		TimeSlot ts7 = new TimeSlot(DayOfWeek.THURSDAY,LocalTime.parse("14:30"),LocalTime.parse("15:30"), "TR 9");
		TimeSlot ts8 = new TimeSlot(DayOfWeek.THURSDAY,LocalTime.parse("10:30"),LocalTime.parse("12:30"), "HWLAB1");
		Course c1 = new Course("Human Computer Interaction", "CZ2004", "School of Computer Science and Engineering");
		c1.AddtocIndexList(10241, 15, "FSP1", ts1, ts2);
		c1.AddtocIndexList(10242, 15, "FSP2", ts3, ts4);
		c1.AddtocIndexList(10243, 15, "FSP3", ts5, ts6);
		c1.AddtocIndexList(10244, 15, "FSP4", ts7, ts8);
		
		ts1 = new TimeSlot(DayOfWeek.MONDAY,LocalTime.parse("09:30"),LocalTime.parse("10:30"), "TR+37");
		ts2 = new TimeSlot(DayOfWeek.MONDAY,LocalTime.parse("10:30"),LocalTime.parse("12:30"), "SWLAB1");
		ts3 = new TimeSlot(DayOfWeek.TUESDAY,LocalTime.parse("14:30"),LocalTime.parse("15:30"), "TR+45");
		ts4 = new TimeSlot(DayOfWeek.TUESDAY,LocalTime.parse("10:30"),LocalTime.parse("12:30"), "SWLAB1");
		ts5 = new TimeSlot(DayOfWeek.MONDAY,LocalTime.parse("16:30"),LocalTime.parse("17:30"), "TR+37");
		ts6 = new TimeSlot(DayOfWeek.MONDAY,LocalTime.parse("10:30"),LocalTime.parse("12:30"), "SWLAB1");
		ts7 = new TimeSlot(DayOfWeek.FRIDAY,LocalTime.parse("13:30"),LocalTime.parse("12:30"), "TR+45");
		ts8 = new TimeSlot(DayOfWeek.FRIDAY,LocalTime.parse("10:30"),LocalTime.parse("12:30"), "SWLAB1");
		Course c2 = new Course("Operating Systems", "CZ2005", "School of Computer Science and Engineering");
		c2.AddtocIndexList(10251, 15, "SSR1", ts1, ts2);
		c2.AddtocIndexList(10252, 15, "SSR2", ts3, ts4);
		c2.AddtocIndexList(10253, 15, "SSR3", ts5, ts6);
		c2.AddtocIndexList(10254, 15, "BCG4", ts7, ts8);
		
		
		courseList.add(c1);
		courseList.add(c2);
		FileIO.writeToFile("courselist.dat", courseList);
		
		List studentCList = new ArrayList();
		
		
		
		FileIO.writeToFile("studentRecords.dat", studentCList);
	}
}
