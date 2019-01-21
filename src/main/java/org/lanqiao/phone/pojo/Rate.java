package org.lanqiao.phone.pojo;

public class Rate {
    private int r_Id;
    private int c_Id;
    private int u_Id;
    private int s_Id;
    private String r_news;
    private String u_name;
    private String c_name;
    private  String s_shopname;

    public Rate() {
    }

    public Rate(int c_Id, int u_Id, int s_Id, String r_news, String u_name, String c_name, String s_shopname) {
        this.c_Id = c_Id;
        this.u_Id = u_Id;
        this.s_Id = s_Id;
        this.r_news = r_news;
        this.u_name = u_name;
        this.c_name = c_name;
        this.s_shopname = s_shopname;
    }

    public Rate(int r_Id, int c_Id, int u_Id, int s_Id, String r_news, String u_name, String c_name, String s_shopname) {
        this.r_Id = r_Id;
        this.c_Id = c_Id;
        this.u_Id = u_Id;
        this.s_Id = s_Id;
        this.r_news = r_news;
        this.u_name = u_name;
        this.c_name = c_name;
        this.s_shopname = s_shopname;
    }

    public int getR_Id() {
        return r_Id;
    }

    public void setR_Id(int r_Id) {
        this.r_Id = r_Id;
    }

    public int getC_Id() {
        return c_Id;
    }

    public void setC_Id(int c_Id) {
        this.c_Id = c_Id;
    }

    public int getU_Id() {
        return u_Id;
    }

    public void setU_Id(int u_Id) {
        this.u_Id = u_Id;
    }

    public int getS_Id() {
        return s_Id;
    }

    public void setS_Id(int s_Id) {
        this.s_Id = s_Id;
    }

    public String getR_news() {
        return r_news;
    }

    public void setR_news(String r_news) {
        this.r_news = r_news;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public String getS_shopname() {
        return s_shopname;
    }

    public void setS_shopname(String s_shopname) {
        this.s_shopname = s_shopname;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "r_Id=" + r_Id +
                ", c_Id=" + c_Id +
                ", u_Id=" + u_Id +
                ", s_Id=" + s_Id +
                ", r_news='" + r_news + '\'' +
                ", u_name='" + u_name + '\'' +
                ", c_name='" + c_name + '\'' +
                ", s_shopname='" + s_shopname + '\'' +
                '}';
    }
}
