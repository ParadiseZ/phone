package org.lanqiao.phone.controller;


import org.lanqiao.phone.pojo.ConnectionInfo;
import org.lanqiao.phone.pojo.News;
import org.lanqiao.phone.pojo.SessionList;
import org.lanqiao.phone.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@Controller
public class MyController {
    @Autowired
    INewsService newsService;
    @RequestMapping("/login")
    public String contLogin(String username, String password, Model model){
        if("光头强".equals(username)&&"123".equals(password)){
            model.addAttribute("sender",username);
//            return "choose";
            return "choose";
        }else if("熊大".equals(username)&&"123".equals(password)){
            model.addAttribute("sender",username);
            return "choose";
        }else if("熊二".equals(username)&&"123".equals(password)){
            model.addAttribute("sender",username);
            return "choose";
        }
        return "login";
    }
    @RequestMapping("/choose")
    public String chooseId(HttpServletRequest req,HttpServletResponse resp,Model model) throws ServletException, IOException {
        String sender = req.getParameter("sender");
        String choose = req.getParameter("chooseOf");
        String socketID = "";
        int len = 0;

        for(SessionList sessionList:SessionList.sessionList){
            if(sender.equals(sessionList.getRecieverId())&&choose.equals(sessionList.getSenderId())){
                socketID = sessionList.getSocketId();
                model.addAttribute("socketID",socketID);
                model.addAttribute("sender",sessionList.getRecieverId());
                model.addAttribute("reciever",sessionList.getSenderId());
                return "testq";
            }else{
                len++;
            }
        }
        if(len==SessionList.sessionList.size()){
            String newSocketID = sender + choose;
            SessionList newSession = new SessionList(sender,newSocketID,choose);
            SessionList.sessionList.add(newSession);
            model.addAttribute("socketID",newSocketID);
            model.addAttribute("sender",sender);
            model.addAttribute("reciever",choose);
            return "testq";
        }
        return "choose";
    }
    @RequestMapping("/getUnreadNum")
    public String getAllUnreadNum(HttpServletRequest req, Model model, HttpSession session){
        String recieverIdStr = req.getParameter("recieverId");
        String senderIdStr = req.getParameter("senderId");
        String recieverType =req.getParameter("recieverType");
        int senderId = 0;
        if(!StringUtils.isEmpty(senderIdStr)){
             senderId =Integer.parseInt(req.getParameter("senderId")) ;
        }
        String u_IdType = (String) session.getAttribute("personUserType");
        if(StringUtils.isEmpty(u_IdType)){
            u_IdType = (String)session.getAttribute("businessUserType");
            if(StringUtils.isEmpty(u_IdType)){
                u_IdType = (String)session.getAttribute("managerUserType");
            }
        }
        List<News> newsList = newsService.getAllNews(senderId,u_IdType,Integer.parseInt(recieverIdStr),recieverType);//消息列表
        Map<Integer,Date> recieverIdAndSocketId = new HashMap<>();  //接收者和会话标识ID
        Map<Integer,String> recieverName = new HashMap<>();
        Map<Integer,String> receiveType = new HashMap<>();
        //如果有接收者（即点击商品处的聊天进入）
        if(!StringUtils.isEmpty(recieverIdStr)){
            int receiverId = Integer.parseInt(recieverIdStr);
            //如果数据库有消息数据
            if(newsList.size()>0){
                int s_Id = -1;  //标识获取的第一条消息的接收者
                int u_Id = -1;
                String s_userTypeInit = "";
                String u_userTypeInit = "";
                int oneOfNewNum = 0;
                //将该用户的消息进行遍历，如其中没有该接收者，则将连接信息添加到列表
                for(News oneOfNew:newsList){
                    if(receiverId != oneOfNew.getS_Id()){
                        oneOfNewNum++;
                    }
                    if(s_Id!=oneOfNew.getS_Id()&&!s_userTypeInit.equals(oneOfNew.getS_IdType())){   //第一次遍历则将发送者ID和接收者ID放入连接列表中，并用时间作为会话标识ID
                        s_Id = oneOfNew.getS_Id();
                        s_userTypeInit = oneOfNew.getS_IdType();
                        recieverName.put(s_Id,newsService.getUserNameByNum(s_Id,oneOfNew.getS_IdType()));
                        descSocketId(senderId,s_Id,recieverIdAndSocketId);
                        receiveType.put(s_Id,oneOfNew.getS_IdType());
                    }
                    if(u_Id!=oneOfNew.getU_Id()&&!u_userTypeInit.equals(oneOfNew.getU_IdType())){
                        u_Id = oneOfNew.getU_Id();
                        u_userTypeInit = oneOfNew.getU_IdType();
                        recieverName.put(u_Id,newsService.getUserNameByNum(u_Id,oneOfNew.getU_IdType()));
                        descSocketId(senderId,u_Id,recieverIdAndSocketId);
                        receiveType.put(u_Id,oneOfNew.getU_IdType());
                    }
                }
                //消息所有消息遍历结束，未发现该接收者则建立新的连接信息
                if(oneOfNewNum==newsList.size()){
                    recieverName.put(receiverId,newsService.getUserNameByNum(receiverId,recieverType));
                    descSocketId(senderId,receiverId,recieverIdAndSocketId);
                    receiveType.put(receiverId,recieverType);
                }
            }else {
                //若数据库没有消息信息，则将发送接收者ID和标识（此刻时间）添加到列表中
                descSocketId(senderId,receiverId,recieverIdAndSocketId);
                recieverName.put(receiverId,newsService.getUserNameByNum(receiverId,recieverType));
                receiveType.put(receiverId,recieverType);
            }

        }else if(StringUtils.isEmpty(recieverIdStr)&&newsList.size()==0){
            //如果没有接收者，即点击消息处进入
            model.addAttribute("newsInfo","对不起，您还没有新的消息！");
            return "";
        }else if(newsList.size()>0){
            int s_Id = -1;  //标识获取的第一条消息的接收者
            for(News oneOfNew:newsList){
                if(s_Id!=oneOfNew.getS_Id()){   //第一次遍历则将发送者ID和接收者ID放入连接列表中，并用时间作为会话标识ID
                    s_Id = oneOfNew.getS_Id();
                    recieverName.put(s_Id,newsService.getUserNameByNum(s_Id,oneOfNew.getS_IdType()));
                    descSocketId(senderId,s_Id,recieverIdAndSocketId);
                    receiveType.put(s_Id,oneOfNew.getS_IdType());
                }
            }
        }

        model.addAttribute("newsList",newsList);
        model.addAttribute("recieverIdAndSocketId",recieverIdAndSocketId);
        model.addAttribute("recieverName",recieverName);
        model.addAttribute("receiveType",receiveType);
        return "index";
    }

    //遍历连接列表，将不重复的连接进行存储
    public static synchronized void descSocketId(int sender,int receiver,Map<Integer,Date> recieverIdAndSocketId){
        Date socketId = null;
        int len = 0;
        for(ConnectionInfo connInfo:ConnectionInfo.ConInfoList){
            if(sender == connInfo.getS_id()&&receiver == connInfo.getU_id()){
//                socketId = connInfo.getSocketId();
                break;
            }else{
                len++;
            }
        }
        if(len==ConnectionInfo.ConInfoList.size()){
            socketId = new Date();
            ConnectionInfo connectionInfo = ConnectionInfo.builder().u_id(sender).s_id(receiver).socketId("").build();
            ConnectionInfo.ConInfoList.add(connectionInfo);
        }
        recieverIdAndSocketId.put(receiver,socketId);
    }

    @RequestMapping("/chat")
    public String goToChatPage(Model model){
        model.addAttribute("user","张三");
        return "chatonline";
    }
}
