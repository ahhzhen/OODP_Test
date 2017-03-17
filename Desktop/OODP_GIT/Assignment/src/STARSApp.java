
import java.util.*;
import java.io.Console;
import java.util.regex.Pattern;

public class STARSApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		Console console = System.console();
		String type = "", user = "", pw = "";
		int choice = 0;
		User userSession;
		boolean login = false;
		ArrayList<String> uList = new ArrayList<String>();
		String str = "abdced";
		uList.add("123456;" + str.hashCode()+";Admin");
		uList.add("aaa074;" + "123456".hashCode()+";Student");
		System.out.println("Welcome~\nPlease Login");
		while(login == false)
		{
			while(choice != 1 && choice != 2)
			{
				System.out.println("Please key in user domain: \n1)Student\n2)Admin");
				choice = input.nextInt();	
			}
			if(choice ==1)
				type = "Student";
			else
				type = "Admin";
			
			System.out.print("Please key in username: ");
			user = input.next();
			
			System.out.print("Please key in password: ");
			/*char[] passString = console.readPassword();
			pw = new String(passString);*/
			pw = input.next();
			
			login = checkLogin(uList, user, pw, type);
			choice = 0;
		}
		userSession = createUser(user, type);
		while(choice!= 7)
		{
			userSession.displayMenu();
			System.out.println("Please select choice from menu: ");
			try{choice = input.nextInt();}
			catch(Exception e){input.next();}
			if(userSession instanceof Student)
				studentMenu((Student)userSession,choice);
			else
				adminMenu((Admin)userSession, choice);
		}
	}

	public static boolean checkLogin(ArrayList<String> uList,String user,String pw,String type)
	{
		for(int i = 0; i < uList.size(); i++)
		{
			String[] s = uList.get(i).split(";");
			if(s[0].equals(user))
			{
				if(s[1].equals(Integer.toString(pw.hashCode())))
				{
					if(s[2].equals(type))
					{
						System.out.println("Login Successfully!");
						return true;
					}
				}
			}
		}
		System.out.println("Login failed. Please try again.");
		return false;
	}
		
	public static User createUser(String username, String type)
	{
		User u;
		if(type.equals("Student"))
			u = new Student(username, "U1620736G", "Student1", 'F', "Singaporean");
		else
			u = new Admin(username);
		return u;
	}

	public static void studentMenu(Student stud, int choice)
	{
		switch(choice)
		{
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
		case 6:
			break;
		case 7:
			break;
		default:System.out.println("Wrong input detected, please try again!");
		}
	}
	
	public static void adminMenu(Admin adm, int choice)
	{
		switch(choice)
		{
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
		case 6:
			break;
		case 7:
			break;
		default:System.out.println("Wrong input detected, please try again!");
		}
	}
}




