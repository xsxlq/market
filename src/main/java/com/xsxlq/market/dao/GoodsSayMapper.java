package com.xsxlq.market.dao;

import com.xsxlq.market.pojo.GoodsSay;

import java.util.List;

public interface GoodsSayMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsSay record);

    int insertSelective(GoodsSay record);

    GoodsSay selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoodsSay record);

    int updateByPrimaryKey(GoodsSay record);

    List<GoodsSay> selectByGoodsId(int gid);

    List<GoodsSay> selectALLSelective(GoodsSay gs);
}