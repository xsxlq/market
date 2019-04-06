package com.xsxlq.market.dao;

import com.xsxlq.market.pojo.EmailInfo;

import java.util.List;

public interface EmailInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EmailInfo record);

    int insertSelective(EmailInfo record);

    EmailInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EmailInfo record);

    int updateByPrimaryKey(EmailInfo record);

    List<EmailInfo> selectALLSelective(EmailInfo emailInfo);
}