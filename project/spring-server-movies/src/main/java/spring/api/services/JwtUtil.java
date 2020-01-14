package spring.api.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;
import spring.configuration.JwtConfig;

import java.util.Date;

@Component
public class JwtUtil {

    private final static int TOKEN_DURATION = 30 * 100000;

    public String createToken(String username) {
        String token;
        try {
            Algorithm algorithm = Algorithm.HMAC256(JwtConfig.JWT_SHARED_SECRET.getBytes());
            token = JWT.create()
                    .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_DURATION))
                    .withIssuer("Me")
                    .withSubject(username)
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            throw new Error("Could not generate JWT token");
        }
    }

    public DecodedJWT verifyToken(String token) throws JWTVerificationException {
        Algorithm algorithm = Algorithm.HMAC256(JwtConfig.JWT_SHARED_SECRET.getBytes());
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("Me")
                .build(); //Reusable verifier instance
        return verifier.verify(token);
    }

    /**
     * Decode jwt token. This method should be used only after the token has been verified.
     * @param token jwt token
     * @return decoded jwt token
     */
    public DecodedJWT decodeToken(String token) {
        try {
            return JWT.decode(token);
        } catch (JWTDecodeException exception){
            //Invalid token
            throw new Error("Could not decode token");
        }
    }

    public String extractToken(String authHeader) {
        if (authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        } else {
            throw new Error("Header does not have authorization token");
        }
    }
}
