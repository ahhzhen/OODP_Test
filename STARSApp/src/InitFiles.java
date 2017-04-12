import java.time.*;
import java.util.ArrayList;
import java.util.List;

public class InitFiles {
	
	public static void init()
	{	
		PasswordHash pwHash = new PasswordHash();
		
		List studList = new ArrayList();
		Student StudentA = new Student("jchek001", pwHash.hash("123456","jchek001"), "U1620736G", "Jeanelle", 'F', "Singaporean");
		Student StudentB = new Student("zchen035", pwHash.hash("123456","zchen035"), "U1622603D", "Zhenni", 'F', "Singaporean");
		Student StudentC = new Student("fly0001", pwHash.hash("123456","fly0001"), "U1621531K", "Li Yan", 'F', "Singaporean");
		Student StudentD = new Student("ddd123", pwHash.hash("654321","ddd123"), "U1622123D", "Student4", 'F', "Malaysian");
		Student StudentE = new Student("eee123", pwHash.hash("123456","eee123"), "U1622124E", "Student5", 'M', "Singaporean");
		Student StudentF = new Student("fff123", pwHash.hash("654321","fff123"), "U1622125F", "Student6", 'F', "Singaporean");
		Student StudentG = new Student("ggg123", pwHash.hash("123456","ggg123"), "U1622126G", "Student7", 'M', "Malaysian");
		Student StudentH = new Student("hhh123", pwHash.hash("654321","hhh123"), "U1622127H", "Student8", 'F', "Singaporean");
		Student StudentI = new Student("iii123", pwHash.hash("123456","iii123"), "U1622128I", "Student9", 'M', "Singaporean");
		studList.add(StudentA);
		studList.add(StudentB);
		studList.add(StudentC);
		studList.add(StudentD);
		studList.add(StudentE);
		studList.add(StudentF);
		studList.add(StudentG);
		studList.add(StudentH);
		studList.add(StudentI);
		FileIO.writeToFile("studentList.dat", studList);
		
		
		List userList = new ArrayList();
		User u1 = new Admin("starsadmin", pwHash.hash("starsadmin","starsadmin"));
		User u2 = StudentA;
		User u3 = StudentB;
		User u4 = StudentC;
		User u5 = StudentD;
		User u6 = StudentE;
		User u7 = StudentF;
		User u8 = StudentG;
		User u9 = StudentH;
		User u10 = StudentI;
		userList.add(u1);
		userList.add(u2);
		userList.add(u3);
		userList.add(u4);
		userList.add(u5);
		userList.add(u6);
		userList.add(u7);
		userList.add(u8);
		userList.add(u9);
		userList.add(u10);
		FileIO.writeToFile("userList.dat", userList);
		
		
		List courseList = new ArrayList();
		TimeSlot ts1 = new TimeSlot(DayOfWeek.WEDNESDAY,LocalTime.parse("13:30"),LocalTime.parse("14:30"), "TR+37");
		TimeSlot ts2 = new TimeSlot(DayOfWeek.WEDNESDAY,LocalTime.parse("14:30"),LocalTime.parse("16:30"), "HWLAB1");
		TimeSlot ts3 = new TimeSlot(DayOfWeek.WEDNESDAY,LocalTime.parse("11:30"),LocalTime.parse("12:30"), "TR+35");
		TimeSlot ts4 = new TimeSlot(DayOfWeek.WEDNESDAY,LocalTime.parse("14:30"),LocalTime.parse("16:30"), "HWLAB1");
		TimeSlot ts5 = new TimeSlot(DayOfWeek.MONDAY,LocalTime.parse("13:30"),LocalTime.parse("14:30"), "TR+37");
		TimeSlot ts6 = new TimeSlot(DayOfWeek.THURSDAY,LocalTime.parse("12:30"),LocalTime.parse("14:30"), "HWLAB3");
		TimeSlot ts7 = new TimeSlot(DayOfWeek.THURSDAY,LocalTime.parse("14:30"),LocalTime.parse("15:30"), "TR 9");
		TimeSlot ts8 = new TimeSlot(DayOfWeek.THURSDAY,LocalTime.parse("10:30"),LocalTime.parse("12:30"), "HWLAB1");
		TimeSlot ts9 = new TimeSlot(DayOfWeek.TUESDAY, LocalTime.parse("13:30"),LocalTime.parse("14:30"), "LT2A");
		TimeSlot ts10= new TimeSlot(DayOfWeek.FRIDAY, LocalTime.parse("09:30"),LocalTime.parse("10:30"), "LT2A");
		ArrayList lectList = new ArrayList();
		lectList.add(ts9);
		lectList.add(ts10);
		Course c1 = new Course("Human Computer Interaction", "CZ2004", "School of Computer Science and Engineering", lectList);
		c1.AddtocIndexList(10241, 0, 2, "FSP1", ts1, ts2);
		c1.AddtocIndexList(10242, 1, 2, "FSP2", ts3, ts4);
		c1.AddtocIndexList(10243, 0, 2, "FSP3", ts5, ts6);
		c1.AddtocIndexList(10244, 0, 2, "FSP4", ts7, ts8);
		
		ts1 = new TimeSlot(DayOfWeek.MONDAY,LocalTime.parse("09:30"),LocalTime.parse("10:30"), "TR+37");
		ts2 = new TimeSlot(DayOfWeek.MONDAY,LocalTime.parse("10:30"),LocalTime.parse("12:30"), "SWLAB1");
		ts3 = new TimeSlot(DayOfWeek.TUESDAY,LocalTime.parse("14:30"),LocalTime.parse("15:30"), "TR+45");
		ts4 = new TimeSlot(DayOfWeek.TUESDAY,LocalTime.parse("10:30"),LocalTime.parse("12:30"), "SWLAB1");
		ts5 = new TimeSlot(DayOfWeek.MONDAY,LocalTime.parse("16:30"),LocalTime.parse("17:30"), "TR+37");
		ts6 = new TimeSlot(DayOfWeek.MONDAY,LocalTime.parse("10:30"),LocalTime.parse("12:30"), "SWLAB1");
		ts7 = new TimeSlot(DayOfWeek.FRIDAY,LocalTime.parse("13:30"),LocalTime.parse("12:30"), "TR+45");
		ts8 = new TimeSlot(DayOfWeek.FRIDAY,LocalTime.parse("10:30"),LocalTime.parse("12:30"), "SWLAB1");
		ts9 = new TimeSlot(DayOfWeek.TUESDAY, LocalTime.parse("08:30"),LocalTime.parse("09:30"), "LT8");
		ts10= new TimeSlot(DayOfWeek.THURSDAY, LocalTime.parse("09:30"),LocalTime.parse("10:30"), "LT8");
		ArrayList lectList2 = new ArrayList();
		lectList2.add(ts9);
		lectList2.add(ts10);
		Course c2 = new Course("Operating Systems", "CZ2005", "School of Computer Science and Engineering", lectList2);
		c2.AddtocIndexList(10251, 0, 2, "SSR1", ts1, ts2);
		c2.AddtocIndexList(10252, 1, 2, "SSR2", ts3, ts4);
		c2.AddtocIndexList(10253, 0, 2, "SSR3", ts5, ts6);
		c2.AddtocIndexList(10254, 0, 2, "BCG4", ts7, ts8);
		
		
		ts1 = new TimeSlot(DayOfWeek.WEDNESDAY,LocalTime.parse("11:30"),LocalTime.parse("12:30"), "TR+8");
		ts2 = new TimeSlot(DayOfWeek.THURSDAY,LocalTime.parse("10:30"),LocalTime.parse("12:30"), "SWLAB2");
		ts9 = new TimeSlot(DayOfWeek.TUESDAY, LocalTime.parse("14:30"),LocalTime.parse("15:30"), "LT2A");
		ts10= new TimeSlot(DayOfWeek.THURSDAY, LocalTime.parse("08:30"),LocalTime.parse("09:30"), "LT2A");
		ArrayList lectList3 = new ArrayList();
		lectList3.add(ts9);
		lectList3.add(ts10);
		Course c3 = new Course("Object Oriented Design and Programming", "CZ2002", "School of Computer Science and Engineering", lectList3);
		c3.AddtocIndexList(10221, 2, 2, "FSP1", ts1, ts2);
		
		courseList.add(c1);
		courseList.add(c2);
		courseList.add(c3);
		FileIO.writeToFile("courselist.dat", courseList);
		
		List studentCList = new ArrayList();
		StudentCourse sc1 = new StudentCourse("U1620736G","CZ2004",10244);
		StudentCourse sc2 = new StudentCourse("U1620736G","CZ2005",10251);
		StudentCourse sc3 = new StudentCourse("U1622603D","CZ2005",10251);
		StudentCourse sc4 = new StudentCourse("U1621531K","CZ2004",10243);
		StudentCourse sc5 = new StudentCourse("U1622123D","CZ2004",10243);
		StudentCourse sc6 = new StudentCourse("U1622123D","CZ2005",10252);
		StudentCourse sc7 = new StudentCourse("U1622124E","CZ2004",10242);
		StudentCourse sc8 = new StudentCourse("U1622124E","CZ2005",10253);
		StudentCourse sc9 = new StudentCourse("U1622125F","CZ2005",10253);
		StudentCourse sc10 = new StudentCourse("U1622126G","CZ2004",10241);
		StudentCourse sc11 = new StudentCourse("U1622126G","CZ2005",10254);
		StudentCourse sc12 = new StudentCourse("U1622127H","CZ2004",10241);
		StudentCourse sc13 = new StudentCourse("U1622128I","CZ2004",10244);
		StudentCourse sc14 = new StudentCourse("U1622128I","CZ2005",10254);
		
		studentCList.add(sc1);
		studentCList.add(sc2);
		studentCList.add(sc3);
		studentCList.add(sc4);
		studentCList.add(sc5);
		studentCList.add(sc6);
		studentCList.add(sc7);
		studentCList.add(sc8);
		studentCList.add(sc9);
		studentCList.add(sc10);
		studentCList.add(sc11);
		studentCList.add(sc12);
		studentCList.add(sc13);
		studentCList.add(sc14);
		FileIO.writeToFile("studentRecords.dat", studentCList);
	}
}
