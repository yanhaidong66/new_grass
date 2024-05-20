package top.haidong556.chat_server.config;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class RouteConfigTest {

    @Autowired
    RestTemplate restTemplate;

    @Test
    void myRestTemplate() {
        String forObject = restTemplate.getForObject("http://deal-server/config/get", String.class);
        System.out.println(forObject);

    }
}