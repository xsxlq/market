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

    /**
     * 模糊查询
     * @param shopGoods
     * @return
     */
    List<ShopGoods> selectiveGoods(ShopGoods shopGoods);

    /**
     * 添加返回主键
     * @param record
     * @return
     */
    int insertSelectiveReturnPK(ShopGoods record);

//    List<ShopGoods> selectMyList(List<Integer> list);
}