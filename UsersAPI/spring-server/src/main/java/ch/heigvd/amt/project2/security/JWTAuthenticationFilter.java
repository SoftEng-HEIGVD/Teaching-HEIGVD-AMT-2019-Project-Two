package ch.heigvd.amt.project2.security;

import ch.heigvd.amt.project2.configuration.MyGrantedAuthority;
import com.auth0.jwt.JWT;
import org.json.HTTP;
import org.json.JSONObject;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static ch.heigvd.amt.project2.security.SecurityConstants.EXPIRATION_TIME;
import static ch.heigvd.amt.project2.security.SecurityConstants.HEADER_STRING;
import static ch.heigvd.amt.project2.security.SecurityConstants.SECRET;
import static ch.heigvd.amt.project2.security.SecurityConstants.TOKEN_PREFIX;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            BufferedReader reader = req.getReader();
            StringBuffer jb = new StringBuffer();
            String line = null;

            while ((line = reader.readLine()) != null)
                jb.append(line);

            JSONObject jsonObject = new JSONObject(HTTP.toJSONObject(jb.toString()).getString("Method"));
            String username = jsonObject.getString("username");
            String password = jsonObject.getString("password");

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            username,
                            password,
                            new ArrayList<>())
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        String token = JWT.create()
                .withSubject(makeJWTSubject(((User) auth.getPrincipal()).getAuthorities()))
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(SECRET.getBytes()));
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
    }

    private String makeJWTSubject(Collection<GrantedAuthority> array) {
        String res = "";
        for(GrantedAuthority ga : array) {
            res += ga.getAuthority()+";";
        }
        return res;
    }
}
