import java.time.*;
public class TimeSlot {
	private LocalTime start;
	private LocalTime end;
	private DayOfWeek day;
	public TimeSlot (DayOfWeek day, LocalTime start, LocalTime end)
	{
		this.start = start;
		this.end = end;
		this.day = day;
	}
	
	public LocalTime getStart()
	{
		return this.start;
	}
	
	public LocalTime getEnd()
	{
		return this.end;
	}
	
	public DayOfWeek getDay()
	{
		return this.day;
	}
}
