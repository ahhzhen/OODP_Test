import java.security.*;
import java.math.BigInteger;

public final class PasswordHash{
	public PasswordHash(){}
	
	public String hash(String password, String salt) {
		String md5 = null;
		if(null == password) return null;
		
		try{
			MessageDigest digest = MessageDigest.getInstance("MD5");
			
			String str = salt + password;
			digest.update(str.getBytes(), 0, str.length());
			
			md5 = new BigInteger(1, digest.digest()).toString();
		} 
		catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return md5;
	}
}