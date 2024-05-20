package top.haidong556.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import top.haidong556.chat_server.entity.UserServerMapping;
import top.haidong556.chat_server.repository.MappingRepository;
@Component
public class MatchingServerFilter implements GatewayFilter {
    @Autowired
    MappingRepository mappingRepository;
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        exchange.getRequest().getBody();//获得发送者用户名或者id
                                        //获得接收者的用户名或者id
        UserServerMapping mappingBySenderId = mappingRepository.getMappingByUserId(id);
        UserServerMapping mappingByReciverId = mappingRepository.getMappingByUserId(id);

        if(mappingBySenderId !=null&&mappingByReciverId!=null){
                在请求头中携带两个服务器id
        }
        else if(mappingBySenderId==null){
            从nacos获取服务器列表;
            UserServerMapping newUserServerMapping;
            int effectRows = mappingRepository.addMapping(newUserServerMapping);
            if(effectRows<1){
                throw new
            }

        }
        else if(mappingByReciverId==null){

        }
        return null;
    }
}
