package com.xsxlq.market.service;

import com.xsxlq.market.pojo.GoodsSay;

import java.util.List;

public interface GoodsSayService {
    List<GoodsSay> selectByGoodsId(int gid);

    int insertSelective(GoodsSay say);

    int deleteByPrimaryKey(Integer id);

    List<GoodsSay> selectALLSelective(GoodsSay gs);
}
