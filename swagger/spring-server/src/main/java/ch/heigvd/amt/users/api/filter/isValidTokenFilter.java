package ch.heigvd.amt.users.api.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import
import ch.heigvd.amt.users.api.util.JWTToken;
import io.jsonwebtoken.Claims;
import org.springframework.http.HttpStatus;

public class isValidTokenFilter implements Filter {

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String headerToken = request.getHeader("Authorization");

        try{
            Claims claims = JWTToken.decodeJWT(headerToken);
            request.setAttribute("email", claims.getSubject());
        }catch(Exception e){
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Authentification fail invalid token");
        }


        filterChain.doFilter(servletRequest, servletResponse);

    }
}
