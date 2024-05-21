package top.haidong556.chat_server.service;

import org.apache.zookeeper.*;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat;
import top.haidong556.chat_server.config.MyConfiguration;

import java.io.IOException;
import java.util.List;

public class ZooKeeperService {
    private ZooKeeper zooKeeper;
    private ConsistentHashLoadBalancerService consistentHashLoadBalancerService=ConsistentHashLoadBalancerService.getInstance();
    private  static volatile ZooKeeperService instance=new ZooKeeperService();
    public static ZooKeeperService getInstance(){
        if(instance==null){
            synchronized (ZooKeeperService.class){
                if(instance==null){
                    instance=new ZooKeeperService();
                }
            }
        }
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
            registerServer();
            watchServer();
        } catch (IOException e) {
            System.out.println("create zookeeper error");
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (KeeperException e) {
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
    public void createTempZnode(String path, byte[] data) throws KeeperException, InterruptedException {
        if (zooKeeper.exists(path, false) == null) {
            zooKeeper.create(path, data, Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            System.out.println("Created temporary znode: " + path);
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
    private void ensurePathExists(String path) throws KeeperException, InterruptedException {
        String[] pathParts = path.split("/");
        StringBuilder fullPath = new StringBuilder();

        for (String part : pathParts) {
            if (!part.isEmpty()) {
                fullPath.append("/").append(part);
                String currentPath = fullPath.toString();
                if (zooKeeper.exists(currentPath, false) == null) {
                    try {
                        zooKeeper.create(currentPath, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
                        System.out.println("Created znode: " + currentPath);
                    } catch (KeeperException.NodeExistsException e) {
                        // Node already exists, ignore
                    }
                }
            }
        }
    }
    public void registerServer() throws InterruptedException, KeeperException {
        ensurePathExists(MyConfiguration.ZOOKEEPER_SERVER_NODE_PATH);
        createTempZnode(MyConfiguration.ZOOKEEPER_SERVER_NODE_PATH+"/"+MyConfiguration.MYSELF_CHAT_SERVER_ID,"chat".getBytes());
    }
    private void watchServer() throws KeeperException, InterruptedException {
        String path=MyConfiguration.ZOOKEEPER_SERVER_NODE_PATH;
        if (path != null && zooKeeper.exists(path, false) != null) {
            zooKeeper.getData(path, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    System.out.println("Watched event: " + event);
                    String path = event.getPath();
                    String node = null;

                    // 提取路径中的节点名称
                    if (path != null) {
                        node = path.substring(MyConfiguration.ZOOKEEPER_SERVER_NODE_PATH.length());
                    }
                    try {
                        switch (event.getType()) {
                            case NodeCreated:
                                // 事件为节点创建
                                consistentHashLoadBalancerService.putNode(node);
                                break;
                            case NodeDeleted:
                                // 事件为节点删除
                                consistentHashLoadBalancerService.removeNode(node);
                                break;
                            case NodeDataChanged:
                                break;
                            default:
                                break;
                        }
                        // 重新注册监听器以持续监控该节点
                        watchServer();
                    } catch (KeeperException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, new Stat());
        }
    }



}
