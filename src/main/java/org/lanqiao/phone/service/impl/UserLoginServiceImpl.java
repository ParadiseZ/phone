package org.lanqiao.phone.service.impl;

import org.lanqiao.phone.mapper.UserLoginMapper;
import org.lanqiao.phone.pojo.UserLogin;
import org.lanqiao.phone.service.IUserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLoginServiceImpl implements IUserLoginService {
    @Autowired
    UserLoginMapper userLoginMapper;

    @Override
    public UserLogin Login(UserLogin userLogin){
        return userLoginMapper.Login(userLogin);
    }
}
