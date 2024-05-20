package top.haidong556.oauth.config;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.haidong556.oauth.interceptor.LogInterceptor;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry
                .addInterceptor(new LogInterceptor())
                .addPathPatterns("/**");
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
