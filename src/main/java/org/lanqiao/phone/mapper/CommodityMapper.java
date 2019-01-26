package org.lanqiao.phone.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.lanqiao.phone.pojo.Commodity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommodityMapper {
    //查询商品
    @Select("select * from commodity where c_name like concat('%',#{c_name},'%')")
    //public Commodity selectCommodity(@Param("c_name") String c_name);
    public List<Commodity> selectCommodity(@Param("c_name") String c_name);

    //点击商品到商品详情
    @Select("select * from commodity where c_name = #{c_name}")
    public Commodity linkxiangqing(String c_name);
}
