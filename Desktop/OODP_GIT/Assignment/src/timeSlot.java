public class timeSlot {
	private int day;
	private String time;
	private String lessonType;

	public timeSlot(String courseCode, int d, String t, String lType) {

		// day in 1 digit format (eg. "MON = 1")
		day = d;
		// time in 24hr format
		time = t;
		// lessontype in 3 character format (eg. "LEC")
		lessonType = lType;
	}

	public timeSlot(int d, String t, String lType) {
		// day in 1 digit format (eg. "MON = 1")
		day = d;
		// time in 24hr format
		time = t;
		// lessontype in 3 character format (eg. "LEC")
		lessonType = lType;
	}

	public void print() {
		// System.out.print(super.getcourseCode() + ":" +
		// day+":"+time+":"+lessonType+"%");
	}
}