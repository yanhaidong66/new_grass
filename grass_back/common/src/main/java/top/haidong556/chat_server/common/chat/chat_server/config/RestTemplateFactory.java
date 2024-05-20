package top.haidong556.chat_server.common.chat.chat_server.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;


public class RestTemplateFactory {

    public static  RestTemplate getRestTemplate(){
        return new RestTemplateBuilder()
                .setConnectTimeout(Duration.ofMillis(3000))
                .setReadTimeout(Duration.ofMillis(3000))
                .build();
    }
}
