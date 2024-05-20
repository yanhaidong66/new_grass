package top.haidong556.chat.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import top.haidong556.chat.gateway.filter.CorsFilter;
import top.haidong556.chat.gateway.filter.OauthLoginFilter;
import top.haidong556.chat.gateway.filter.HttpVerificationFilter;
import top.haidong556.chat.gateway.filter.WebsocketVerificationFilter;


@Configuration
public class RouteConfig {


    @Bean
    public RouteLocator myRoute(RouteLocatorBuilder builder
            , HttpVerificationFilter httpVerificationFilter
            , WebsocketVerificationFilter websocketVerificationFilter
            , CorsFilter corsFilter
            , OauthLoginFilter oauthLoginFilter){
        return builder.routes()
                .route("oauth_login_server",predicateSpec -> {
                    return predicateSpec.path("/chat/login")
                            .and()
                            .method("POST")
                           .filters(f -> f.filter(httpVerificationFilter).filter(oauthLoginFilter))
                            .uri("lb://oauth-server/createJwt");
                })
                .route("chat_server_http",predicateSpec -> {
                    return predicateSpec.method("GET","POST")
                            .and().path("/chat/**")
                            .filters(f -> f.filter(httpVerificationFilter).filter(corsFilter))
                            .uri("lb://oauth-server/verify");
                })
                .route("chat_server_websocket",predicateSpec -> {
                    return predicateSpec.path("/chat/**")
                            .filters(f->f.filter(websocketVerificationFilter))
                            .uri("lb://");
                }).build();
    }


}
