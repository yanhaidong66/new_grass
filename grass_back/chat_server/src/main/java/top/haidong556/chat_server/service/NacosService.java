package top.haidong556.chat_server.service;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;


import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.Properties;

import static top.haidong556.chat_server.config.MyConfiguration.*;

public class NacosService {
    private volatile ConfigService configService=null;
    private volatile NamingService namingService=null;
    private boolean createNamingService() throws NacosException {
        if(namingService==null){
            synchronized (this){
                namingService = NamingFactory.createNamingService(NACOS_SERVER_ADDR);

            }
        }
        return true;
    }
    private boolean createConfigService() throws NacosException {
        Properties properties = new Properties();
        properties.put("serverAddr", NACOS_SERVER_ADDR);
        if(configService==null){
            synchronized (this){
                if(configService==null)
                    configService = NacosFactory.createConfigService(properties);
            }
        }
        return true;
    }


    public boolean register() throws NacosException, IOException {
        createNamingService();

        Instance instance = new Instance();
        instance.setIp(NETTY_ADDR);
        instance.setPort(Integer.parseInt(NETTY_PORT));
        instance.setHealthy(true);
        instance.setServiceName("chat-server");
        instance.setWeight(2.0);


        namingService.registerInstance("chat-server", instance);
        return true;
    }

    public Properties getConfig() throws NacosException, IOException {
        Properties config=new Properties();
        createConfigService();
        String content = configService.getConfig(DATAID, GROUP, 5000);
        StringReader stringReader=new StringReader(content);
        config.load(stringReader);
        return config;
    }

    public boolean disconnect() throws NacosException {
        namingService.deregisterInstance("chat-server",NETTY_ADDR,Integer.parseInt(NETTY_PORT),"DEFAULT");
        return true;
    }



}
