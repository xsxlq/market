package com.xsxlq.market.service.impl;

import com.xsxlq.market.dao.ShopUserMapper;
import com.xsxlq.market.pojo.ShopUser;
import com.xsxlq.market.pojo.ShopUserInfo;
import com.xsxlq.market.service.ShopUserInfoService;
import com.xsxlq.market.service.ShopUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Component("ShopUserService")
public class ShopUserServiceImpl implements ShopUserService {
    @Autowired
    private ShopUserMapper shopUserMapper;

    @Autowired
    private ShopUserInfoService shopUserInfoService;

    /**
     * 添加
     * @param shopUser
     * @return
     */
    @Override
    public int insert(ShopUser shopUser) {
        if(selectByPhone(shopUser.getPhone()) != null){
            return 2; //已存在
        }
        shopUser.setRegistertime(new Timestamp(new Date().getTime()));
        //默认状态正常
        shopUser.setStatus(1);
        //默认用户为普通用户
        shopUser.setPerms("user:user");
        //用户基本信息 添加后返回主键到对象
        shopUserMapper.insertSelective(shopUser);
        int returnRegUserId = shopUser.getId();

//        System.out.println("ShopUserServiceImp:returnRegUserId:"+returnRegUserId);
        //根据主键创建用户敏感信息
        ShopUserInfo shopUserInfo = new ShopUserInfo();
        shopUserInfo.setId(returnRegUserId);
        return shopUserInfoService.insert(shopUserInfo);
    }

    /**
     * 根据phone 查询
     * @param phone
     * @return
     */
    @Override
    public ShopUser selectByPhone(String phone) {
        return shopUserMapper.selectByPhone(phone);
    }

    @Override
    public ShopUser selectById(int id) {
        return shopUserMapper.selectByPrimaryKey(id);
    }

    /**
     * 修改
     * @param record
     * @return
     */
    @Override
    public int updateByPrimaryKeySelective(ShopUser record) {
        return shopUserMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<ShopUser> selectAll() {
        return shopUserMapper.selectAll();
    }
}
