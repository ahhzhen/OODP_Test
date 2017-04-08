import java.io.Serializable;
import java.time.*;
import java.util.List;
public class TimeSlot implements Serializable {
	private LocalTime start;
	private LocalTime end;
	private DayOfWeek day;
	private String venue;
	
	TimeSlot(){}
	
	TimeSlot (int d, String s, String e, String v)
	{
		day = DayOfWeek.of(d);
		start = LocalTime.parse(s);
		end = LocalTime.parse(e);
		venue = v;
	}
	
	TimeSlot(DayOfWeek d, LocalTime s, LocalTime e, String v)
	{
		day = d;
		start = s;
		end = e;
		venue = v;
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
	
	public boolean hasClash(List<TimeSlot> list)
	{
		for (int i = 0; i < list.size(); i++)
		{
			if(list.get(i).getDay() == day)
			{
				System.out.println("Checking...");
				if (start.isAfter(list.get(i).getStart()) && start.isBefore(list.get(i).getEnd())) //start is within an occupied slot
				{
					System.out.println("Start clashed.");
					return true;
				}
				else if (end.isAfter(list.get(i).getStart()) && end.isBefore(list.get(i).getEnd())) //end is within an occupied slot
				{
					System.out.println("End clashed.");
					return true;
				}
				else if(start.equals(list.get(i).getStart()) || end.equals(list.get(i).getEnd())) //start or end is concurrent with another slot
				{
					System.out.println("Start/End clashed.");
					return true;
				}
			}
		}
		return false;
	}
}
