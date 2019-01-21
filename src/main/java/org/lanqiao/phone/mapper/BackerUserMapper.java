package org.lanqiao.phone.mapper;

import org.apache.ibatis.annotations.*;
import org.lanqiao.phone.pojo.BackUser;
import org.lanqiao.phone.pojo.Rate;
import org.lanqiao.phone.pojo.ShopUser;
import org.lanqiao.phone.pojo.User;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@Mapper
@Repository
public interface BackerUserMapper {
    //管理员登录
    @Select("select * from backstage where b_user=#{b_user}")
    public BackUser getImfor(String b_user);
    //商家数量查询
    @Select( "select count(*)from shop" )
    public int getShopNum();
    //查询用户数量
    @Select( "select count(*) from user" )
    public int getUserNum();
    //查询所有用户
    @Select( "select * from user limit #{startPage},#{pageSize}" )
    public List<User> getUserList(@Param("startPage") int startPage, @Param("pageSize")int pageSize);
    //逐个删除用户
    @Delete( "delete from user where u_Id= #{u_Id}")
    public void deleteUser(int u_Id );
    //根据用户名查找
    @Select("select * from user where u_name like concat('%',#{u_name},'%') limit #{startPage},#{pageSize}")
    public List<User> searchUuserByUsername(@Param("u_name") String u_name,@Param("startPage") int startPage,@Param("pageSize")int pageSize);
    //批量删除用户
    @Delete( "delete from user where u_Id=#{u_Id}")
    public void deleteAll(int[] a);
     //查询所有商家
    @Select( "select *from shop limit #{startPage},#{pageSize}")
    public List<ShopUser> getSopList(@Param( "startPage" ) int startPage,@Param("pageSize")int pageSize);
    //逐个删除商家
    @Delete( "delete from shop where s_Id=#{s_Id}")
    public  void deleteShopUser(int s_Id);
    //通过店铺名称查询商家
    @Select( "select *from shop where s_shopname like concat('%',#{s_shopname},'%') limit #{startPage},#{pageSize}")
    public List<ShopUser> searchShop(@Param( "s_shopname") String s_shopname,@Param("startPage") int startPage,@Param("pageSize")int pageSize);
   //评论查询
    @Select("select u.u_name,r.r_news,c.c_name,s.s_shopname from user u,shop s,rate r,commodity c where u.u_Id=r.u_Id and r.c_Id=c.c_Id and c.s_Id=s.s_Id limit #{startPage},#{pageSize} ")
    public List<Rate> getPingLun(@Param("startPage") int startPage, @Param("pageSize")int pageSize);
    //通过c_Id逐个删除评论
    @Delete("delete from rate where r_Id=#{r_Id}")
    public void  deleteping(int r_Id);
    //查询评论数量
    @Select( "select count(*)from rate")
    public  int  pingLunNum();
    //cha询为审批商家
    @Select( "select *from shop where s_agree='未通过'")
    public List<ShopUser> getWeiShen();
    //tong计为审批商家数量
    @Select( "select count(*) from shop where  s_agree='未通过'" )
    public  int getnoPassShopNum();
    //通过审批
  @Update( "update shop  set  s_agree='通过'where s_Id=#{s_Id} " )
    public ShopUser updatePass();
}
