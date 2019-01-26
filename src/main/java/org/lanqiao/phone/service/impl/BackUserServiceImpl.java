package org.lanqiao.phone.service.impl;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.lanqiao.phone.mapper.BackerUserMapper;
import org.lanqiao.phone.pojo.*;
import org.lanqiao.phone.service.BackUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public class BackUserServiceImpl implements BackUserService {
    @Autowired
    BackerUserMapper backerUserMapper;
    @Override
    public BackUser getBackUser(String b_user) {
        return backerUserMapper.getImfor(b_user);
    }

    @Override
    public int getShopnum() {
       return backerUserMapper.getShopNum();

    }

    @Override
    public int getUsernum() {
        return backerUserMapper.getUserNum();
    }

    @Override
    public List<User> getUserlist(int startPage,int pageSize) {
        return backerUserMapper.getUserList(startPage,pageSize);
    }

    @Override
    public void DeleteUser(int u_Id) {
        backerUserMapper.deleteUser( u_Id );
    }

    @Override
    public List <User> searchUser(String u_name,int startPage,int pageSize) {
        return backerUserMapper.searchUuserByUsername(u_name,startPage,pageSize);
    }

    @Override
    public void deleteAlluser(int[] a) {
        for (int k:a){
        backerUserMapper.deleteAll( a );
            System.out.println(k);}
    }

    @Override
    public List <ShopUser> getShopList(int startPage,int pageSize) {
        return backerUserMapper.getSopList( startPage,pageSize);
    }

    @Override
    public void DeleteShop(int s_Id) {
        backerUserMapper.deleteShopUser( s_Id );
    }

    @Override
    public List <ShopUser> searchShopUser(String s_shopname, int startPage,int pageSize) {
        return backerUserMapper.searchShop( s_shopname,startPage,pageSize );
    }

    @Override
    public List <Rate> getPingLUN(int startPage,int pageSize) {
        return backerUserMapper.getPingLun(startPage,pageSize);
    }

    @Override
    public void DeletePing(int r_Id) {
        backerUserMapper.deleteping( r_Id );
    }

    @Override
    public int getPinNum() {
        return backerUserMapper.pingLunNum();
    }

    @Override
    public List <ShopUser> getNoPass( int startPage,int pageSize) {
        return backerUserMapper.getWeiShen(startPage,pageSize);
    }

    @Override
    public int getNoPAANum() {
        return backerUserMapper.getnoPassShopNum();
    }

    @Override
    public void UpDatePass(String s_shopname) {
        backerUserMapper.updatePass( s_shopname);
    }

    @Override
    public ShopUser thing(String s_shopname) {
        return backerUserMapper.thing(s_shopname);
    }

    @Override
    public void deaftPass(String s_shopname) {
        backerUserMapper.defaultPass(s_shopname);
    }

    @Override
    public List <Rate> getUserPING(String r_news, int startPage, int pageSize) {
        return backerUserMapper.getRateList( r_news,startPage,pageSize );
    }

    @Override
    public List <TouSu> getTList(int startPage,int pageSize ) {
        return backerUserMapper.getTouSuList(startPage,pageSize);
    }

    @Override
    public void deleteTOU(int t_Id) {
        backerUserMapper.deleteTousu( t_Id );
    }

    @Override
    public int getTousuNUM() {
        return backerUserMapper.getPINGnum();
    }

    @Override
    public void SendEmailY(int t_Id) {
        backerUserMapper.yonghuEmail( t_Id );
    }

    @Override
    public int getAllTouNUM() {
        return backerUserMapper.getAllTouSuNum();
    }

}
