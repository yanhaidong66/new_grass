package top.haidong556.chat_server.service;

import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import top.haidong556.chat_server.config.MyConfiguration;

import java.io.IOException;

public class ZooKeeperService {
    private ZooKeeper zooKeeper;
    private final static ZooKeeperService instance=new ZooKeeperService();
    public static ZooKeeperService getInstance(){
        return instance;
    }

    private ZooKeeperService() {
        try {
            zooKeeper = new ZooKeeper(MyConfiguration.ZOOKEEPER_ADDR, 3000, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    System.out.println("Received event: " + event);
                }
            });
        } catch (IOException e) {
            System.out.println("create zookeeper error");
            throw new RuntimeException(e);
        }
    }

    public void createZnode(String path, byte[] data) throws KeeperException, InterruptedException {
        if (zooKeeper.exists(path, false) == null) {
            zooKeeper.create(path, data, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            System.out.println("Created znode: " + path);
        } else {
            System.out.println("Znode already exists: " + path);
        }
    }

    public byte[] getZnodeData(String path) throws KeeperException, InterruptedException {
        if (zooKeeper.exists(path, false) != null) {
            return zooKeeper.getData(path, false, null);
        } else {
            System.out.println("Znode does not exist: " + path);
            return null;
        }
    }

    public void setZnodeData(String path, byte[] data) throws KeeperException, InterruptedException {
        if (zooKeeper.exists(path, false) != null) {
            zooKeeper.setData(path, data, -1);
            System.out.println("Updated znode data for: " + path);
        } else {
            System.out.println("Znode does not exist: " + path);
        }
    }

    public void deleteZnode(String path) throws KeeperException, InterruptedException {
        if (zooKeeper.exists(path, false) != null) {
            zooKeeper.delete(path, -1);
            System.out.println("Deleted znode: " + path);
        } else {
            System.out.println("Znode does not exist: " + path);
        }
    }

    public void close() throws InterruptedException {
        if (zooKeeper != null) {
            zooKeeper.close();
            System.out.println("ZooKeeper connection closed");
        }
    }



}
