package ch.heigvd.user.api.interceptor;

import ch.heigvd.user.api.exceptions.ApiException;
import ch.heigvd.user.utils.TokenJwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.SignatureException;

@Component
public class CheckLogin implements HandlerInterceptor {

    @Autowired
    TokenJwt tokenJwt;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ApiException {
        String header = request.getHeader("Authorization");

        if(header == null)
            throw new ApiException(0, "Please Log !");

        System.out.println(header);

        tokenJwt.parseJWT(header);


        return true;
    }
}
