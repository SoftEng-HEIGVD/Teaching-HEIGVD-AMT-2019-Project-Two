//Source: https://developer.okta.com/blog/2018/10/31/jwts-with-java

package ch.heigvd.amt.users.api.util;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;

import ch.heigvd.amt.users.api.model.UserLogin;
import ch.heigvd.amt.users.entities.UserEntity;
import ch.heigvd.amt.users.repositories.UserRepository;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class JWTToken {
    @Autowired
    UserRepository userRepository;

    final static String SECRET = "secret";

    public String createJWT(UserLogin credentials){
        UserEntity userEntity = userRepository.findByMail(credentials.getEmail());

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        Date currentDate = new Date();
        Date expirationTime = new Date(currentDate.getTime() + (24 * 3600 * 1000));


        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        JwtBuilder builder = Jwts.builder().claim("email", userEntity.getEmail()).claim("administrator", userEntity.getAdministrator())
                .setIssuedAt(currentDate).setExpiration(expirationTime).signWith(signatureAlgorithm, signingKey);

        return builder.compact();
    }

    public boolean isTokenValid(Claims decodeJWT){
        String email = decodeJWT.getSubject();
        if(userRepository.findByMail(email) != null){
            return true;
        }
        return false;
    }

    public Claims decodeJWT(String jwt){
        Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(SECRET)).parseClaimsJws(jwt).getBody();
        return claims;
    }
}
