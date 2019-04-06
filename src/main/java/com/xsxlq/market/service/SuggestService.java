package com.xsxlq.market.service;

import com.xsxlq.market.pojo.Suggest;

import java.util.List;

public interface SuggestService {
    int insertSelective(Suggest record);

    List<Suggest> selectALLSelective(Suggest s);

    int deleteByPrimaryKey(Integer id);
}
