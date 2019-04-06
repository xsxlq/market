package com.xsxlq.market.service.impl;

import com.xsxlq.market.dao.ShopUserInfoMapper;
import com.xsxlq.market.pojo.ShopUserInfo;
import com.xsxlq.market.service.ShopUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("ShopUserInfoService")
public class ShopUserInfoServiceImpl implements ShopUserInfoService {

    @Autowired
    private ShopUserInfoMapper shopUserInfoMapper;
    @Override
    public ShopUserInfo selectById(int id) {
        return shopUserInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean updateByPrimaryKeySelective(ShopUserInfo record) {
        return shopUserInfoMapper.updateByPrimaryKeySelective(record) > 0;
    }

    @Override
    public int insert(ShopUserInfo shopUserInfo) {
        return shopUserInfoMapper.insert(shopUserInfo);
    }

    @Override
    public List<ShopUserInfo> selectALLSelective(ShopUserInfo userInfo) {
        return shopUserInfoMapper.selectALLSelective(userInfo);
    }
}
