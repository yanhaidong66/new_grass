package top.haidong556.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.math.BigInteger;

@Component
public class OauthLoginFilter implements GatewayFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String requestPath = exchange.getRequest().getPath().toString();
        StringBuffer path=new StringBuffer();
        System.out.println(requestPath);
        ServerWebExchange mutatedExchange=exchange;
        if(requestPath.equals("/chat/login")){
            mutatedExchange= exchange.mutate()
                    .request(exchange.getRequest().mutate().path("/oauth/createJwt").build())
                    .build();
        }
        System.out.println(mutatedExchange.getRequest().getPath());

        return chain.filter(mutatedExchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
