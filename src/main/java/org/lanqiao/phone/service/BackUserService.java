package org.lanqiao.phone.service;

import org.apache.ibatis.annotations.Param;
import org.lanqiao.phone.pojo.*;

import java.util.List;

public interface BackUserService {
    public BackUser getBackUser(String b_user);
    //商家数量
    public int getShopnum();
    //统计用户数量
    public int getUsernum();
    //查询.所有用户
    public List<User> getUserlist(int startPage,int pageSize);
    //单个删除用户
    public  void DeleteUser(int u_Id);
    //根据用户名查找
    public  List<User> searchUser(String u_name,int startPage,int pageSize);
    //批量删除用户
    public  void deleteAlluser(int[] a);
    //查询所有商家
    public List<ShopUser> getShopList(int startPage,int pageSize);
    //单个删除商家
    public void DeleteShop( int s_Id);
    //通过店铺名称查询商家
    public List<ShopUser> searchShopUser(String s_shopname,int startPage,int pageSize);
    //查询评论
    public List<Rate> getPingLUN(int startPage,int pageSize);
     //通过c_Id逐个删除评论
    public void DeletePing(int r_Id);
    //查询评论数量
    public int getPinNum();
    //cha询未审批商家
    public List<ShopUser> getNoPass( int startPage,int pageSize);
    //tong计为审批商家数量
    public  int getNoPAANum();
   //通过审批
  public void UpDatePass( String s_shopname);
    //
    public ShopUser thing(String s_shopname);
    //拒绝通过并发送消息
    public void deaftPass(String s_shopname);
    //通过客户查询评论
    public List<Rate> getUserPING( String r_news,int startPage,int pageSize );
    //查询所有tousu
    public List<TouSu> getTList(int startPage,int pageSize );
    //tong过t_Id逐个删除投诉
    public  void deleteTOU(int t_Id);
    //统计未处理投诉数量
    public int getTousuNUM();
    //处理投诉（用户）
    public void SendEmailY(int t_Id);
    //tong计所有投诉shu量
    public  int getAllTouNUM();
}
