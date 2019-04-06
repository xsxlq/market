package com.xsxlq.market.dao;

import com.xsxlq.market.pojo.ShopUserInfo;

import java.util.List;

public interface ShopUserInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ShopUserInfo record);

    int insertSelective(ShopUserInfo record);

    ShopUserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ShopUserInfo record);

    int updateByPrimaryKey(ShopUserInfo record);

    List<ShopUserInfo> selectALLSelective(ShopUserInfo userInfo);



}