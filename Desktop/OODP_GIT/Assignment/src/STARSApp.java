
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
		Student stud;
		boolean login = false;
		ArrayList<String> uList = new ArrayList<String>();
		String str = "abdced";
		uList.add("123456;" + str.hashCode()+";Admin");
		uList.add("aud0074;" + "abd645".hashCode()+";Student");
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
		if(type.equals("Student"))
		{
			stud = createStudent(user);
			while(choice != 0)
			{
				displayStudentMenu();
				
			}
		}
		else
		{
			
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

	public static void displayStudentMenu()
	{
		System.out.println("");
		System.out.println("1. Add Course");
		System.out.println("2. Drop Course");
		System.out.println("3. Check Courses Registered");
		System.out.println("4. Check vacancies available");
		System.out.println("5. Change index number of a course");
		System.out.println("6. Swop index number with another student");
		System.out.println("7. Quit");
	}
	
	public static Student createStudent(String username)
	{
		Student stud = new Student(username, "U1620736G", "Student1", 'F', "Singaporean");
		return stud;
		
		///yiu don comment anyhow
	}
}




