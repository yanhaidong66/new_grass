package top.haidong556.chat_server.service;

import com.alibaba.nacos.api.exception.NacosException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Properties;

import static java.lang.Thread.sleep;

class NacosServiceTest {
    static NacosService nacosService;
    @BeforeAll
    static void init(){
        nacosService=new NacosService();
    }

    @Test
    void register() throws IOException, NacosException, InterruptedException {
        nacosService.register();
        sleep(10000);
    }

    @Test
    void getConfig() throws IOException, NacosException {
        Properties config = nacosService.getConfig();
        System.out.println(config);

    }

    @Test
    void disconnect() throws InterruptedException, NacosException, IOException {
        nacosService.register();
        sleep(5000);
        nacosService.disconnect();
        sleep(10000);
        nacosService.register();
        sleep(10000);
    }
}