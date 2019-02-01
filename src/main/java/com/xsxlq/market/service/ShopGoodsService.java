package com.xsxlq.market.service;

import com.xsxlq.market.pojo.ShopGoods;

import java.util.List;

public interface ShopGoodsService {
    /**
     * 查询所有商品
     */
    List<ShopGoods> selectAllGoods();
    /**
     * 通过商品id删除
    * @param goodsid
     * @return
     */
    int deleteByPrimaryKey(Integer goodsid);

    /**
     * 添加商品
     * @param record
     * @return
     */
    int insert(ShopGoods record);

    /**
     * 添加商品
     * @param record
     * @return
     */
    int insertSelective(ShopGoods record);

    /**
     * 通过id查询单个商品
     * @param goodsid
     * @return
     */
    ShopGoods selectByPrimaryKey(Integer goodsid);

    /**
     * 修改商品
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(ShopGoods record);

    /**
     * 修改商品
     * @param record
     * @return
     */
    int updateByPrimaryKey(ShopGoods record);
}
