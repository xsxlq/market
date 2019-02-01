package com.xsxlq.market.dao;

import com.xsxlq.market.pojo.ShopGoodsType;

public interface ShopGoodsTypeMapper {
    int deleteByPrimaryKey(Integer goodstypeid);

    int insert(ShopGoodsType record);

    int insertSelective(ShopGoodsType record);

    ShopGoodsType selectByPrimaryKey(Integer goodstypeid);

    int updateByPrimaryKeySelective(ShopGoodsType record);

    int updateByPrimaryKey(ShopGoodsType record);
}