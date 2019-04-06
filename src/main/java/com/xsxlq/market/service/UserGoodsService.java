package com.xsxlq.market.service;

import com.xsxlq.market.pojo.UserGoods;

import java.util.List;
import java.util.Map;

public interface UserGoodsService {
    /**
     * @param record
     * @return
     */
    Map<String,String> insert(UserGoods record);

    /**
     * 根据用户id查询商品id集合
    * @param id
     * @return
     */
    List<UserGoods> selectByUserId(int id);

    /**
     * 根据商品id查询用户id
     * @param id
     * @return
     */
    UserGoods selectByGoodsId(int id);

    /**
     * 根据用户id和商品id删除一条记录
     * @param userId
     * @param goodsId
     * @return
     */
    int delete(int userId,int goodsId);

    /**
     * 判断是否已存在
     * @param userId
     * @param goodsId
     * @return
     */
    boolean isLoved(int userId,int goodsId);

    /**
     * 所有
     */
    List<UserGoods> selectAll(UserGoods userGoods);
}
