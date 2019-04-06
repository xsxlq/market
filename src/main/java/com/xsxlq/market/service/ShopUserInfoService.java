package com.xsxlq.market.service;

import com.xsxlq.market.pojo.ShopUserInfo;

import java.util.List;

public interface ShopUserInfoService {
    ShopUserInfo selectById(int id);

    boolean updateByPrimaryKeySelective(ShopUserInfo record);

    int insert(ShopUserInfo shopUserInfo);

    List<ShopUserInfo> selectALLSelective(ShopUserInfo userInfo);

}
