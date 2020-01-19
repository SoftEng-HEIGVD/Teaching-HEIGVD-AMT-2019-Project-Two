package ch.heigvd.amt.users.api.util;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class HashPassword {
    // Inspired by this : https://www.stubbornjava.com/posts/hashing-passwords-in-java-with-bcrypt
    public String hashPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean checkPassword(String password, String hashPassword){
        try{
            return BCrypt.checkpw(password, hashPassword);
        }catch (Exception e){
            return false;
        }
    }
}
