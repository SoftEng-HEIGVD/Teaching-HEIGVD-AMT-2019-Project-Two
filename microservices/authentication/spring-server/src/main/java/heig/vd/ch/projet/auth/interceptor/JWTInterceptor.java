package heig.vd.ch.projet.auth.interceptor;

import com.auth0.jwt.exceptions.JWTVerificationException;
import heig.vd.ch.projet.auth.api.service.DecodedToken;
import heig.vd.ch.projet.auth.api.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JWTInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    JWTService jwtService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try{
            String token = request.getHeader("Authorization");
            DecodedToken decodedToken = jwtService.verifyToken(token);
            request.setAttribute("decodedToken",decodedToken);
            return true;
        }catch (JWTVerificationException | NullPointerException exception){
            //Return an unauthorized status (401)
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
    }
}
