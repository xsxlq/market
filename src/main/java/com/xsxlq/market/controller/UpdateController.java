package com.xsxlq.market.controller;

import com.xsxlq.market.pojo.ShopUser;
import com.xsxlq.market.pojo.ShopUserInfo;
import com.xsxlq.market.service.LoginLogService;
import com.xsxlq.market.service.ShopUserInfoService;
import com.xsxlq.market.service.ShopUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpdateController {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ShopUserService shopUserService;

    @Autowired
    private ShopUserInfoService shopUserInfoService;

    @Autowired
    private LoginLogService loginLogService;

    /**
     * 更新用户私有信息
     * @param id
     * @param user
     * @return
     */
    @PostMapping("/updateUserPrivateInfo")
    @ResponseBody
    public int updateUserPrivateInfo(String id,ShopUserInfo user){
        logger.debug("id:"+id+"\n"+user);
        int num = 0;
        if(id != null && !id.equals("")){
            user.setId(Integer.valueOf(id));
            if(shopUserInfoService.updateByPrimaryKeySelective(user)){
                num = 1;
            }
        }
        return num;
    }

    /**
     * 更新用户基本信息
     * @param id
     * @param user
     * @return
     */
    @PostMapping("/updateUserCommInfo")
    @ResponseBody
    public int updateUserCommInfo(String id,ShopUser user){
        logger.debug("id:"+id+"\n"+user);
        int num = 0;
        if(id != null && !id.equals("")){
            user.setId(Integer.valueOf(id));
            if(shopUserService.updateByPrimaryKeySelective(user) > 0){
                num = 1;
            }
        }
        return num;
    }

    /**
     * 通过phone 返回用户对象
     * @param id
     * @return
     */
    @PostMapping("/selectUserById")
    @ResponseBody
    public ShopUser selectById(String id){
        logger.debug("id:"+id);
        if(id != null && !id.equals("")){
            //获取基本信息
            ShopUser user = shopUserService.selectById(Integer.valueOf(id));
            if(user != null){
                //格式化注册时间
                user.setStrRegTime(user.getRegistertime().toString().substring(0,19));
                //模糊密码
                user.setPwd("*****");
                //查询并添加登录时间记录
                user.setLoginLogs(loginLogService.selectLogById(user.getId()));
                return user;
            }
        }
        return null;
    }
    @PostMapping("/selectUserInfoById")
    @ResponseBody
    public ShopUserInfo selectUserInfoById(String id){
        logger.debug("updateController:id:"+id);
        if(id != null && !id.equals("")){
            ShopUserInfo userInfo = shopUserInfoService.selectById(Integer.valueOf(id));
            return userInfo;
        }
        return null;
    }
    @PostMapping("/selectUserIconById")
    @ResponseBody
    public ShopUserInfo selectUserIconById(String id){
        ShopUserInfo userInfo = selectUserInfoById(id);
        if(userInfo == null){
            return null;
        }
        ShopUser shopUser = selectById(id);
        if(shopUser == null){
            return null;
        }
        ShopUserInfo returnUserInfo = new ShopUserInfo();
        ShopUser returnShopUser = new ShopUser();

        returnShopUser.setName(shopUser.getName());

        returnUserInfo.setIcon(userInfo.getIcon());
        returnUserInfo.setShopUser(returnShopUser);
        return returnUserInfo;
    }
}
