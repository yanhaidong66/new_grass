package top.haidong556.chat_server;


import com.alibaba.nacos.api.exception.NacosException;
import top.haidong556.chat_server.common.GlobalContext;
import top.haidong556.chat_server.service.NacosService;
import top.haidong556.chat_server.service.RocketmqService;
import top.haidong556.chat_server.service.WebSocketService;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static top.haidong556.chat_server.config.MyConfiguration.NETTY_PORT;

public class ChatServerApplication {
    private GlobalContext context=GlobalContext.getInstance();

    public static void main(String[] args) throws IOException, NacosException {

//        NacosService nacosService=new NacosService();
//        Properties config = nacosService.getConfig();
//        boolean register = nacosService.register();
        RocketmqService rocketmqService=RocketmqService.getInstance();
        Thread rocketmqThread=new Thread(rocketmqService);
        rocketmqThread.start();
        int nettyPort= Integer.parseInt(NETTY_PORT);
        WebSocketService socketServer=new WebSocketService();
        socketServer.run(nettyPort);
        try {
            rocketmqThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
