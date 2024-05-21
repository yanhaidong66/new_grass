package top.haidong556.chat_server.config;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
public class MyConfiguration {
    static {
        Properties config = new Properties();
        try {
            InputStream inputStream = MyConfiguration.class.getClassLoader().getResourceAsStream("chat_server.properties");
            config.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MYSELF_CHAT_SERVER_ID=Integer.valueOf(config.getProperty("chat_server_id"));
        DATAID=config.getProperty("nacos_dataId");
        GROUP=config.getProperty("nacos_group");
        NACOS_SERVER_ADDR=config.getProperty("nacos_server_addr");
        NETTY_PORT= config.getProperty("netty_port");
        NETTY_ADDR = config.getProperty("netty_addr");
        ZOOKEEPER_ADDR=config.getProperty("zookeeper_addr");
        CONSISTENT_HASHING_REPLACAS=config.getProperty("consistent_hashing_replicas");
        ROCKETMQ_NAMESRV_ADDR= config.getProperty("rocketmq_namesrv_addr");
        ROCKETMQ_CONSUMER_GROUP= config.getProperty("rocketmq_consumer_group");
        ROCKETMQ_PRODUCER_GROUP= config.getProperty("rocketmq_producer_group");
        ROCKETMQ_TOPIC= config.getProperty("rocketmq_topic");
        ROCKETMQ_CHAT_MESSAGE_MYSELF_TAG= config.getProperty("rocketmq_chat_message_tag")+ config.getProperty("chat_server_id");
        ROCKETMQ_MYSQL_FAIL_TAG=config.getProperty("rocketmq_mysql_fail_tag");
    }
    public static final int MYSELF_CHAT_SERVER_ID;
    public static final String DATAID;
    public static final String NETTY_ADDR;
    public static final String NETTY_PORT;
    public static final String GROUP;
    public static final String NACOS_SERVER_ADDR;
    public static final String ZOOKEEPER_ADDR;
    public static final String CONSISTENT_HASHING_REPLACAS;
    public static final String ROCKETMQ_NAMESRV_ADDR;
    public static final String ROCKETMQ_CONSUMER_GROUP;
    public static final String ROCKETMQ_PRODUCER_GROUP;
    public static final String ROCKETMQ_TOPIC;
    public static final String ROCKETMQ_CHAT_MESSAGE_MYSELF_TAG;
    public static final String ROCKETMQ_MYSQL_FAIL_TAG;

    private static final MyConfiguration instance=new MyConfiguration();
    private MyConfiguration(){};
    public static MyConfiguration getInstance(){
        return instance;
    }


}
