package org.lanqiao.phone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class pageController {
    @RequestMapping("/Blogin")
    public String goLogin() {
        return "Blogin";
    }
}