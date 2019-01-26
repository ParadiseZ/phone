package org.lanqiao.phone.controller;


import com.alibaba.fastjson.JSON;
import net.sf.json.JSONArray;
import org.lanqiao.phone.pojo.*;
import org.lanqiao.phone.service.BackUserService;
import org.lanqiao.phone.utils.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
public class backerUserController {

    @Autowired
   BackUserService service;
    //管理员登录
    @PostMapping("/backuser")
    public String backUser(HttpServletRequest req,String user, String password, Model model, HttpSession session){
        System.out.println(user);
        System.out.println(password);
        BackUser backUser=service.getBackUser(user);
        if(user.equals(backUser.getB_user())&&password.equals( backUser.getB_password() )){
            int shopnum=service.getShopnum();
           int usernum=service.getUsernum();
            int noPAssShopNum=service.getNoPAANum();
            int tousunum=service.getTousuNUM();
            int AllTouNum=service.getAllTouNUM();
            session.setAttribute("noPAssShopNum",noPAssShopNum);
            session.setAttribute( "user",user );
           session.setAttribute( "shopnum",shopnum );
          session.setAttribute( "usernum",usernum );
          session.setAttribute("tousunum",tousunum);
          session.setAttribute( "AllTouNum",AllTouNum );

            session.setAttribute("buserInfo",backUser);
            session.setAttribute( "shopnum",shopnum );
            session.setAttribute( "usernum",usernum );
            System.out.println(user);
            return "bindex";
//            return "testMy";
        }else {
            model.addAttribute( "msg" ,"用户名或密码错误，请重新登录");
            return "Blogin";
        }
    }
    //查询所有用户
    @RequestMapping("/empList")
public String User(HttpServletRequest req,Model model,HttpSession session){
        int allNum = service.getUsernum();
        session.setAttribute("usernum",service.getUsernum());
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
public  String deleteUserSingle(@PathVariable int id, HttpSession session,Model model){
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
}//pi量删除用户
    @RequestMapping("/deleteAllUser")
public String deleteAllUser(String attr, HttpServletResponse resp) throws IOException {
    JSONArray jsonA = JSONArray.fromObject( JSON.parse(attr));
    int arrary[] = new int[jsonA.size()];
    for(int i = 0; i<jsonA.size() ;i++){
        arrary[i] = Integer.parseInt(jsonA.get(i).toString());
    }
    service.deleteAlluser( arrary );
        PrintWriter out = resp.getWriter();
        String is = JSON.toJSONString("eppea");
        out.print(is);
        return "Buser_list";
}
//查询所有商家用户

@RequestMapping("/shopList")
public String getShopAllUser( HttpServletRequest req,Model model,HttpSession session){
     int allNum=service.getShopnum();

     session.setAttribute( "shopnum",service.getShopnum() );
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
//查询未审批的
// 商家
@RequestMapping("/nopassSHOP")
public  String GetNopassShop(Model model, HttpSession session,HttpServletRequest req){
    int noPAssShopNum=service.getNoPAANum();
    session.setAttribute("noPAssShopNum",noPAssShopNum);
    int currentPage = 1;
    if(!StringUtils.isEmpty( req.getParameter( "currentPage" ) )){
        currentPage = Integer.parseInt(req.getParameter( "currentPage" ));
    }
    System.out.println("ddddddddddddddddddddddd"+noPAssShopNum);
    PageModel pm = new PageModel( currentPage,noPAssShopNum,5 );
       List<ShopUser>shopNoUserList= service.getNoPass(pm.getStartIndex(),pm.getPageSize());
       pm.setRecords(shopNoUserList );
//       model.addAttribute( "shopNoUserList",shopNoUserList );
    model.addAttribute( "pm",pm );
        return "BShops_Audit";
}
@RequestMapping("/UpdatepassSHOP")
public String updateNoPass(Model model,String mm){
    System.out.println("--------:::::"+mm);
    ShopUser UpdateNoUser = service.thing(mm);
    System.out.println(UpdateNoUser);
/*    List<ShopUser>UpdateNoUser= service.getNoPass();
    System.out.println("================"+UpdateNoUser);*/
    model.addAttribute( "UpdateNoUser",UpdateNoUser );
        return "Bshopping_detailed";
}

//审批通过的商家
@RequestMapping("updatepa/{pass}")
public String  UpdatePass( Model model, @PathVariable String pass,HttpSession session  ){
        service.UpDatePass( pass );
        session.setAttribute( "noPAssShopNum",service.getNoPAANum() );
//    model.addAttribute( " updatePass", updatePass );
        return "redirect:/nopassSHOP";
}
@RequestMapping("/Bhome")
public String getDeleteNum(HttpServletRequest req,HttpSession session){
    int shopnum=service.getShopnum();
    int usernum=service.getUsernum();
    session.setAttribute( "shopnum",shopnum );
    session.setAttribute( "usernum",usernum );
        return"Bhome.html";
}
//拒绝通过审核
   @RequestMapping("jujue/{nopass}")
    public String defeatPaa(HttpServletRequest req ,@PathVariable String  nopass){
      service.deaftPass(nopass);
        return "redirect:/nopassSHOP";
    }
    //通过客户查询评论
    @RequestMapping("/kehucha")
    public String UserPingL(HttpServletRequest req ,String kehu ,Model model){
        System.out.println(kehu+"小张");
        int  allNum=service.getPinNum();
        int currentPage = 1;
        if(!StringUtils.isEmpty( req.getParameter( "currentPage" ) )){
            currentPage = Integer.parseInt(req.getParameter( "currentPage" ));
        }
        PageModel pm = new PageModel( currentPage,allNum,5 );
        List<Rate> rateList=service.getUserPING(kehu,pm.getStartIndex(),pm.getPageSize());
        pm.setRecords( rateList );
        model.addAttribute( "pm",pm );
        return "BGuestbook";
    }
    //查询投诉
    @RequestMapping("/tousu")
    public  String getTOUSuList(Model model,HttpSession session ,HttpServletRequest req){
        int tousunum=service.getTousuNUM();
        session.setAttribute("tousunum",tousunum);
        int currentPage = 1;
        if(!StringUtils.isEmpty( req.getParameter( "currentPage" ) )){
            currentPage = Integer.parseInt(req.getParameter( "currentPage" ));
        }
        PageModel pm = new PageModel( currentPage,tousunum,5 );
      List<TouSu> touSuList= service.getTList(pm.getStartIndex(),pm.getPageSize());
        pm.setRecords(touSuList );
//   model.addAttribute( "touSuList",touSuList );
        model.addAttribute( "pm",pm );
        return "BFeedback";
    }
    //删除投诉
   @DeleteMapping("tousu00/{id}")
    public String deleteping(@PathVariable int id){
       System.out.println(id);
        service.deleteTOU( id );
       System.out.println(id);
        return "redirect:/tousu";
    }
}
