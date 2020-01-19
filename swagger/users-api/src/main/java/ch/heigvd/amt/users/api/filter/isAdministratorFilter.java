package ch.heigvd.amt.users.api.filter;

import ch.heigvd.amt.users.api.util.JWTToken;
import io.jsonwebtoken.Claims;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class isAdministratorFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException{
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String jwtToken = request.getHeader("Authorization");
        Boolean isValidToken = true;
        Claims claims = null;
        try{
            claims = JWTToken.decodeJWT(jwtToken);
        } catch(Exception e){
            isValidToken = false;
        }

        if((!isValidToken || !(boolean) claims.get("administrator")) && request.getMethod().equals("POST")){
            throw new IOException("Invalid token login");
        }
        filterChain.doFilter(request, response);
    }
}
