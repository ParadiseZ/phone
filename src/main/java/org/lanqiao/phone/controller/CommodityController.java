package org.lanqiao.phone.controller;

import org.lanqiao.phone.pojo.Commodity;
import org.lanqiao.phone.service.impl.CommodityServiceImpl;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CommodityController {

    @Autowired
    CommodityServiceImpl commodityService;

    @RequestMapping("/chacun")
    public String selectCommodity(String c_name, Model model){
        System.out.println(c_name);
        List<Commodity> commodityList = commodityService.selectCommidity(c_name);
        model.addAttribute("commodityList",commodityList);
        return "liebiao";
    }

    @RequestMapping("/xiangqing.do")
    public String link(String c_name,Model model){
        System.out.println(c_name);
        Commodity commodity = commodityService.link(c_name);
        System.out.println(c_name);
        model.addAttribute("commodity",commodity);
        return "xiangqing";
    }


}
