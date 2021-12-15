package utility;

import org.apache.commons.codec.digest.DigestUtils;

public class PasswordEncryption {
    
    public static String generateHash(String password)
    {
        String sha256hex = DigestUtils.sha256Hex(password);
        
        return sha256hex;
    }
    
    public static boolean validatePassword(String originalPassword, String storedPassword)
    {
        String sha256hex = DigestUtils.sha256Hex(originalPassword);
        
        if(sha256hex.equals(storedPassword))
            return true;
        
        return false;
    }
}
