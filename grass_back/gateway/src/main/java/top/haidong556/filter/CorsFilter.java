package top.haidong556.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
@Component
public class CorsFilter implements GatewayFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        exchange.getResponse().getHeaders().add("Access-Control-Allow-Origin", "*");
        exchange.getResponse().getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        exchange.getResponse().getHeaders().add("Access-Control-Allow-Headers", "Content-Type, Authorization");
        System.out.println("cors");
        // 如果是预检请求（OPTIONS 请求），直接返回 OK 状态
        if (exchange.getRequest().getMethod().equals("OPTIONS")) {
            exchange.getResponse().setStatusCode(HttpStatus.OK);
            return Mono.empty();
        }
        // 对于非预检请求，继续执行下一个过滤器
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
