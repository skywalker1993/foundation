package me.huang.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationServiceException;

import javax.servlet.*;
import java.io.IOException;

@Slf4j
public class CustomAuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String permit_code = servletRequest.getParameter("permit_code");
        log.info("accessing custom filter, permit_code is {}", permit_code);
        if(!permit_code.equals("hello-security")) {
            throw new AuthenticationServiceException("Authentication item needed");
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
