package ch.heigvd.amt.users.api.util;

import org.mindrot.jbcrypt.BCrypt;

public class HashPassword {
    // Inspired by this : https://www.stubbornjava.com/posts/hashing-passwords-in-java-with-bcrypt
    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean verifyHash(String password, String hash){
        return BCrypt.checkpw(password, hash);
    }
}
