package org.lanqiao.phone;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.lanqiao.phone.pojo.Commodity;
import org.lanqiao.phone.service.impl.CommodityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PhoneApplicationTests {
    @Autowired
    CommodityServiceImpl commodityService;

    @Test
    public void selectCommodity(){
        List<Commodity> commodityList = commodityService.selectCommidity("OPPO");
        System.out.println(commodityList.size()+"======");
        List<Commodity> com = new ArrayList<>();
        List<List<Commodity>> comm = new ArrayList<>();
        for(int i=1;i<commodityList.size()+1;i++){
            if(com.size()%5==0&&com.size()!=0){
                comm.add(com);
                com.clear();
                i--;
            }else {
                if(i==10){
                    comm.add(com);
                }
                com.add(commodityList.get(i-1));
            }
        }
    }

    @Test
    public void contextLoads() {
    }

}

