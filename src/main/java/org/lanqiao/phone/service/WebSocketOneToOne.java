package org.lanqiao.phone.service;


import net.sf.json.JSONObject;
import org.lanqiao.phone.pojo.News;
import org.lanqiao.phone.pojo.SessionList;
import org.lanqiao.phone.config.WebSocketInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.text.html.HTMLDocument;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端,
 *                 注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端
 */
@ServerEndpoint(value = "/webSocketOneToOne/{param}")
@Component
public class WebSocketOneToOne {
    /*@Autowired
    INewsService newsService;*/
//    WebSocketInService e = (WebSocketInService)ContextLoaderListener.getCurrentWebApplicationContext().getBean("tests");
    // 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount;
    //实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key为用户标识
    private static Map<String,WebSocketOneToOne> connections = new ConcurrentHashMap<>();
    // 与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    private String role;
    private String socketId;
    private String senderName;

    /**
     * 连接建立成功调用的方法
     *
     * @param session
     *            可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(@PathParam("param") String param,Session session) {
        this.session = session;

        String[] arr = param.split(",");
        this.role = arr[0];             //用户标识
        this.socketId = arr[1];         //会话标识
        this.senderName = arr[2];
        connections.put(role,this);     //添加到map中
        addOnlineCount();               // 在线数加
        System.out.println(this.session+"=="+this.role+"");
        System.out.println("有新连接加入！新用户："+role+",当前在线人数为" + getOnlineCount());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        connections.remove(role);  // 从map中移除
        for(SessionList sessionList:SessionList.sessionList){
            if(role.equals(sessionList.getSenderId())){
            }
        }
        subOnlineCount();          // 在线数减
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message
     *            客户端发送过来的消息
     * @param session
     *            可选的参数
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息:" + message);
        JSONObject json=JSONObject.fromObject(message);
        String string = null;  //需要发送的信息
        String toStr[] = null;      //发送对象的用户标识
        String to = null;
        String recName = null;
        if(json.has("message")){
            string = (String) json.get("message");
        }
        if(json.has("role")){
            toStr = ((String) json.get("role")).split(",");
            to = toStr[0];
            recName = toStr[1];
        }
        send(string,role,to,socketId,recName);
    }

    /**
     * 发生错误时调用
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }


    //发送给指定角色
    public synchronized void send(String msg,String from,String to,String socketId,String recName){

 /*       Collection<WebSocketOneToOne> ss= connections.values();
        for(WebSocketOneToOne va:ss){
            System.out.println(va.role+"--role");
            System.out.println(va.session+"---session");
        }
        Set<String> kest = connections.keySet();
        Iterator<String> sd = kest.iterator();
        while (sd.hasNext()){
            System.out.println(sd.next()+"==key");
        }*/
        String state="";
        try {
            //to指定用户
            WebSocketOneToOne con = connections.get(to);
            if(con!=null){
                if(socketId==con.socketId||con.socketId.equals(socketId)){

//                    recName = newsService.getUserNameByNum(recId,recName);
                    //显示对方消息
//                    con.session.getBasicRemote().sendText(from+"说："+msg);
                    con.session.getBasicRemote().sendText("<div class='msgmain'>\n" +
                            "            <p class='msg-left'>"+recName+"</p><div class='msgshow msg-left'><p>"+msg+"</p></div>\n" +
                            "        </div>");
                }
            }else {
                state="未读";
            }
            //from具体用户
            WebSocketOneToOne confrom = connections.get(from);
            if(confrom!=null){
                if(socketId==confrom.socketId||confrom.socketId.equals(socketId)){

//                    senderName = newsService.getUserNameByNum(senderId,senderName);

                    //显示自己消息
//                    confrom.session.getBasicRemote().sendText(from+"说："+msg);
                    confrom.session.getBasicRemote().sendText("<div class='msgmain'>\n" +
                            "            <p class='msg-right'>"+confrom.senderName+"</p><div class='msgshow msg-right fr'><p>"+msg+"</p></div>\n" +
                            "        </div>,"+state);
                }
            }else {
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

//        newsService.inserNews(senderId,strSen[1],recId,strRec[1],msg,state);
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketOneToOne.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketOneToOne.onlineCount--;
    }
}

