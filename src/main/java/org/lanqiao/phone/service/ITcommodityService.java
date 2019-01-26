package org.lanqiao.phone.service;

import org.lanqiao.phone.pojo.Tcommodity;
import org.lanqiao.phone.pojo.User;

import java.util.List;

public interface ITcommodityService {
    //遍历所有的商品信息
    public List<Tcommodity> findAll();
    //用户进行登入判断
    public User TgyLogin(User user);
    //通过id删除商品
    public Tcommodity tgyDetele(int c_Id);
    //插入数据
    public void tgyInsert(String c_name,String c_xinghao,String c_chicun,String c_time,String c_xitong,
                                String c_cpu,String c_dianchi,String c_houshe,String c_chongdianqi,String c_zhiwen);
}
