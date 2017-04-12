import java.io.Serializable;
import java.time.*;
import java.util.List;
public class TimeSlot implements Serializable, Comparable<TimeSlot> {
	private static final long serialVersionUID = 1L;
	private LocalTime start;
	private LocalTime end;
	private DayOfWeek day;
	private String venue;
	
	TimeSlot(){}
	
	TimeSlot (int day, String start, String end, String venue) {
		this.day = DayOfWeek.of(day);
		this.start = LocalTime.parse(start);
		this.end = LocalTime.parse(end);
		this.venue = venue;
	}
	
	TimeSlot(DayOfWeek day, LocalTime start, LocalTime end, String venue) {
		this.day = day;
		this.start = start;
		this.end = end;
		this.venue = venue;
	}
	
	public LocalTime getStart() {
		return this.start;
	}
	
	public LocalTime getEnd() {
		return this.end;
	}
	
	public DayOfWeek getDay() {
		return this.day;
	}
	
	public String getVenue() {
		return this.venue;
	}
	
	public boolean hasClash(List<TimeSlot> list) {
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getDay() == day) {
				//System.out.println("Checking...");
				if (start.isAfter(list.get(i).getStart()) && start.isBefore(list.get(i).getEnd())) {//start is within an occupied slot
					System.out.println("Start clashed.");
					return true;
				}
				else if (end.isAfter(list.get(i).getStart()) && end.isBefore(list.get(i).getEnd())) {//end is within an occupied slot
					System.out.println("End clashed.");
					return true;
				}
				else if(start.equals(list.get(i).getStart()) || end.equals(list.get(i).getEnd())) {//start or end is concurrent with another slot
					System.out.println("Start/End clashed.");
					return true;
				}
			}
		}
		return false;
	}
	
	public int compareTo(TimeSlot ts) {
		if(this.day.getValue() < (ts.getDay().getValue())) {//when the first day is bigger than the second
			return 1;
		}
		if(this.day.getValue() == ts.getDay().getValue()) { 
			if(this.start.isBefore(ts.getStart()))
				return 1;
		}
		return 0;
	}
}