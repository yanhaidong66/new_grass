package top.haidong556.oauth.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;
@Component
public class SetRequestIdFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String requestId = UUID.randomUUID().toString();
        servletRequest.setAttribute("UUID", requestId);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
