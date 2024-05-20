package top.haidong556.oauth.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


import java.io.IOException;

/**
 * @author 01
 * @program wrapper-demo
 * @description 替换HttpServletRequest
 * @create 2018-12-24 21:04
 * @since 1.0
 **/
@Slf4j
@Component
public class ReplaceRequestInputStreamFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            if ("POST".equals(httpRequest.getMethod())) {
                if(request instanceof RequestWrapper){
                    chain.doFilter(request, response);
                }else{
                    ServletRequest requestWrapper = new RequestWrapper((HttpServletRequest) request);
                    chain.doFilter(requestWrapper, response);
                }
            }else {
                chain.doFilter(request, response);
            }
        }
        else{
            chain.doFilter(request, response);

        }
    }

}