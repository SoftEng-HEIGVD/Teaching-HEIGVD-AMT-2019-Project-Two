package spring.api.endpoints.interceptors;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import spring.api.exceptions.NotAuthenticatedException;
import spring.api.exceptions.NotFoundException;
import spring.api.util.jwt.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtInterceptor extends HandlerInterceptorAdapter {

    private static Logger log = LoggerFactory.getLogger(JwtInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("Verifying user token");

        String authorization = request.getHeader("Authorization");
        if(authorization == null || authorization.isEmpty()) {
            throw new Error("Authentication failed, no token provided");
        }

        try {
            JwtUtil.verifyToken(JwtUtil.extractToken(authorization));
        } catch (JWTVerificationException e) {
            throw new NotAuthenticatedException(401, "Not Authenticated: invalid token");
        }

        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("User token verified");
        super.postHandle(request, response, handler, modelAndView);
    }
}
