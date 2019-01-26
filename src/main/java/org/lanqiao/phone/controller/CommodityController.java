package org.lanqiao.phone.controller;

import org.lanqiao.phone.pojo.Commodity;
import org.lanqiao.phone.service.impl.CommodityServiceImpl;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CommodityController {

    @Autowired
    CommodityServiceImpl commodityService;

    @RequestMapping("/chacun")
    public String selectCommodity(String c_name, Model model){
        List<Commodity> commodityList = commodityService.selectCommidity(c_name);
        List<Commodity> com = new ArrayList<>();
        List<List<Commodity>> comm = new ArrayList<>();
        if(commodityList.size()<5){
            comm.add(commodityList);
        }
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
        model.addAttribute("commodityList",comm);
        return "liebiao";
    }

    @RequestMapping("/xiangqing.do")
    public String link(String c_name,Model model){
        Commodity commodity = commodityService.link(c_name);
        model.addAttribute("commodity",commodity);
        return "xiangqing";
    }


}
