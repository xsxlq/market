package com.xsxlq.market.controller;

import com.xsxlq.market.pojo.ShopUser;
import com.xsxlq.market.service.ShopUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 注册控制类
 */
@RestController
public class RegisiterController {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ShopUserService shopUserService;

    /**
     * 检查验证码，后续完成邮箱或号码验证码的下发与验证
     * @return
     */
    @PostMapping("checkCode")
    @ResponseBody
    public ShopUser checkCode(ShopUser shopUser){
        // 0_code_error,
        // 1_ok,
        // 2_phone_exist
        ShopUser user = shopUserService.selectByPhone(shopUser.getPhone());
        if(user != null){
            //排除已注销
            if(user.getStatus() != 0){
                shopUser.setCode("2"); // phone_exist
            }
         }else if(true){//验证码，后续完成
            shopUser.setCode("1");
        }
        return shopUser; //ok
    }

    /**
     * 填写密码后登录并添加用户信息
     * @return
     */
    @PostMapping("insertUser")
    @ResponseBody
    public int inserUser(ShopUser shopUser){
        logger.debug(shopUser.toString());
        return shopUserService.insert(shopUser); //1_ok.0_error.2_exist
    }
}
