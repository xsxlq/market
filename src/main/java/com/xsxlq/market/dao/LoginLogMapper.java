package com.xsxlq.market.dao;

import com.xsxlq.market.pojo.LoginLog;

import java.util.List;

public interface LoginLogMapper {
    int insert(LoginLog record);

    int insertSelective(LoginLog record);

    List<LoginLog> selectLogById(int id);
}