package top.haidong556.oauth.interceptor;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import top.haidong556.oauth.config.RequestWrapper;


import java.io.*;
import java.util.UUID;

@Component
@Slf4j
public class LogInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String ipAddress = request.getRemoteAddr();
        String uuid = (String)request.getAttribute("UUID");
        request.setAttribute("startTime",System.currentTimeMillis());
        // 如果是 POST 请求，记录请求体
        if ("POST".equals(method)) {
            RequestWrapper requestWrapper;
            requestWrapper=(RequestWrapper) request;
            String body=requestWrapper.getBodyString();
            log.info("RequestUUID:{},Received {} request to {} from {} Request body: {}", uuid, method, uri, ipAddress,body);
            return HandlerInterceptor.super.preHandle(requestWrapper,response,handler);
        }else{
            log.info("RequestUUID:{},Received {} request to {} from {}", uuid,method, uri, ipAddress);
            return true;
        }
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        int responseState=response.getStatus();
        long startTime = (long) request.getAttribute("startTime");
        String uuid=(String)request.getAttribute("UUID");
        long endTime=System.currentTimeMillis();
        log.info("RequestUUID:{},response state:{} and request cost timeMillis:{}",uuid,responseState,endTime-startTime);
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
