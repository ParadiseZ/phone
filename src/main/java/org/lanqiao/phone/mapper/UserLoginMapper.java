package org.lanqiao.phone.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.lanqiao.phone.pojo.UserLogin;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserLoginMapper {
    //添加个人用户到数据库
    @Select("select * from user where u_name=#{u_name} and u_password=#{u_password}")
    public UserLogin Login(UserLogin userLogin);

}
