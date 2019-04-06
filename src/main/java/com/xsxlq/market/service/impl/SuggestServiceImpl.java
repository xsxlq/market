package com.xsxlq.market.service.impl;

import com.xsxlq.market.dao.SuggestMapper;
import com.xsxlq.market.pojo.Suggest;
import com.xsxlq.market.service.SuggestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("SuggestService")
public class SuggestServiceImpl implements SuggestService {
    @Autowired
    private SuggestMapper suggestMapper;
    @Override
    public int insertSelective(Suggest record) {
        return suggestMapper.insertSelective(record);
    }

    @Override
    public List<Suggest> selectALLSelective(Suggest s) {
        return suggestMapper.selectALLSelective(s);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return suggestMapper.deleteByPrimaryKey(id);
    }
}
