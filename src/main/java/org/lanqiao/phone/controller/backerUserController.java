package org.lanqiao.phone.controller;

import org.lanqiao.phone.pojo.BackUser;
import org.lanqiao.phone.pojo.Rate;
import org.lanqiao.phone.pojo.ShopUser;
import org.lanqiao.phone.pojo.User;
import org.lanqiao.phone.service.BackUserService;
import org.lanqiao.phone.utils.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class backerUserController {

    @Autowired
   BackUserService service;
    //管理员登录
    @PostMapping("/backuser")
    public String backUser(String user, String password, Model model, HttpSession session){
        System.out.println(user);
        System.out.println(password);
        BackUser backUser=service.getBackUser(user);
        if(user.equals(backUser.getB_user())&&password.equals( backUser.getB_password() )){
            int shopnum=service.getShopnum();
            int usernum=service.getUsernum();
            int noPAssShopNum=service.getNoPAANum();
            session.setAttribute("noPAssShopNum",noPAssShopNum);
            session.setAttribute( "user",user );
            session.setAttribute( "shopnum",shopnum );
            session.setAttribute( "usernum",usernum );
            System.out.println(shopnum);
            return "bindex";
//            return "testMy";
        }else {
            model.addAttribute( "msg" ,"用户名或密码错误，请重新登录");
            return "Blogin";
        }
    }
    //查询所有用户
    @RequestMapping("/empList")
public String User(HttpServletRequest req,Model model){
        int allNum = service.getUsernum();
        int currentPage = 1;
        if(!StringUtils.isEmpty( req.getParameter( "currentPage" ) )){
            currentPage = Integer.parseInt(req.getParameter( "currentPage" ));
        }
        PageModel pm = new PageModel( currentPage,allNum,5 );
    List<User> userList=service.getUserlist(pm.getStartIndex(),pm.getPageSize());
    pm.setRecords( userList );
    model.addAttribute( "pm",pm);
    return "Buser_list";
}
//根据用户id逐个删除用户
    @DeleteMapping("user/{id}")
public  String deleteUserSingle(@PathVariable int id){
        System.out.println(id);
        service.DeleteUser(id);
        return "redirect:/empList";
}
//根据用户名查询
@RequestMapping("/search")
public String SearchByUname(HttpServletRequest req,String huiyuan,Model model,HttpSession session){
   System.out.println(huiyuan);
    int allNum = service.getUsernum();
    System.out.println("====="+allNum);
    int currentPage = 1;
    if(!StringUtils.isEmpty( req.getParameter( "currentPage" ) )){
        currentPage = Integer.parseInt(req.getParameter( "currentPage" ));
    }
    PageModel pm = new PageModel( currentPage,allNum,5 );
    List <User> userList=service.searchUser( huiyuan,pm.getStartIndex(),pm.getPageSize());
//    System.out.println(userList);
//    model.addAttribute( "userList",userList);
    pm.setRecords( userList );
    model.addAttribute( "pm",pm);
    return "Buser_list";
}
public String deleteAllUser(HttpServletRequest req){

        return null;
}
//查询所有商家用户

@RequestMapping("/shopList")
public String getShopAllUser( HttpServletRequest req,Model model){
     int allNum=service.getShopnum();
    int currentPage = 1;
    if(!StringUtils.isEmpty( req.getParameter( "currentPage" ) )){
        currentPage = Integer.parseInt(req.getParameter( "currentPage" ));
    }
    PageModel pm=new PageModel( currentPage,allNum,5 );
        List<ShopUser> shopUserList=service.getShopList(pm.getStartIndex(),pm.getPageSize());
//        model.addAttribute( "shopUserList",shopUserList );
     pm.setRecords( shopUserList);
     model.addAttribute( "pm", pm );
        return "BShop_list";
}
//逐个删除商家
    @DeleteMapping("shop/{id}")
public String DeleteShopUser(@PathVariable int id){
        System.out.println(id+"==========================");
        service.DeleteShop( id );
        return "redirect:/shopList";
}
//通过店铺名称查找商家
    @RequestMapping("/searchshop")
public String SearchShopUser (String shopname,Model model,HttpServletRequest req){
        int allNum=service.getShopnum();
        int currentPage = 1;
        if(!StringUtils.isEmpty( req.getParameter( "currentPage" ) )){
            currentPage = Integer.parseInt(req.getParameter( "currentPage" ));
        }
        System.out.println(shopname+"==================");
        PageModel pm = new PageModel( currentPage,allNum,5 );
        List<ShopUser> shopUserList=service.searchShopUser( shopname,pm.getStartIndex(),pm.getPageSize() );
//        model.addAttribute( "shopUserList" ,shopUserList);
        pm.setRecords(shopUserList);
        model.addAttribute( "pm",pm );
        return "BShop_list" ;
}
//评论查询
    @RequestMapping("/pinglun")
public String getPingL(Model model,HttpSession session,HttpServletRequest req){
        int  allNum=service.getPinNum();
      session.setAttribute( "pinglunnum",allNum);
        int currentPage = 1;
        if(!StringUtils.isEmpty( req.getParameter( "currentPage" ) )){
            currentPage = Integer.parseInt(req.getParameter( "currentPage" ));
        }
        System.out.println("ddddddddddddddddddddddd"+allNum);
        PageModel pm = new PageModel( currentPage,allNum,5 );
        List<Rate> rateList=service.getPingLUN(pm.getStartIndex(),pm.getPageSize());
        pm.setRecords( rateList );
        model.addAttribute( "pm",pm );
        return "BGuestbook";
}
//通过c_Id逐个删除评论
    @DeleteMapping("rate/{id}")
public String deletePingL(@PathVariable int id){
        System.out.println("pinglunid"+"666666"+id);
        service.DeletePing(id);
        System.out.println("pinglunid"+"666666"+id);
        return "redirect:/pinglun";
}
@RequestMapping("/nopassSHOP")
public  String GetNopassShop(Model model, HttpSession session ){
       List<ShopUser>shopNoUserList= service.getNoPass();
    System.out.println("================"+shopNoUserList);
       model.addAttribute( "shopNoUserList",shopNoUserList );
        return "BShops_Audit";
}
@RequestMapping("/UpdatepassSHOP")
public String updateNoPass(Model model,HttpSession session,HttpServletRequest req){
    List<ShopUser>UpdateNoUser= service.getNoPass();
    System.out.println("================"+UpdateNoUser);
    model.addAttribute( "UpdateNoUser",UpdateNoUser );
        return "Bshopping_detailed";
}
}
