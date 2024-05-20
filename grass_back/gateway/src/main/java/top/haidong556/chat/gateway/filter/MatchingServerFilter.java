package top.haidong556.chat.gateway.filter;

import com.alibaba.nacos.api.naming.pojo.Instance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import top.haidong556.chat.gateway.entity.JwtUserMessage;
import top.haidong556.chat.gateway.entity.UserServerMapping;
import top.haidong556.chat.gateway.repository.MappingRepository;
import top.haidong556.chat_server.common.util.NacosServiceUtil;

import java.net.URI;
import java.util.List;

/**
 * @when 除了登录之外的都需要使用
 * @description 设置这个用户派发到哪个chat_server服务器，通过给请求头添加字段server_id实现，具体来讲，查找数据库看之前有没有给用户分配过服务器，如果分配过且这个服务器现在nacos在线，则将server_id设置为这个服务器，如果没有分配过或者分配的服务器不在线，则重新分配服务器，将server_id设置为新分配的服务器
 */
@Component
public class MatchingServerFilter implements GatewayFilter {
    @Autowired
    MappingRepository mappingRepository;
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        URI newUri=null;
        JwtUserMessage jwtUserMessage = exchange.getAttribute("jwtUserMessage");
        UserServerMapping mappingBySenderId = mappingRepository.getMappingByUserId(jwtUserMessage.getUserId());
        if(mappingBySenderId !=null){
            String serviceName="chat_server_"+mappingBySenderId.getServerId();
            List<Instance> services = NacosServiceUtil.getServiceStatus(serviceName);
            if(services!=null){
                String targetServiceName = services.get(0).getServiceName();
                String targetUri = "lb://" + targetServiceName;
                newUri = URI.create(targetUri);
                ServerHttpRequest request = exchange.getRequest().mutate()
                        .uri(newUri)
                        .build();
                ServerWebExchange newExchange = exchange.mutate().request(request).build();
                return chain.filter(newExchange);
            }
        }

        Instance minUserChatServer = getMinUserChatServer();
        UserServerMapping newUserServerMapping=new UserServerMapping();
        newUserServerMapping.setServerId(Integer.valueOf(NacosServiceUtil.extractSuffixFromServiceName(minUserChatServer.getServiceName())));
        newUserServerMapping.setUserId(jwtUserMessage.getUserId());
        newUserServerMapping.setUserName(jwtUserMessage.getUserName());
        int effectRows = mappingRepository.addMapping(newUserServerMapping);
        if(effectRows<1){
            throw new RuntimeException("add mapping wrong");
        }
        String targetServiceName = minUserChatServer.getServiceName();
        String targetUri = "lb://" + targetServiceName;
        newUri = URI.create(targetUri);
        ServerHttpRequest request = exchange.getRequest().mutate()
                .uri(newUri)
                .build();
        ServerWebExchange newExchange = exchange.mutate().request(request).build();
        return chain.filter(newExchange);
    }





    private Instance getMinUserChatServer(){
        List<Instance> chatServerInstances = NacosServiceUtil.getServiceInstancesWithPrefix("chat_server");
        Instance minUserServer=null;
        int minServerUserCount=Integer.MAX_VALUE;
        if (chatServerInstances != null) {
            for (Instance instance : chatServerInstances) {
                String serviceName = instance.getServiceName();
                String suffix = NacosServiceUtil.extractSuffixFromServiceName(serviceName);
                int serverId=Integer.valueOf(suffix);
                int countUsersUsingChatServer = mappingRepository.countUsersUsingChatServer(serverId);
                if(countUsersUsingChatServer<minServerUserCount){
                    minUserServer=instance;
                    minServerUserCount=countUsersUsingChatServer;
                }
            }
            return minUserServer;
        }else{
            throw new RuntimeException("The list of available servers is empty");
        }
    }

}

