package ch.heigvd.user.api.interceptor;

import ch.heigvd.user.api.exceptions.ApiException;
import ch.heigvd.user.utils.TokenJwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CheckUpdateUser implements HandlerInterceptor {

    @Autowired
    TokenJwt tokenJwt;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ApiException {
        String token = request.getHeader("Authorization");

        String mailLogger = tokenJwt.getMailByToken(token);
        System.out.println(request.getServletPath());
        System.out.println(request.getContextPath());
        System.out.println(request.getHeaderNames());

       // String mailuser = request.getC
        if(request.getMethod().equals("POST") && !tokenJwt.IsAdmin(token))
            throw new ApiException(0, "You are not an admin !");
        return true;
    }
}
