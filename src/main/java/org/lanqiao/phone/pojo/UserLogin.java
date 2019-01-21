package org.lanqiao.phone.pojo;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserLogin {
    private int u_Id;//用户ID
    private String u_name;//用户姓名
    private String u_password;//用户密码
    private String u_nickname;//用户昵称
    private long u_phone;//用户电话
    private String u_email;//用户邮箱
}
