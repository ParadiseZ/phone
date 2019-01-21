package org.lanqiao.phone.pojo;

import lombok.*;

import java.util.Date;
import java.util.concurrent.CopyOnWriteArrayList;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ConnectionInfo {
    private int u_id;   //发送者ID
    private String u_idType;    //发送者类型
    private int s_id;   //接收者ID
    private String s_idType;    //接收者类型
    private Date socketId;  //会话标识ID
    public static CopyOnWriteArrayList<ConnectionInfo> ConInfoList = new CopyOnWriteArrayList();
}
