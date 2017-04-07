import java.util.*;
import java.time.*;

public class Daytimeclash {
	
	public static void main(String[] args) {
		List<TimeSlot> test = new ArrayList<TimeSlot>();
		// TODO Auto-generated method stub
		test.add(new TimeSlot(DayOfWeek.MONDAY,LocalTime.parse("13:30"),LocalTime.parse("15:30")));
		Scanner sc = new Scanner(System.in);
		//menu
		System.out.println("Please enter day: ");
		String day = sc.next();
		System.out.println("Please enter start time(HH:MM): ");
		LocalTime start = LocalTime.parse(sc.next());
		System.out.println("Please enter end time(HH:MM): ");
		LocalTime end = LocalTime.parse(sc.next());
		DayOfWeek dow = DayOfWeek.valueOf(day.toUpperCase()); //string-to-DayOfWeek
		sc.close();
		//end menu
		if(hasClash(test, dow, start, end))
		{
			System.out.println("Clash detected. Time slot not added.");
			for(int i = 0; i < test.size();i++)
			{
				System.out.println(test.get(i).getDay() + " " + test.get(i).getStart() + " " + test.get(i).getEnd());
			}
		}
		else
		{
			test.add(new TimeSlot(dow,start,end));
			System.out.println("Test completed.");
			for(int i = 0; i < test.size();i++)
			{
				System.out.println(test.get(i).getDay() + " " + test.get(i).getStart() + " " + test.get(i).getEnd());
			}
		}
	}

	public static boolean hasClash(List<TimeSlot> list, DayOfWeek day, LocalTime start, LocalTime end)
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
