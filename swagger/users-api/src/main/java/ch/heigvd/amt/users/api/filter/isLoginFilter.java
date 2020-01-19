package ch.heigvd.amt.users.api.filter;

import ch.heigvd.amt.users.api.exceptions.ApiException;
import ch.heigvd.amt.users.api.util.JWTToken;
import io.jsonwebtoken.Claims;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class isLoginFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException{
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String jwtToken = request.getHeader("Authorization");
        Boolean isValidToken = true;
        try{
            Claims claims = JWTToken.decodeJWT(jwtToken);
            request.setAttribute("email", claims.getSubject());
        }catch (Exception e){
            isValidToken = false;
        }

        if(isValidToken == false){
            throw new IOException("Invalid token login");
        }

        filterChain.doFilter(request,response);
    }

}
