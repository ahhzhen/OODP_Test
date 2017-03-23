import java.time.*;
public class Lesson {
	private DayOfWeek day;
	private LocalTime start;
	private LocalTime end;
	private String venue;
	
	Lesson(){}
	
	Lesson(int d, String s, String e, String v)
	{
		day = DayOfWeek.of(d);
		start = LocalTime.parse(s);
		end = LocalTime.parse(e);
		venue = v;
	}
	
	public DayOfWeek getDay() {
		return day;
	}
	public LocalTime getStart() {
		return start;
	}
	public LocalTime getEnd() {
		return end;
	}
	public String getVenue() {
		return venue;
	}
	
	
}
