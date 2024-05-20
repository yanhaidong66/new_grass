package top.haidong556.oauth.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import top.haidong556.oauth.service.impl.UserServiceImpl;


@Configuration
public class SecurityConfig  {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http
                                                   ) throws Exception {
        http.authorizeHttpRequests(auth->{
            auth.anyRequest().permitAll();
        });


        http.csrf(auth->{
            auth.disable();
        });


        http.sessionManagement(auth->{
            auth.disable();
                });

        http.rememberMe(auth->{
            auth.disable();
        });

        return http.build();
    }
//    @Bean
//    public AuthenticationManager myAuthenticationManager(DaoAuthenticationProvider myDaoAuthenticationProvider) throws Exception {
//        ProviderManager providerManager=new ProviderManager(myDaoAuthenticationProvider);
//        return providerManager;
//    }
//
//    @Bean
//    public DaoAuthenticationProvider myDaoAuthenticationProvider(UserServiceImpl userService) {
//        final DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setPasswordEncoder(new PasswordEncoder() {
//            @Override
//            public String encode(CharSequence rawPassword) {
//                return rawPassword.toString();
//
//            }
//            @Override
//            public boolean matches(CharSequence rawPassword, String encodedPassword) {
//                return encodedPassword.matches(rawPassword.toString());
//            }
//        });
//        provider.setUserDetailsService(userService);
//        provider.setUserDetailsPasswordService(userService);
//        provider.setHideUserNotFoundExceptions(false);
//        return provider;
//    }


}
