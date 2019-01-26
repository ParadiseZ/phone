package org.lanqiao.phone.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.lanqiao.phone.pojo.Tcommodity;
import org.lanqiao.phone.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface TcommodityMapper {
    @Select("select * from commodity")
    public List<Tcommodity> selectAll();

    //通过id进行删除
    @Select("delete from commodity where c_Id=#{c_Id}")
    public Tcommodity deteleById(int c_Id);

    //插入数据
    @Insert("insert into commodity(c_name,c_xinghao,c_chicun,c_time,c_xitong,c_cpu,c_dianchi,c_houshe,c_chongdianqi,c_zhiwen) values(#{c_name},#{c_xinghao},#{c_chicun},#{c_time},#{c_xitong},#{c_cpu},#{c_dianchi},#{c_houshe},#{c_chongdianqi},#{c_zhiwen})")
    public void insertCommitt(@Param("c_name")String c_name,@Param("c_xinghao")String c_xinghao,@Param("c_chicun")String c_chicun,@Param("c_time")String c_time,@Param("c_xitong")String c_xitong,@Param("c_cpu")String c_cpu,@Param("c_dianchi")String c_dianchi,@Param("c_houshe")String c_houshe,@Param("c_chongdianqi")String c_chongdianqi,@Param("c_zhiwen")String c_zhiwen);

    //用户登入
    @Select("select * from user where u_name=#{u_name} and u_password=#{u_password}")
    public User TgyLogin(User user);

}
