package com.xsxlq.market.service;

import com.github.pagehelper.PageInfo;
import com.xsxlq.market.pojo.ShopGoods;

import java.util.List;

public interface ShopGoodsService {
    /**
     * 分页查询
     */
    public PageInfo<ShopGoods> getPageGoodsList(Integer userId,int pageNum, int pageSize,ShopGoods shopGoods);
    /**
     * 查询所有商品
     */
    List<ShopGoods> selectAllGoods(Integer userId);

    /**
     * 模糊查询
     * @return
     */
    List<ShopGoods> selectiveGoods(Integer userId,ShopGoods shopGoods);
    /**
     * 通过商品id删除
    * @param goodsid
     * @return
     */
    int deleteByPrimaryKey(Integer goodsid);

    /**
     * 添加返回主键
     * @param record
     * @return
     */
    int insertSelectiveReturnPK(ShopGoods record);
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
