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
        MYSELF_CHAT_SERVER_ID = Integer.valueOf(config.getProperty("chat_server_id", "1"));
        DATAID = config.getProperty("nacos_dataId", "chat-server.properties");
        GROUP = config.getProperty("nacos_group", "DEFAULT_GROUP");
        NACOS_SERVER_ADDR = config.getProperty("nacos_server_addr", "http://localhost:8848");
        NETTY_PORT = config.getProperty("netty_port", "5001");
        NETTY_ADDR = config.getProperty("netty_addr", "localhost");
        ZOOKEEPER_ADDR = config.getProperty("zookeeper_addr", "localhost:2000");
        CONSISTENT_HASHING_REPLACAS = config.getProperty("consistent_hashing_replicas", "2");
        ROCKETMQ_NAMESRV_ADDR = config.getProperty("rocketmq_namesrv_addr", "localhost:9876");
        ROCKETMQ_CONSUMER_GROUP = config.getProperty("rocketmq_consumer_group", "NettyServerConsumerGroup");
        ROCKETMQ_PRODUCER_GROUP = config.getProperty("rocketmq_producer_group", "NettyServerProducerGroup");
        ROCKETMQ_TOPIC = config.getProperty("rocketmq_topic", "ChatServer");
        ROCKETMQ_CHAT_MESSAGE_TAG_PREFIX = config.getProperty("rocketmq_chat_message_tag_prefix", "ChatMessageTag") ;
        ROCKETMQ_MYSQL_FAIL_TAG = config.getProperty("rocketmq_mysql_fail_tag", "MysqlUpdateFailTag");
        ZOOKEEPER_SERVER_NODE_PATH = config.getProperty("zookeeper_server_node_path", "/chat_server/server");
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
    public static final String ROCKETMQ_CHAT_MESSAGE_TAG_PREFIX;
    public static final String ROCKETMQ_MYSQL_FAIL_TAG;
    public static final String ZOOKEEPER_SERVER_NODE_PATH;

    private static final MyConfiguration instance=new MyConfiguration();
    private MyConfiguration(){};
    public static MyConfiguration getInstance(){
        return instance;
    }


}
