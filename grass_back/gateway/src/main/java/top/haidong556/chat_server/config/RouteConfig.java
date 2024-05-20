package top.haidong556.chat_server.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import top.haidong556.filter.VerificationFilter;
import top.haidong556.filter.CorsFilter;
import top.haidong556.filter.OauthLoginFilter;


@Configuration
public class RouteConfig {


    @Bean
    public RouteLocator myRoute(RouteLocatorBuilder builder
            , VerificationFilter verificationFilter
            , CorsFilter corsFilter
            , OauthLoginFilter oauthLoginFilter){
        return builder.routes()
                .route("oauth_login_server",predicateSpec -> {
                    return predicateSpec.path("/chat/login")
                            .and()
                            .method("POST")
                           .filters(f -> f.filter(verificationFilter).filter(oauthLoginFilter))
                            .uri("lb://oauth-server/oauth/jwt/creatJwt")
                            ;
                })
                .route("chat_server",predicateSpec -> {
                    return predicateSpec.method("GET","POST")
                            .and().path("/chat")
                            .filters(f -> f.filter(verificationFilter).filter(corsFilter))
                            .uri("lb://chat-server");
                })
                .build();
    }


}
