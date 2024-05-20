package top.haidong556.oauth.config;

import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Autowired
    ReplaceRequestInputStreamFilter replaceRequestInputStreamFilter;

    @Autowired
    SetRequestIdFilter setRequestIdFilter;
    @Bean
    public FilterRegistrationBean filterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(replaceRequestInputStreamFilter);
        registration.setFilter(setRequestIdFilter);
        registration.setName("filterRegistration");
        return registration;
    }


}