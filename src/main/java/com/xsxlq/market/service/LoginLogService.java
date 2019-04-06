package com.xsxlq.market.service;

import com.xsxlq.market.pojo.LoginLog;

import java.util.List;

public interface LoginLogService {
    /**
     * 根据id查询所有登录历史时间
     * @param id
     * @return
     */
    List<LoginLog> selectLogById(int id);

    /**
     * 添加记录
     * @param record
     * @return
     */
    int insertSelective(LoginLog record);
}
