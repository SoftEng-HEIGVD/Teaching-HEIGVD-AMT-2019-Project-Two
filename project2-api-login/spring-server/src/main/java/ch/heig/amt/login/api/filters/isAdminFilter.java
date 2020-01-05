package ch.heig.amt.login.api.filters;

import ch.heig.amt.login.api.exceptions.ForbiddenException;
import ch.heig.amt.login.api.util.UtilsJWT;
import com.sun.org.apache.xpath.internal.operations.Bool;
import io.jsonwebtoken.Claims;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class isAdminFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String token = request.getHeader("Authorization");
        Claims claims = null;
        Boolean validToken = true;
        try{
            claims = UtilsJWT.decodeJWT(token);
        } catch (Exception e){
            validToken = false;
        }
        if((!validToken || !(boolean)claims.get("isadmin")) && request.getMethod().equals("POST")){
            throw new ForbiddenException("Invalid auth token");
        }

        //call next filter in the filter chain
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
