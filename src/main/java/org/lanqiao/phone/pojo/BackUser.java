package org.lanqiao.phone.pojo;

public class BackUser {
    private  int b_Id;
    private  String b_user;
    private  String b_password;

    public BackUser() {
    }

    public BackUser(int b_Id, String b_user, String b_password) {
        this.b_Id = b_Id;
        this.b_user = b_user;
        this.b_password = b_password;
    }

    public BackUser(String b_user, String b_password) {
        this.b_user = b_user;
        this.b_password = b_password;
    }

    public int getB_Id() {
        return b_Id;
    }

    public void setB_Id(int b_Id) {
        this.b_Id = b_Id;
    }

    public String getB_user() {
        return b_user;
    }

    public void setB_user(String b_user) {
        this.b_user = b_user;
    }

    public String getB_password() {
        return b_password;
    }

    public void setB_password(String b_password) {
        this.b_password = b_password;
    }

    @Override
    public String toString() {
        return "BackUser{" +
                "b_Id=" + b_Id +
                ", b_user='" + b_user + '\'' +
                ", b_password='" + b_password + '\'' +
                '}';
    }
}
