package top.haidong556.oauth.security.key;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;

class JwtUpdaterTest {
    static JwtUpdater jwtUpdater;
    @BeforeAll
    static void init(){
        jwtUpdater=new JwtUpdater(false);
    }
    @Test
    void testPush(){
        jwtUpdater.pushJwtToNacos();
    }

    @Test
    void testRun() throws InterruptedException {
        Thread thread=new Thread(jwtUpdater);
        thread.setName("updaterThread");
        Thread thread1=new Thread(()->{
            while(true){
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(JwtFactory.getNowPublicKeyJson());

            }
        });
        thread1.setName("outputThread");
        thread.start();
        thread1.start();

        thread.join();
        thread1.join();
    }
}