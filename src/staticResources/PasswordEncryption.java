package staticResources;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncryption {
	
	/**
	 * @return a random salt
	 */
	public static String createSalt(){
	    String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	    String salt = "";
	    for(int x=0;x<10;x++)
	    {
	       int i = (int)Math.floor(Math.random() * 62);
	       salt += chars.charAt(i);
	    }
		return salt;
	}
	
	
	/**
	 * @param word
	 * @return the hash of word
	 */
	private static String hash(String word){
		byte[] hash;
		String result;
		
		try{
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(word.getBytes());
			hash = md.digest();
			// digest creation and conversion to hex
			result = new BigInteger(1, hash).toString(16); 
		}catch(NoSuchAlgorithmException clr){
			clr.printStackTrace();
			result = "NoSuchAlgorithmException";
		}
		return result;
	}
	
	/**
	 * @param word
	 * @param salt
	 * @return the hash salted of word
	 */
	public static String encryptPassword(String password, String salt){
		return hash(password+salt);
	}
}
