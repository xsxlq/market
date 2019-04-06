package com.xsxlq.market.service.impl;

import com.xsxlq.market.dao.LoginLogMapper;
import com.xsxlq.market.pojo.LoginLog;
import com.xsxlq.market.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component("LoginLogService")
public class LoginLogServiceImpl implements LoginLogService {
    @Autowired
    private LoginLogMapper loginLogMapper;

    @Override
    public List<LoginLog> selectLogById(int id) {

        // 返回之前为每一个时间进行字符串格式化
        List<LoginLog> formatList = new ArrayList<>();
        List<LoginLog> list = loginLogMapper.selectLogById(id);
        for (LoginLog log:list
             ) {
            LoginLog ll = new LoginLog();
            ll.setStrTime(log.getLoginLogTime().toString().substring(0,19));
            formatList.add(ll);
        }
        return formatList;
    }

    @Override
    public int insertSelective(LoginLog record) {
        return loginLogMapper.insertSelective(record);
    }
}
