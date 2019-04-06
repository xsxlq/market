package com.xsxlq.market.service;

import com.xsxlq.market.pojo.EmailInfo;

import java.util.List;

public interface EmailInfoService {

    List<EmailInfo> selectALLSelective(EmailInfo emailInfo);

    int insertSelective(EmailInfo record);

    int deleteByPrimaryKey(Integer id);
}
