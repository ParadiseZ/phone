package org.lanqiao.phone.service.impl;

import org.lanqiao.phone.mapper.CommodityMapper;
import org.lanqiao.phone.pojo.Commodity;
import org.lanqiao.phone.service.ICommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommodityServiceImpl implements ICommodityService {

    @Autowired
    CommodityMapper commodityMapper;

    @Override
    public List<Commodity> selectCommidity(String c_name) {
        return commodityMapper.selectCommodity(c_name);
    }

    @Override
    public Commodity link(String c_name) {
        return commodityMapper.linkxiangqing(c_name);
    }
}
