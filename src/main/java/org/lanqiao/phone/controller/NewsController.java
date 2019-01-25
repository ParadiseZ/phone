package org.lanqiao.phone.controller;

import org.lanqiao.phone.pojo.*;
import org.lanqiao.phone.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class NewsController {
    @Autowired
    INewsService newsService;
    @RequestMapping("/getnews")
    public String getNews(HttpServletRequest req, Model model, HttpSession session){
        String receiverIdStr = req.getParameter("receiverId");
        int senderId = 0;
        String u_IdType = null;
        UserLogin personal = (UserLogin)session.getAttribute("userLogin");
        BackUser bck;
        if(StringUtils.isEmpty(personal)){
            bck = (BackUser)session.getAttribute("buserInfo");
            if(StringUtils.isEmpty(bck)){


            }else {
                senderId = bck.getB_Id();
                u_IdType = "管理员";
            }
        }else {
            senderId = personal.getU_Id();
            u_IdType = "个人";
        }

        //根据该用户ID和类型查询该用户的所有不同对话人ID和用户类型
        List<UserIdAndType> userIdAndTypes = newsService.getIdAndType(senderId,u_IdType);//对话用户ID和类型表
        List<UserIdAndType> userIdAndTypeList =new ArrayList<>();
        for(UserIdAndType loop:userIdAndTypes){
            if(!userIdAndTypeList.contains(loop)){
                userIdAndTypeList.add(loop);
            }
        }
        List<UserConnectionInfo> userConnectionInfos = new ArrayList<>(); //信息
        List<News> newsList = new ArrayList<>();
        if(!StringUtils.isEmpty(receiverIdStr)){
            int receiverId = Integer.parseInt(receiverIdStr);
            String receiverType =req.getParameter("receiverType");
            UserIdAndType check = UserIdAndType.builder().userIdFind(receiverId).userType(receiverType).build();
            if(!userIdAndTypeList.contains(check)){
                userIdAndTypeList.add(check);
            }
            newsList = newsService.getAllNews(senderId,u_IdType,receiverId,receiverType);
            descSocketId(senderId,u_IdType,userConnectionInfos,newsService,userIdAndTypeList);
            model.addAttribute("newsList",newsList);
            model.addAttribute("userConInfo",userConnectionInfos);
            model.addAttribute("recName",newsService.getUserNameByNum(receiverId,receiverType));
        }else if(userIdAndTypeList.size()>0){
            UserIdAndType uit0 = userIdAndTypeList.get(0);
            newsList = newsService.getAllNews(senderId,u_IdType,uit0.getUserIdFind(),uit0.getUserType());
            descSocketId(senderId,u_IdType,userConnectionInfos,newsService,userIdAndTypeList);
            model.addAttribute("newsList",newsList);
            model.addAttribute("userConInfo",userConnectionInfos);
            model.addAttribute("recName",newsService.getUserNameByNum(uit0.getUserIdFind(),uit0.getUserType()));
        }else {
            model.addAttribute("error","您还有没有新的消息！");
        }
        model.addAttribute("u_IdType",u_IdType);
        model.addAttribute("senderId",senderId);
        model.addAttribute("senderName",newsService.getUserNameByNum(senderId,u_IdType));
        return "chatonline";
    }

    @RequestMapping("/insertNew")
    @ResponseBody
    public String inserNew(HttpServletRequest req){
        int senderId = Integer.parseInt(req.getParameter("senderId"));
        String senderType = req.getParameter("u_IdType");
        int recId = Integer.parseInt(req.getParameter("recId"));
        String recType = req.getParameter("recType");
        String n_news = req.getParameter("oneNew");
        String n_state = req.getParameter("n_state");
        newsService.inserNews(senderId,senderType,recId,recType,n_news,n_state);
        return "";
    }
    //遍历连接列表，将不重复的连接进行存储
    public static synchronized void descSocketId(int senderId,String senderType,List<UserConnectionInfo> uci,INewsService ins,List<UserIdAndType> userIdAndTypeList){
        int receiverId = -1;
        String resType = "";
        String socketId = "";
        boolean xu = false;
        for(UserIdAndType uit:userIdAndTypeList) {
            receiverId = uit.getUserIdFind();
            resType = uit.getUserType();

            int len = 0;
            for (ConnectionInfo connInfo : ConnectionInfo.ConInfoList) {
                boolean asNormal = (senderId == connInfo.getU_id() && senderType.equals(connInfo.getU_idType()) && receiverId == connInfo.getS_id() && resType.equals(connInfo.getS_idType()));
                boolean asRe = (senderId == connInfo.getS_id() && senderType.equals(connInfo.getS_idType()) && receiverId == connInfo.getU_id() && resType.equals(connInfo.getU_idType()));
                if (asNormal || asRe) {
                    socketId = connInfo.getSocketId();
                    break;
                } else {
                    len++;
                }
            }
            if (len == ConnectionInfo.ConInfoList.size()) {
                xu =true;
                socketId = senderId+senderType+receiverId+resType;
                ConnectionInfo connectionInfo = ConnectionInfo.builder().u_id(senderId).u_idType(senderType).s_id(receiverId).s_idType(resType).socketId(socketId).build();
                ConnectionInfo.ConInfoList.add(connectionInfo);
            }
            uci.add(UserConnectionInfo.builder().userIdAndType(uit).socketId(socketId).usernameFind(ins.getUserNameByNum(receiverId,resType)).build());
        }
    }

    @RequestMapping("/findNews")
    public String getNewsByIdAndType(HttpServletRequest req,Model model){
        int senderId = Integer.parseInt(req.getParameter("senderId")) ;
        String u_IdType = req.getParameter("u_IdType");
        int receiverId =Integer.parseInt(req.getParameter("receiverId"));
        String receiverType = req.getParameter("receiverType");
        List<News> newsList = newsService.getAllNews(senderId,u_IdType,receiverId,receiverType);
        model.addAttribute("newsList",newsList);
        model.addAttribute("u_IdType",u_IdType);
        model.addAttribute("senderId",senderId);
        model.addAttribute("senderName",newsService.getUserNameByNum(senderId,u_IdType));
        model.addAttribute("recName",newsService.getUserNameByNum(receiverId,receiverType));
        return "chatonline::changeSign";
    }
}
