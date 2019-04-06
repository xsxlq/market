package com.xsxlq.market.dao;

import com.xsxlq.market.pojo.Suggest;

import java.util.List;

public interface SuggestMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Suggest record);

    int insertSelective(Suggest record);

    Suggest selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Suggest record);

    int updateByPrimaryKey(Suggest record);

    List<Suggest> selectALLSelective(Suggest s);
}