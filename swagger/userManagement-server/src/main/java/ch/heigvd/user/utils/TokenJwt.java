package ch.heigvd.user.utils;

import ch.heigvd.user.entities.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.validation.Valid;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenJwt {


    long ttlMillis = 21600;

    //@Value("${secretKey}")
    private String secret_key = "22051993_robel_teklehaimanot_amt";
    Map<String,Object> claims = new HashMap<>();


    public Boolean IsAdmin(String token){
        return Jwts.parser().setSigningKey(secret_key).parseClaimsJws(token).getBody().get("isAdmin",Boolean.class);
    }

    public String getMailByToken(String token){
        return Jwts.parser().setSigningKey(secret_key).parseClaimsJws(token).getBody().get("email",String.class);
    }

    public String createJWT(UserEntity user) {

        claims.put("isAdmin", user.isIsAdmin());

        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secret_key);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());



        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setClaims(claims)
                .setSubject(user.getEmail())
                .signWith(signatureAlgorithm, signingKey);

        //if it has been specified, let's add the expiration
        if (ttlMillis > 0) {
            long expMillis = nowMillis * ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }


    public void parseJWT(String jwt) {

        //This line will throw an exception if it is not a signed JWS (as expected)
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(secret_key))
                .parseClaimsJws(jwt).getBody();
        System.out.println("email: " + claims.getSubject());
        System.out.println("Expiration: " + claims.getExpiration());
    }
}
