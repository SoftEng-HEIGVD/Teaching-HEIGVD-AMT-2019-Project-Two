package spring.api.endpoints.interceptors;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import spring.api.exceptions.AuthenticationException;
import spring.api.exceptions.ForbiddenException;
import spring.api.services.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {

    private static Logger log = LoggerFactory.getLogger(JwtInterceptor.class);

    @Autowired
    JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("Authorizing user");
        try {
            String authHeader = request.getHeader("Authorization");
            if(authHeader == null || authHeader.isEmpty()) {
                throw new AuthenticationException("Not authenticated");
            }
            String jwtToken = jwtUtil.extractToken(authHeader);
            DecodedJWT decodedJWT = jwtUtil.verifyToken(jwtToken);

            Claim isBlocked = decodedJWT.getClaim("isBlocked");

            if (isBlocked.asBoolean()) {
                throw new ForbiddenException("Not authorized: Your account has been blocked. Please contact System Administrator");
            }

            // decoded jwt token has username of the user as subject
            request.setAttribute("owner", decodedJWT.getSubject());
            return super.preHandle(request, response, handler);
        } catch (JWTVerificationException e) {
            throw new AuthenticationException(e.getMessage());
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("Authorized user");
        super.postHandle(request, response, handler, modelAndView);
    }
}
