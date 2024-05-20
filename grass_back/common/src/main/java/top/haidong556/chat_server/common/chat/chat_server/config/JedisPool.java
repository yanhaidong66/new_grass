package top.haidong556.chat_server.common.chat.chat_server.config;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;
/**
 *
 */
public class JedisPool {

    private static redis.clients.jedis.JedisPool pool;
    private static JedisPool instance;

    static {
        Properties properties=new Properties();
        String host;
        int port ;
        int maxTotal ;
        int maxWaitMillis;
        Duration maxWaitMillisDuration;
        int maxIdle ;
        int minIdle;
        InputStream inputStream= JedisPool.class.getClassLoader().getResourceAsStream("jedis-config.properties");
        try {
            properties.load(inputStream);
            host = properties.getProperty("redis.host", "localhost");
            port = Integer.parseInt(properties.getProperty("redis.port", "6379"));
            maxTotal = Integer.parseInt(properties.getProperty("redis.maxTotal", "10"));
            maxIdle = Integer.parseInt(properties.getProperty("redis.maxIdle", "5"));
            minIdle=Integer.parseInt(properties.getProperty("redis.minIdle","1"));
            maxWaitMillis = Integer.parseInt(properties.getProperty("redis.maxWaitMillis", "10000"));
            maxWaitMillisDuration=Duration.ofMillis(maxWaitMillis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JedisPoolConfig poolConfig=new JedisPoolConfig();
        poolConfig.setMinIdle(minIdle);
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMaxTotal(maxTotal);
        poolConfig.setMaxWait(maxWaitMillisDuration);
        pool=new redis.clients.jedis.JedisPool(poolConfig,host,port);
    }
    private JedisPool(){}
    public static JedisPool getInstance() {
        if(instance==null){
            synchronized (JedisPool.class){
                if(instance==null){
                    instance=new JedisPool();
                }
            }
        }
        return instance;
    }

    public Jedis getJedis(){
        Jedis jedis=pool.getResource();
        return jedis;
    }
}