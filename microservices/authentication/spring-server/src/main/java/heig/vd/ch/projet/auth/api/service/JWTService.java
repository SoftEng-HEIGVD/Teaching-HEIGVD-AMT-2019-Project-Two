package heig.vd.ch.projet.auth.api.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import heig.vd.ch.projet.auth.entities.UserEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class JWTService implements IJWTService{

    @Value("${token.secret}")
    private String secret;

    @Override
    public DecodedToken verifyToken(String token)  throws JWTVerificationException,NullPointerException{
        String[] jwt = token.split(" ");

        if (jwt[0].equals("Bearer") && jwt.length == 2){
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                                      .build(); //Reusable verifier instance
            DecodedJWT tokenJWT = verifier.verify(jwt[1]);

            String email = tokenJWT.getClaim("email").asString();
            String role = tokenJWT.getClaim("role").asString();

            return DecodedToken.builder().email(email).role(role).build();
        }else{
            throw new JWTVerificationException("Not a bearer token");
        }
    }

    @Override
    public String createToken(UserEntity userEntity) {
        //Create the token
        Algorithm algorithm = Algorithm.HMAC256(secret);
        String jwttoken = JWT.create()
                .withClaim("email",userEntity.getEmail())
                .withClaim("role",userEntity.getRole())
                .sign(algorithm);

        return "Bearer " + jwttoken;
    }
}
