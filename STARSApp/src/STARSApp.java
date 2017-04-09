import java.util.*;
import java.io.Console;
import java.util.regex.Pattern;
import java.time.*;
public class STARSApp 
{

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String type = "", user = "", pw = "";
		int choice = 0;
		User userSession;
		boolean login = false;
		ArrayList<String> uList = new ArrayList<String>();
		String str = "abdced";
		uList.add("123456;" + str.hashCode() + ";Admin");
		uList.add("aaa074;" + "123456".hashCode() + ";Student");
		uList.add("bbb123;" + "654321".hashCode() + ";Student");
		uList.add("ccc123;" + "123456".hashCode() + ";Student");
		uList.add("ddd123;" + "654321".hashCode() + ";Student");
		uList.add("eee123;" + "123456".hashCode() + ";Student");
		uList.add("fff123;" + "654321".hashCode() + ";Student");
		uList.add("ggg123;" + "123456".hashCode() + ";Student");
		uList.add("hhh123;" + "654321".hashCode() + ";Student");
		uList.add("iii123;" + "123456".hashCode() + ";Student");
		//InitFiles.init();
		System.out.println("Welcome~\nPlease Login");
		while (login == false) {
			while (choice != 1 && choice != 2) {
				System.out
						.println("Please key in user domain: \n1)Student\n2)Admin");
				choice = input.nextInt();
			}
			if (choice == 1)
				type = "Student";
			else
				type = "Admin";

			System.out.print("Please key in username: ");
			user = input.next();

			System.out.print("Please key in password: ");
			/*
			 * char[] passString = console.readPassword(); pw = new
			 * String(passString);
			 */
			pw = input.next();

			login = checkLogin(uList, user, pw, type);
			choice = -1;
		}
		userSession = createUser(user, type);
		if(userSession!= null){
			System.out.println("Login Successfully!");
			userSession.startSession();
		}
	}

	public static boolean checkLogin(ArrayList<String> uList, String user,
			String pw, String type) {
		for (int i = 0; i < uList.size(); i++) {
			String[] s = uList.get(i).split(";");
			if (s[0].equals(user)) {
				if (s[1].equals(Integer.toString(pw.hashCode()))) {
					if (s[2].equals(type)) {
						return true;
					}
				}
			}
		}
		System.out.println("Login failed. Please try again.");
		return false;
	}

	public static User createUser(String username, String type) {
		User u = null;
		if (type.equals("Student"))
		{
			Student s;
			List studList = Student.getStudentList();
			for(int i=0; i<studList.size();i++)
			{
				s = (Student)studList.get(i);
				if(s.getUsername().equals(username))
				{
					if(s.checkAccessPeriod())
						u = s;
				}
			}
		}
		else
			u = new Admin(username);
		return u;
	}
}