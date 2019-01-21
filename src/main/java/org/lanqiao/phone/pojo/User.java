package org.lanqiao.phone.pojo;

public class User {
    private int u_Id;
    private String u_name;
    private String u_password;
    private String u_nickname;
    private int u_phone;
    private String u_email;

    public User() {
    }

    public User(String u_name, String u_password, String u_nickname, int u_phone, String u_email) {
        this.u_name = u_name;
        this.u_password = u_password;
        this.u_nickname = u_nickname;
        this.u_phone = u_phone;
        this.u_email = u_email;
    }

    public User(int u_Id, String u_name, String u_password, String u_nickname, int u_phone, String u_email) {
        this.u_Id = u_Id;
        this.u_name = u_name;
        this.u_password = u_password;
        this.u_nickname = u_nickname;
        this.u_phone = u_phone;
        this.u_email = u_email;
    }

    public int getU_Id() {
        return u_Id;
    }

    public void setU_Id(int u_Id) {
        this.u_Id = u_Id;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getU_password() {
        return u_password;
    }

    public void setU_password(String u_password) {
        this.u_password = u_password;
    }

    public String getU_nickname() {
        return u_nickname;
    }

    public void setU_nickname(String u_nickname) {
        this.u_nickname = u_nickname;
    }

    public int getU_phone() {
        return u_phone;
    }

    public void setU_phone(int u_phone) {
        this.u_phone = u_phone;
    }

    public String getU_email() {
        return u_email;
    }

    public void setU_email(String u_email) {
        this.u_email = u_email;
    }

    @Override
    public String toString() {
        return "User{" +
                "u_Id=" + u_Id +
                ", u_name='" + u_name + '\'' +
                ", u_password='" + u_password + '\'' +
                ", u_nickname='" + u_nickname + '\'' +
                ", u_phone=" + u_phone +
                ", u_email='" + u_email + '\'' +
                '}';
    }
}
