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
public class AdminInterceptor extends HandlerInterceptorAdapter {

    private static Logger log = LoggerFactory.getLogger(AdminInterceptor.class);

    @Autowired
    JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("Authorizing admin");
        try {
            String authorization = request.getHeader("Authorization");
            if(authorization == null || authorization.isEmpty()) {
                throw new AuthenticationException("No token provided");
            }
            String token = jwtUtil.extractToken(authorization);
            DecodedJWT decodedJWT = jwtUtil.verifyToken(token);

            Claim isAdmin = decodedJWT.getClaim("isAdmin");
            if(isAdmin.asBoolean()) {
                return super.preHandle(request, response, handler);
            } else {
                throw new ForbiddenException("Not authorized: This operation is only reserved for system administrator");
            }
        } catch (JWTVerificationException e) {
            throw new AuthenticationException("Could not verify admin token: " + e.getMessage());
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("Admin authorized");
        super.postHandle(request, response, handler, modelAndView);
    }
}
