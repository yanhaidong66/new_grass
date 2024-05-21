package top.haidong556.chat_server.service;

import top.haidong556.chat_server.config.MyConfiguration;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.SortedMap;
import java.util.TreeMap;

public class ConsistentHashLoadBalancerService {
    private ZooKeeperService zooKeeperService=ZooKeeperService.getInstance();
    private final TreeMap<Long,String> circle=new TreeMap<>();
    private final int numberOfReplicas= Integer.valueOf(MyConfiguration.CONSISTENT_HASHING_REPLACAS);
    private static final ConsistentHashLoadBalancerService instance=new ConsistentHashLoadBalancerService();
    private ConsistentHashLoadBalancerService(){

    }
    public static ConsistentHashLoadBalancerService getInstance(){
        return instance;
    }

    public void putNode(String node){
        for (int i=0;i<numberOfReplicas;i++){
            long nodeHash=hash(node+i);
            circle.put(nodeHash,node);
        }
    }
    public String getNode(long key){
        if(circle.isEmpty()==true){
            return null;
        }
        long keyHash=hash(String.valueOf(key));
        if (!circle.containsKey(keyHash)) {
            SortedMap<Long, String> tailMap = circle.tailMap(keyHash);
            keyHash = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();
        }
        return circle.get(keyHash);
    }

    public void removeNode(String node) {
        for (int i = 0; i < numberOfReplicas; i++) {
            long hash = hash(node + i);
            circle.remove(hash);
        }
    }


    private long hash(String key) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] bytes = md.digest(key.getBytes(StandardCharsets.UTF_8));
        return ((long) (bytes[0] & 0xFF) << 56)
                | ((long) (bytes[1] & 0xFF) << 48)
                | ((long) (bytes[2] & 0xFF) << 40)
                | ((long) (bytes[3] & 0xFF) << 32)
                | ((long) (bytes[4] & 0xFF) << 24)
                | ((long) (bytes[5] & 0xFF) << 16)
                | ((long) (bytes[6] & 0xFF) << 8)
                | ((long) (bytes[7] & 0xFF));
    }


}
