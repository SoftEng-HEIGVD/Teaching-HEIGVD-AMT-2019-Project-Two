package heig.vd.ch.projet.auth.api.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class AuthenticateService implements IAuthenticateService {
    /**
     * Method to hash a password
     * @param password password to hash
     * @return a hashed password
     */
    @Override
    public String hashPassword(String password) {
        return BCrypt.hashpw(password,BCrypt.gensalt());
    }

    /**
     * Method to check password between hash and plaintext password
     * @param password password to check
     * @param hashedPassword hashes password
     * @return true if is correct, otherwise false
     */
    @Override
    public boolean checkPassord(String password, String hashedPassword) {
        try {
            boolean correct = BCrypt.checkpw(password,hashedPassword);
            return correct;
        }catch (Exception e){
            return false;
        }
    }
}
