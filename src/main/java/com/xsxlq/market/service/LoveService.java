package com.xsxlq.market.service;

import com.xsxlq.market.pojo.Love;

import java.util.List;

public interface LoveService {
    int insert(Love record);

    List<Love> selectByUserId(int userId);

    List<Love> selectByGoodsId(int goodsId);

    int delete(Love l);

    int selectByUserIdAndGoodsId(Love love);

    int selectCountByGoodsId(int goodsId);

    List<Love> selectGoodsListByLoves();
}
