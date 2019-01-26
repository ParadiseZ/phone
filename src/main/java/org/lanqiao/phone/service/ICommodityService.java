package org.lanqiao.phone.service;

import org.lanqiao.phone.pojo.Commodity;

import java.util.List;

public interface ICommodityService {
    //查找商品
    public List<Commodity> selectCommidity(String c_name);

    //点击跳转到详情
    public Commodity link(String c_name);
}
