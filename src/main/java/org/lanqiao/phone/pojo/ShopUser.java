package org.lanqiao.phone.pojo;

import lombok.Data;

@Data
public class ShopUser {
    private int s_Id;
    private String s_user;
    private  String s_password;
    private  String s_shopname;
    private  String s_license;
    private int s_licenseid;
    private  String s_name;
    private  String s_idcard;
    private  String s_money;
    private  String s_home;
    private  String s_agree;
    private String s_pinglun;
    private String s_detail;
    private String s_emial;
    public ShopUser() {
    }

    public ShopUser(String s_user, String s_password, String s_shopname, String s_license, int s_licenseid, String s_name, String s_idcard, String s_money, String s_home, String s_agree) {
        this.s_user = s_user;
        this.s_password = s_password;
        this.s_shopname = s_shopname;
        this.s_license = s_license;
        this.s_licenseid = s_licenseid;
        this.s_name = s_name;
        this.s_idcard = s_idcard;
        this.s_money = s_money;
        this.s_home = s_home;
        this.s_agree = s_agree;
    }

    public ShopUser(int s_Id, String s_user, String s_password, String s_shopname, String s_license, int s_licenseid, String s_name, String s_idcard, String s_money, String s_home, String s_agree) {
        this.s_Id = s_Id;
        this.s_user = s_user;
        this.s_password = s_password;
        this.s_shopname = s_shopname;
        this.s_license = s_license;
        this.s_licenseid = s_licenseid;
        this.s_name = s_name;
        this.s_idcard = s_idcard;
        this.s_money = s_money;
        this.s_home = s_home;
        this.s_agree = s_agree;
    }

    public String getS_pinglun() {
        return s_pinglun;
    }

    public void setS_pinglun(String s_pinglun) {
        this.s_pinglun = s_pinglun;
    }

    public String getS_detail() {
        return s_detail;
    }

    public void setS_detail(String s_detail) {
        this.s_detail = s_detail;
    }

    public int getS_Id() {
        return s_Id;
    }

    public void setS_Id(int s_Id) {
        this.s_Id = s_Id;
    }

    public String getS_user() {
        return s_user;
    }

    public void setS_user(String s_user) {
        this.s_user = s_user;
    }

    public String getS_password() {
        return s_password;
    }

    public void setS_password(String s_password) {
        this.s_password = s_password;
    }

    public String getS_shopname() {
        return s_shopname;
    }

    public void setS_shopname(String s_shopname) {
        this.s_shopname = s_shopname;
    }

    public String getS_license() {
        return s_license;
    }

    public void setS_license(String s_license) {
        this.s_license = s_license;
    }

    public int getS_licenseid() {
        return s_licenseid;
    }

    public void setS_licenseid(int s_licenseid) {
        this.s_licenseid = s_licenseid;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public String getS_idcard() {
        return s_idcard;
    }

    public void setS_idcard(String s_idcard) {
        this.s_idcard = s_idcard;
    }

    public String getS_money() {
        return s_money;
    }

    public void setS_money(String s_money) {
        this.s_money = s_money;
    }

    public String getS_home() {
        return s_home;
    }

    public void setS_home(String s_home) {
        this.s_home = s_home;
    }

    public String getS_agree() {
        return s_agree;
    }

    public void setS_agree(String s_agree) {
        this.s_agree = s_agree;
    }

    @Override
    public String toString() {
        return "ShopUser{" +
                "s_Id=" + s_Id +
                ", s_user='" + s_user + '\'' +
                ", s_password='" + s_password + '\'' +
                ", s_shopname='" + s_shopname + '\'' +
                ", s_license='" + s_license + '\'' +
                ", s_licenseid=" + s_licenseid +
                ", s_name='" + s_name + '\'' +
                ", s_idcard='" + s_idcard + '\'' +
                ", s_money='" + s_money + '\'' +
                ", s_home='" + s_home + '\'' +
                ", s_agree='" + s_agree + '\'' +
                '}';
    }
}
