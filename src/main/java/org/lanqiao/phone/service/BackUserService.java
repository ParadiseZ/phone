package org.lanqiao.phone.service;

import org.lanqiao.phone.pojo.BackUser;
import org.lanqiao.phone.pojo.Rate;
import org.lanqiao.phone.pojo.ShopUser;
import org.lanqiao.phone.pojo.User;

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
    public List<ShopUser> getNoPass();
    //tong计为审批商家数量
    public  int getNoPAANum();
}
