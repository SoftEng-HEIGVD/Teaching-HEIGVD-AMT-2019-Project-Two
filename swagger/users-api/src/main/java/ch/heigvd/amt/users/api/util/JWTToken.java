//Source: https://developer.okta.com/blog/2018/10/31/jwts-with-java

package ch.heigvd.amt.users.api.util;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;

import ch.heigvd.amt.users.api.exceptions.ApiException;
import ch.heigvd.amt.users.api.model.UserLogin;
import ch.heigvd.amt.users.entities.UserEntity;
import ch.heigvd.amt.users.repositories.UserRepository;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTToken {

    @Autowired
    UserRepository userRepository;

    @Autowired
    HashPassword hashPassword;

    final static String SECRET = "secret";

    public String createJWT(UserLogin credentials) throws ApiException {
        UserEntity userEntity = userRepository.findByEmail(credentials.getEmail());

        if(userEntity == null || !hashPassword.checkPassword(credentials.getPassword(), userEntity.getPassword())){
            throw new ApiException(400, "Invalid email/password");
        }

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        Date currentDate = new Date();
        Date expirationTime = new Date(currentDate.getTime() + (24 * 3600 * 1000));


        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        JwtBuilder builder = Jwts.builder().setSubject(userEntity.getEmail()).claim("administrator", userEntity.getAdministrator())
                .setIssuedAt(currentDate).setExpiration(expirationTime).signWith(signatureAlgorithm, signingKey);

        return builder.compact();
    }

    public static Claims decodeJWT(String jwt){
        Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(SECRET)).parseClaimsJws(jwt).getBody();
        return claims;
    }
}