package spring.api.endpoints.interceptors;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import spring.api.exceptions.AuthenticationException;
import spring.api.exceptions.ForbiddenException;
import spring.api.services.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtInterceptor extends HandlerInterceptorAdapter {

    private static Logger log = LoggerFactory.getLogger(JwtInterceptor.class);

    @Autowired
    JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("Verifying user token");

        String authorization = request.getHeader("Authorization");
        if(authorization == null || authorization.isEmpty()) {
            throw new AuthenticationException("Not Authenticated: no token provided");
        }

        try {
            DecodedJWT decodedJWT = jwtUtil.verifyToken(jwtUtil.extractToken(authorization));
            // Set attribute owner for the controllers to handle authorization
            request.setAttribute("owner", decodedJWT.getSubject());

            // reroute blocked users to prevent them from doing operations
            Claim isBlocked = decodedJWT.getClaim("isBlocked");

            if (isBlocked.asBoolean()) {
                throw new ForbiddenException("Not authorized: Your account has been blocked. Please contact System Administrator");
            }

        } catch (JWTVerificationException e) {
            throw new AuthenticationException("Not Authenticated: invalid token, " + e.getMessage());
        }

        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("User token verified");
        super.postHandle(request, response, handler, modelAndView);
    }
}
