package spring.api.endpoints.interceptors;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import spring.api.util.jwt.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AdminInterceptor extends HandlerInterceptorAdapter {

    private static Logger log = LoggerFactory.getLogger(JwtInterceptor.class);

    @Autowired
    JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            String authorization = request.getHeader("Authorization");
            if(authorization == null || authorization.isEmpty()) {
                throw new Error("No token provided");
            }
            String token = jwtUtil.extractToken(authorization);
            DecodedJWT decodedJWT = jwtUtil.verifyToken(token);

            Claim isAdmin = decodedJWT.getClaim("isAdmin");
            if(isAdmin.asBoolean()) {
                return super.preHandle(request, response, handler);
            }
        } catch (JWTVerificationException e) {
            throw new Error("Could not verify admin token");
        }
        return false;
    }
}
