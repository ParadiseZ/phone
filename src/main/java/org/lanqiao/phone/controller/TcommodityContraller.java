package org.lanqiao.phone.controller;

import org.lanqiao.phone.pojo.Tcommodity;
import org.lanqiao.phone.pojo.User;
import org.lanqiao.phone.service.impl.TcommodityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class TcommodityContraller {
    @Autowired
    TcommodityServiceImpl tcommodityService;
    @RequestMapping("/Tcommodity")
    public String TcommoditygetAll(Model model){
        List<Tcommodity> tcommodityList = tcommodityService.findAll();
        model.addAttribute("tcommodityList",tcommodityList);
        return "TProducts_List";
    }

    @RequestMapping("/Tcommodity/{c_id}")
    public String deleteByI(Model model,@PathVariable int c_id){
        Tcommodity tcommodity =tcommodityService.tgyDetele(c_id);
        model.addAttribute("tcommodity",tcommodity);
        return "redirect:/Tcommodity";
    }

    @RequestMapping("/Tlogin")
    public String TcommoditygLogin(Model model){
        return "Tindex";
    }
    @RequestMapping("/Tpicture")
    public String TcommoditygPicture(){
        return "/Tpicture-add";
    }
    @RequestMapping("/AddShangping")
    public String addShopping(String c_name, String c_xinghao,String c_chicun, String c_time, String c_xitong,
                               String c_cpu,String c_dianchi,String c_houshe, String c_chongdianqi,String c_zhiwen
    ) {
        tcommodityService.tgyInsert(c_name, c_xinghao, c_chicun, c_time, c_xitong, c_cpu, c_dianchi, c_houshe, c_chongdianqi, c_zhiwen);
        return "redirect:/Tcommodity";

    }


//商家登录
    @RequestMapping("/Tgylogin")
    public String tgyLogin(HttpServletRequest req,Model model){
        String username = req.getParameter("username");

        if (username == null){
            return "Tgylogin";
        }
        String password = req.getParameter("password");
        if (StringUtils.isEmpty(username)){
            model.addAttribute("msg","用户名不能为空");
            return "Tgylogin";
        }
        if(username.length() > 10){
            model.addAttribute("msg","用户名不能多于10个字符！");
            return "Tgylogin";
        }
        if(StringUtils.isEmpty(password)){
            model.addAttribute("msg","密码不能为空！");
            return "Tgylogin";
        }
        User user = new User();
        user.setU_name(username);
        user.setU_password(password);
        User retUser = tcommodityService.TgyLogin(user);
        System.out.println(user);
        System.out.println(retUser);
        if(retUser == null){
             model.addAttribute("msg","用户名或密码错误，请重新输入！");
            return "Tgylogin";
        }else{
            HttpSession session = req.getSession();
            session.setAttribute("user",retUser);
            return "Tindex";
        }
    }
}
