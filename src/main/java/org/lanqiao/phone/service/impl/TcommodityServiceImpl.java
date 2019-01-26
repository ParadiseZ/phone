package org.lanqiao.phone.service.impl;

import org.apache.ibatis.annotations.Param;
import org.lanqiao.phone.mapper.TcommodityMapper;
import org.lanqiao.phone.pojo.Tcommodity;
import org.lanqiao.phone.pojo.User;
import org.lanqiao.phone.service.ITcommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TcommodityServiceImpl implements ITcommodityService {
    @Autowired
    TcommodityMapper tcommodityMapper;
    @Override
    public List<Tcommodity> findAll() {

        return tcommodityMapper.selectAll();
    }

    @Override
    public User TgyLogin(User user) {
        return tcommodityMapper.TgyLogin(user);
    }

    @Override
    public Tcommodity tgyDetele(int c_Id) {

        return tcommodityMapper.deteleById(c_Id);
    }

    @Override
    public void tgyInsert(String c_name,String c_xinghao,String c_chicun,String c_time,String c_xitong, String c_cpu,String c_dianchi,String c_houshe,String c_chongdianqi,String c_zhiwen) {
        tcommodityMapper.insertCommitt(c_name,c_xinghao,c_chicun,c_time,c_xitong,c_cpu,c_dianchi,c_houshe,c_chongdianqi,c_zhiwen);
    }
}
