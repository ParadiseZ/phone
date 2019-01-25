package org.lanqiao.phone.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.lanqiao.phone.pojo.News;
import org.lanqiao.phone.pojo.UserIdAndType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface INewsDao {

    @Select("select count(*) from news where u_id=#{u_id} and n_state='未读'")
    public Integer getUnreadNewsAllNum(int u_id);
    @Select("select * from news where u_id=#{u_id} and u_idType=#{u_idType} and s_id=#{s_id} and s_idType=#{s_idType} or u_id=#{s_id} and u_idType=#{s_idType} and s_id=#{u_id} and s_idType=#{u_idType} order by id")
    public List<News> getAllNews(@Param("u_id") int u_id,@Param("u_idType") String u_idType,@Param("s_id")int s_id,@Param("s_idType")String s_idType);
    //作为发送者,接收者信息
    @Select("select distinct s_id userIdFind,s_IdType userType from news where u_id=#{u_id} and u_idType=#{u_idType}")
    public List<UserIdAndType> getIdAndTypeBySend(@Param("u_id") int u_id,@Param("u_idType") String u_idType);
    //作为接收者，发送者信息
    @Select("select distinct u_id userIdFind,u_IdType userType from news where s_id=#{s_id} and s_idType=#{s_idType}")
    public List<UserIdAndType> getIdAndTypeByRec(@Param("s_id") int s_id,@Param("s_idType") String s_idType);
    //从个人用户表中查询用户名
    @Select("select u_name from user where u_Id=#{u_Id}")
    public String getUserNameFromUser(int u_Id);
    //从商家用户表中查询用户名
    @Select("select s_shopname from shop where s_Id=#{s_Id}")
    public String getUserNameFromShop(int s_Id);
    //从管理员用户表中查询用户名
    @Select("select b_user from backstage where b_Id=#{b_Id}")
    public String getUserNameFromBackstage(int b_Id);
    @Insert("insert into news(u_Id,u_idType,s_Id,s_idType,n_news,n_state) values(#{u_Id},#{u_IdType},#{s_Id},#{s_IdType},#{n_news},#{n_state})")
    public void inserNews(@Param("u_Id")int u_Id,@Param("u_IdType")String u_IdType,@Param("s_Id")int s_Id,@Param("s_IdType")String s_IdType,@Param("n_news")String n_news,@Param("n_state")String n_state);
}
