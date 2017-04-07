import java.io.Serializable;
import java.util.Scanner;

public abstract class User implements Serializable {
	private String username;
	private String password;
	private String type;
	
	User(){}
	
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
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	abstract void startSession();

	
}
