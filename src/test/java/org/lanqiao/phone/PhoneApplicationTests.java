package org.lanqiao.phone;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.lanqiao.phone.service.BackUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PhoneApplicationTests {
    @Autowired
    BackUserService backUserService;
    @Test
    public void contextLoads() {
//        System.out.println(backUserService.getBackUser("admin","admin"));
//            System.out.println(backUserService.getImforOne("admin"));
        System.out.println(backUserService.getNoPAANum());

    }

}

