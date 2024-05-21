package top.haidong556.chat_server;


import com.alibaba.nacos.api.exception.NacosException;
import top.haidong556.chat_server.common.GlobalContext;
import top.haidong556.chat_server.config.MyConfiguration;
import top.haidong556.chat_server.service.*;

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
        ConsistentHashLoadBalancerService.getInstance().putNode(String.valueOf(MyConfiguration.MYSELF_CHAT_SERVER_ID));
        RocketmqService rocketmqService=RocketmqService.getInstance();
        Thread rocketmqThread=new Thread(rocketmqService);
        rocketmqThread.start();
        try {
            rocketmqThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        int nettyPort= Integer.parseInt(NETTY_PORT);
        WebSocketService socketServer=WebSocketService.getInstance();
        socketServer.run(nettyPort);

    }
}
