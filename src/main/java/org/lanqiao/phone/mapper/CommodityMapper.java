package org.lanqiao.phone.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.lanqiao.phone.pojo.Commodity;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CommodityMapper {
    //查询商品
    @Select("select * from commodity" )
    public CommodityMapper Cha(Commodity commodity);
}
