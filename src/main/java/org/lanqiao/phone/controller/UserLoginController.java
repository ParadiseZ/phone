package org.lanqiao.phone.controller;

import org.apache.catalina.User;
import org.lanqiao.phone.pojo.UserLogin;
import org.lanqiao.phone.service.IUserLoginService;
import org.lanqiao.phone.service.impl.UserLoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class UserLoginController {
    @Autowired
    IUserLoginService iUserLoginService;

    @RequestMapping("/login.do")
    public String Login(HttpServletRequest req, HttpServletResponse resp, Model model) {
        String username = req.getParameter("username");
        if (username == null) {
            return "login";
        }
        String password = req.getParameter("password");
        if (StringUtils.isEmpty(username)) {
            model.addAttribute("msg", "用户名不能为空");
            return "login";
        }
        if (username.length() > 11) {
            model.addAttribute("msg", "用户名不能多于11个字符!");
            return "login";
        }
        if (StringUtils.isEmpty(password)) {
            model.addAttribute("msg", "密码不能为空");
            return "login";
        }
        UserLogin userLogin = new UserLogin();

        userLogin.setU_name(username);
        userLogin.setU_password(password);
        UserLogin retUserLogin = iUserLoginService.Login(userLogin);
        if (retUserLogin == null) {
            model.addAttribute("msg", "用户名密码有误！");
            return "login";
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("userLogin", retUserLogin);
//            return "index";
            return "choose";
        }
    }
}
