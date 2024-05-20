package top.haidong556.oauth.security.key;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import top.haidong556.chat_server.common.chat.chat_server.config.RestTemplateFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static java.lang.Thread.sleep;

public class JwtUpdater implements Runnable{
    static {
        InputStream resourceAsStream;
        Properties properties=new Properties();
        try {
            resourceAsStream = JwtUpdater.class.getClassLoader().getResourceAsStream("key-config.properties");
            properties.load(resourceAsStream);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        EXPIRED= Long.parseLong(properties.getProperty("server-key-expired-millis","-1"));
        NACOS_URL= properties.getProperty("nacos-url","http://127.0.0.1:8848/nacos/v1/cs/configs");
        NOW_PUBLIC_KEY_DATA_ID=properties.getProperty("data-id-now","public-key-now.properties");
        PRE_PUBLIC_KEY_DATA_ID=properties.getProperty("data-id-pre","public-key-pre.properties");

        GROUP= properties.getProperty("group","DEFAULT_GROUP");
    }
    public JwtUpdater(boolean useNacos){
        this.useNacos=useNacos;
    }

    private static final long EXPIRED;
    private static final String NACOS_URL;
    private static final String NOW_PUBLIC_KEY_DATA_ID ;
    private static final String PRE_PUBLIC_KEY_DATA_ID ;


    private static final String GROUP ;
    private String nowPublicKeyContent = "content";
    private String prePublicKeyContent = "content";

    private boolean useNacos=false;

    private RestTemplate restTemplate= RestTemplateFactory.getRestTemplate();
    @Override
    public void run() {

        if(EXPIRED==-1)
            return ;

        while (true){
            try {
                updateJwt();
                if(useNacos==true)
                    pushJwtToNacos();
                sleep(EXPIRED);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public boolean pushJwtToNacos() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        nowPublicKeyContent=pk2npk(JwtFactory.getNowPublicKeyProperties());
        prePublicKeyContent=pk2ppk(JwtFactory.getPrePublicKeyProperties());
        String nPubKeyRequestBody = "dataId=" +NOW_PUBLIC_KEY_DATA_ID + "&group=" + GROUP + "&content=" + nowPublicKeyContent;
        String pPubKeyRequestBody = "dataId=" + PRE_PUBLIC_KEY_DATA_ID + "&group=" + GROUP + "&content=" + prePublicKeyContent;

        HttpEntity<String> nPubKeyRequestEntity = new HttpEntity<>(nPubKeyRequestBody, headers);
        HttpEntity<String> pPubKeyRequestEntity = new HttpEntity<>(pPubKeyRequestBody, headers);
        try {
            restTemplate.postForLocation(NACOS_URL, nPubKeyRequestEntity);
            restTemplate.postForLocation(NACOS_URL, pPubKeyRequestEntity);
        }catch (RestClientException e){
            System.out.println("update jwt to nacos request sent error.");
            System.out.println(e);
            return false;
        }
        System.out.println("update jwt to nacos request sent successfully.");
        return true;
    }

    private String pk2ppk(String prePublicKeyProperties) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] lines = prePublicKeyProperties.split("\n");
        for (String line : lines) {
            stringBuilder.append("prePublicKey.").append(line).append("\n");
        }
        System.out.println(stringBuilder);
        return stringBuilder.toString();
    }

    private String pk2npk(String nowPublicKeyProperties) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] lines = nowPublicKeyProperties.split("\n");
        for (String line : lines) {
            stringBuilder.append("nowPublicKey.").append(line).append("\n");
        }
        System.out.println(stringBuilder);
        return stringBuilder.toString();
    }

    private void updateJwt() {
        System.out.println("update");
        JwtFactory.updateJwt();
    }
}
