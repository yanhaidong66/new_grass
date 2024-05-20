package top.haidong556.oauth;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import top.haidong556.oauth.security.key.JwtUpdater;


@EnableWebSecurity
@EnableDiscoveryClient
@SpringBootApplication
public class OauthApplication {
    private static final Logger logger= LogManager.getLogger(OauthApplication.class);
    private static boolean useNacos=false;

    public static void main(String[] args) {
        for(String s:args){
            if(s.equals("useNacos")==true) {
                useNacos = true;
                System.out.println("used nacos");
            }
        }
        Thread jwtUpdater=new Thread(new JwtUpdater(useNacos));
        logger.info("updater");
        jwtUpdater.start();
        SpringApplication.run(OauthApplication.class, args);
        logger.info("oauth application run");
        try {
            jwtUpdater.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
