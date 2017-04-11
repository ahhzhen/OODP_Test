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
		//Console console;
		InitFiles.init();
		
		System.out.println("Welcome~\nPlease Login");
		
		while (login == false) {
			while (choice != 1 && choice != 2) {
				System.out.println("Please key in user domain: \n1)Student\n2)Admin");
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

			login = checkLogin(user, pw, type);
			choice = -1;
		}
		userSession = createUser(user, type);
		if(userSession!= null){
			System.out.println("Login Successfully!");
			userSession.startSession();
		}
	}

	public static boolean checkLogin(String user, String pw, String type) {
		List userList = User.getUserList(); 
		PasswordHash pwHash = new PasswordHash();
		for (int i = 0; i < userList.size(); i++) {
			User u = (User)userList.get(i);
			if (u.getUsername().equals(user))
				if (u.getPassword().equals(pwHash.hash(pw,user)))
					if (u.getType().equals(type))
						return true;
		}
		System.out.println("Login failed. Please try again.");
		return false;
	}

	public static User createUser(String username, String type) {
		User u = null;
		List userList = User.getUserList();
		for(int i = 0; i <userList.size();i++)
		{
			User user = (User)userList.get(i);
			if(user.getUsername().equals(username) && user.getType().equals(type)) {
				if(type.equals("Student"))
				{
					Student s = (Student)user;
					if(s.checkAccessPeriod())
						return user;
				}
				else
					return user;
			}
		}
		return u;
	}
}