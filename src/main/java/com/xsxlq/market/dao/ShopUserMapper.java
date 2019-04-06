package com.xsxlq.market.dao;

import com.xsxlq.market.pojo.ShopUser;

import java.util.List;

public interface ShopUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ShopUser record);

    int insertSelective(ShopUser record);

    ShopUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ShopUser record);

    int updateByPrimaryKey(ShopUser record);

    ShopUser selectByPhone(String phone);

    List<ShopUser> selectAll();
}