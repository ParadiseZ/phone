package org.lanqiao.phone.pojo;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class SessionList {
    private String senderId;
    private String socketId;
    private String recieverId;
    public static CopyOnWriteArrayList<SessionList> sessionList = new CopyOnWriteArrayList();

    public SessionList(String senderId, String socketId, String recieverId) {
        this.senderId = senderId;
        this.socketId = socketId;
        this.recieverId = recieverId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getSocketId() {
        return socketId;
    }

    public void setSocketId(String socketId) {
        this.socketId = socketId;
    }

    public String getRecieverId() {
        return recieverId;
    }

    public void setRecieverId(String recieverId) {
        this.recieverId = recieverId;
    }

    @Override
    public String toString() {
        return "SessionList{" +
                "senderId='" + senderId + '\'' +
                ", socketId='" + socketId + '\'' +
                ", recieverId='" + recieverId + '\'' +
                '}';
    }
    public synchronized void addSessionChatNum(){
        sessionList.add(this);
    }

    public static List getSessionList(){
        return sessionList;
    }
}
