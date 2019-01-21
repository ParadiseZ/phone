package org.lanqiao.phone;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.lanqiao.phone.pojo.News;
import org.lanqiao.phone.pojo.UserIdAndType;
import org.lanqiao.phone.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PhoneApplicationTests {
    @Autowired
    INewsService newsService;
    @Test
    public void contextLoads() {
        int senderId =1;
        String u_idType = "个人";
//        List<News> newsList = newsService.getAllNews(1,u_idType);//消息列表
        List<UserIdAndType> userIdAndType = newsService.getIdAndType(senderId,u_idType);//消息列表
        /*String sou = newsService.getUserNameByNum(1,"个人");
        System.out.println(sou+"==");
        sou = newsService.getUserNameByNum(1,"商家");
        System.out.println(sou+"---");;
        sou = newsService.getUserNameByNum(1,"管理员");
        System.out.println(sou+"+++");;*/
/*        for(News news:newsList){
            System.out.println(news);
        }*/
        System.out.println(userIdAndType.get(0));
    }

}

