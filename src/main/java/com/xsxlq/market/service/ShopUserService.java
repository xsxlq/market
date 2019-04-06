package com.xsxlq.market.service;

import com.xsxlq.market.pojo.ShopUser;

import java.util.List;

public interface ShopUserService {
    /**
     * 添加
     * @param shopUser
     * @return
     */
    int insert(ShopUser shopUser);

    /**
     * 查询
     * @param phone
     * @return
     */
    ShopUser selectByPhone(String phone);

    /**
     * 查询
     * @param id
     * @return
     */
    ShopUser selectById(int id);
    /**
     * 修改
     */
    int updateByPrimaryKeySelective(ShopUser record);

    /**
     * 查询全部
     */
    List<ShopUser> selectAll();

}
