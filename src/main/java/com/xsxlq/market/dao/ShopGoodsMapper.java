package com.xsxlq.market.dao;

import com.xsxlq.market.pojo.ShopGoods;

import java.util.List;

public interface ShopGoodsMapper {
    int deleteByPrimaryKey(Integer goodsid);

    int insert(ShopGoods record);

    int insertSelective(ShopGoods record);

    ShopGoods selectByPrimaryKey(Integer goodsid);

    int updateByPrimaryKeySelective(ShopGoods record);

    int updateByPrimaryKey(ShopGoods record);

    List<ShopGoods> selectAllGoods();
}