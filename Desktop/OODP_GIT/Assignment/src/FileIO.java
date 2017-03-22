import java.io.*;
import java.util.*;

public class FileIO {
	public static List readInFile(String filename)
	{
		List fileDetails = null;
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try{
			fis = new FileInputStream(filename);
			in = new ObjectInputStream(fis);
			fileDetails = (ArrayList<?>) in.readObject();
			in.close();
		}catch(IOException ex){}
		catch(ClassNotFoundException ex){}
		return fileDetails;
	}
	
	public static boolean writeToFile(String filename, List list)
	{
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try{
			fos = new FileOutputStream(filename);
			out = new ObjectOutputStream(fos);
			out.writeObject(list);
			out.close();
			return true;
		}
		catch(IOException ex){
			ex.printStackTrace();
			return false;
		}
	}
}
