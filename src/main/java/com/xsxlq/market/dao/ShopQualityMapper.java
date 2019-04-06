package com.xsxlq.market.dao;

import com.xsxlq.market.pojo.ShopQuality;

import java.util.List;

public interface ShopQualityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ShopQuality record);

    int insertSelective(ShopQuality record);

    ShopQuality selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ShopQuality record);

    int updateByPrimaryKey(ShopQuality record);

    List<ShopQuality> selectAll();
}