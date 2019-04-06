package com.xsxlq.market.dao;

import com.xsxlq.market.pojo.Love;

import java.util.List;

public interface LoveMapper {
    int insert(Love record);

    List<Love> selectByUserId(int userId);

    List<Love> selectByGoodsId(int GoodsId);

    int delete(Love l);

    Love selectByUserIdAndGoodsId(Love love);

    Integer selectCountByGoodsId(int goodsId);

    /**
     * 查询收藏了量最多的前n条数据
     */
    List<Love> selectGoodsListByLoves();

}