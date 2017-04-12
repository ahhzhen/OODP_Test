import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private String type;
	
	User(){}
	
	User(String username, String password, String type) {
		this.username = username;
		this.password = password;
		this.type = type;
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
	
	public static List getUserList() {
		return getUserList("userList.dat");
	}
	
	public static List getUserList(String filename) {
		List list = null;
		try {
			list = FileIO.readInFile(filename);
		} catch (Exception e) {
		}
		if (list == null)
			list = new ArrayList();
		return list;
	}
	
	public void saveUserList(List list) {
		FileIO.writeToFile("userList.dat", list);
	}

	public void addNewUserToFile() {
		List list = getUserList();
		list.add(this);
		saveUserList(list);
	}
	
	public abstract void startSession();
}
