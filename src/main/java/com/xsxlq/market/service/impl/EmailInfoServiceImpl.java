package com.xsxlq.market.service.impl;

import com.xsxlq.market.dao.EmailInfoMapper;
import com.xsxlq.market.pojo.EmailInfo;
import com.xsxlq.market.service.EmailInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("EmailInfoService")
public class EmailInfoServiceImpl implements EmailInfoService {

    @Autowired
    private EmailInfoMapper emailInfoMapper;
    @Override
    public List<EmailInfo> selectALLSelective(EmailInfo emailInfo) {
        return emailInfoMapper.selectALLSelective(emailInfo);
    }

    @Override
    public int insertSelective(EmailInfo record) {
        return emailInfoMapper.insertSelective(record);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return emailInfoMapper.deleteByPrimaryKey(id);
    }
}
