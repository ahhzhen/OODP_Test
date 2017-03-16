import java.util.Scanner;

public abstract class User {
	private String username;
	private String password;
	private String type;
	
	User(String u, String p, String t)
	{
		username = u;
		password = p;
		type = t;
	}
	
	User(String u)
	{
		username = u;
	}
	
	abstract void displayMenu();
	
}
