package top.haidong556.oauth;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

public class LogTest {
    public static final Logger logger= LogManager.getLogger(LogTest.class);
    @Test
    void testLog(){
        for (int i = 0; i < 10; i++) {
            logger.info("info"+i);
            logger.error("error"+i);
        }
    }
}