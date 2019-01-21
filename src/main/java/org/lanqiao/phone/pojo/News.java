package org.lanqiao.phone.pojo;

import lombok.*;

import java.util.Date;

@Data
public class News {
    private int id;
    private int u_Id;   //发送者ID
    private String u_IdType;
    private int s_Id;   //接收者ID
    private String s_IdType;
    private String n_news;  //消息内容
    private String n_state; //消息状态
}
