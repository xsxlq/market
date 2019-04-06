package com.xsxlq.market.dao;

import com.xsxlq.market.pojo.UserGoods;

import java.util.List;

public interface UserGoodsMapper {
    int insert(UserGoods record);

    int insertSelective(UserGoods record);

    List<UserGoods> selectByUserId(int id);

    UserGoods selectByGoodsId(int id);

    int delete(int userId,int goodsId);

    List<UserGoods> selectAll(UserGoods userGoods);


}