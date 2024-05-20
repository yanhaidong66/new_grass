package top.haidong556.oauth.security.key;

import com.auth0.jwt.interfaces.RSAKeyProvider;
import org.springframework.context.annotation.PropertySource;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 单例模式
 * 内部含有两对jwtKeyPair，一个是当下的keyPair，一个是刚刚过期的keyPair
 */
@PropertySource("classpath:key-config.properties")
public class JwtKeyProvider  {
    private static final ReentrantReadWriteLock lock=new ReentrantReadWriteLock();
    private static final JwtKeyProvider instance=new JwtKeyProvider();
    private static volatile MyKeyPair nowKeyPair=new MyKeyPair();
    private static volatile MyKeyPair preKeyPair=new MyKeyPair();
    private JwtKeyProvider(){}
    public static JwtKeyProvider getInstance(){
        return instance;
    }
    //只维持一个密钥对，所以keyId参数为空
    public  RSAPublicKey getNowPublicKey() {
        try {
            lock.readLock().lock();
            return (RSAPublicKey) nowKeyPair.getPublicKey();
        }finally {
            lock.readLock().unlock();
        }
    }
    public  RSAPublicKey getPrePublicKey() {
        try {
            lock.readLock().lock();
            return (RSAPublicKey) preKeyPair.getPublicKey();
        }finally {
            lock.readLock().unlock();
        }
    }

    public RSAPrivateKey getNowPrivateKey() {
        try {
            lock.readLock().lock();
            return (RSAPrivateKey) nowKeyPair.getPrivateKey();
        }finally {
            lock.readLock().unlock();
        }
    }
    public RSAPrivateKey getPrePrivateKey() {
        try {
            lock.readLock().lock();
            return (RSAPrivateKey) preKeyPair.getPrivateKey();
        }finally {
            lock.readLock().unlock();
        }
    }
    public void updateKeyPair(){
        try {
            lock.writeLock().lock();
            preKeyPair=nowKeyPair;
            nowKeyPair=new MyKeyPair();
        }finally {
            lock.writeLock().unlock();
        }

    }


    public static String getNowPublicKeyJson(){return nowKeyPair.getPublicKeyJson();}
    public static String getPrePublicKeyJson(){return preKeyPair.getPublicKeyJson();}
    public static String getNowPublicKeyProperties(){return nowKeyPair.getPublicKeyProperties();}
    public static String getPrePublicKeyProperties(){return preKeyPair.getPublicKeyProperties();}

}
