package org.lanqiao.phone;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.lanqiao.phone.config.WebSocketInService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.ContextLoader;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PhoneApplicationTests {
    private static WebSocketInService webSocketInService = (WebSocketInService) ContextLoader.getCurrentWebApplicationContext().getBean("wsis");
    @Test
    public void contextLoads() {
        System.out.println();
    }

}

