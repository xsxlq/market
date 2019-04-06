package com.xsxlq.market.controller.admin;

import com.xsxlq.market.pojo.ShopUser;
import com.xsxlq.market.pojo.ShopUserInfo;
import com.xsxlq.market.service.ShopUserInfoService;
import com.xsxlq.market.service.ShopUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminUserController {
    @Autowired
    private ShopUserService shopUserService;

    @Autowired
    private ShopUserInfoService shopUserInfoService;

    Logger logger = LoggerFactory.getLogger(getClass());
    @RequestMapping("/x-admin/user.json")
    @ResponseBody
    public Map<String,Object> User(String page, String limit, String phone){
        int pageNum = Integer.valueOf(page);
        int limitNum = Integer.valueOf(limit);
        Map<String,Object> returnMap = new HashMap<>();
        returnMap.put("code",0);
        returnMap.put("msg","ok");
        List<ShopUser> userList = null;
        if(phone == null || "".equals(phone)){
            //查询全部
            userList = shopUserService.selectAll();
        }else{
            //条件查询
            userList = new ArrayList<>();
            ShopUser shopUser= shopUserService.selectByPhone(phone);
            if(shopUser != null){
                userList.add(shopUser);
            }
        }
        List<ShopUser> userListPage = new ArrayList<>();
        if(userList.size() == 0){
            returnMap.put("code",1);
            returnMap.put("msg","未查询到数据-->条件: [ phone="+phone+" ]");
            returnMap.put("count",0);
        }else{
            returnMap.put("count",userList.size());
        }
        int i = 1;
        for (ShopUser s: userList
        ) {
            if(i < (pageNum * limitNum) && i>(pageNum - 1)*limitNum){
                userListPage.add(s);
            }
            i++;
        }
        returnMap.put("data",userListPage);
        return returnMap;
    }

    @RequestMapping("/x-admin/userinfo.json")
    @ResponseBody
    public Map<String,Object> userInfo(String page, String limit, ShopUserInfo userInfo){
        int pageNum = Integer.valueOf(page);
        int limitNum = Integer.valueOf(limit);
        Map<String,Object> returnMap = new HashMap<>();
        returnMap.put("code",0);
        returnMap.put("msg","ok");
        List<ShopUserInfo> userList = shopUserInfoService.selectALLSelective(userInfo);
        List<ShopUserInfo> userListPage = new ArrayList<>();
        if(userList.size() == 0){
            returnMap.put("code",1);
            returnMap.put("msg","未查询到数据");
            returnMap.put("count",0);
        }else{
            returnMap.put("count",userList.size());
        }
        int i = 1;
        for (ShopUserInfo s: userList
        ) {
            if(i < (pageNum * limitNum) && i>(pageNum - 1)*limitNum){
                userListPage.add(s);
            }
            i++;
        }
        returnMap.put("data",userListPage);
        return returnMap;
    }

    @RequestMapping("/x-admin/updateUser")
    @ResponseBody
    public Map<String,String> updateUser(String id,String status,String perms) {
        Map<String, String> returnMap = new HashMap<>();
        String resCode = "0";
        String resDesc = "updateByPrimaryKeySelective(shopUser)---->0";
        try {
            ShopUser shopUser = new ShopUser();
            shopUser.setId(Integer.valueOf(id));
            shopUser.setStatus(Integer.valueOf(status));
            shopUser.setPerms(perms);
            if (shopUserService.updateByPrimaryKeySelective(shopUser) == 1) {
                resCode = "1";
                resDesc = "ok";
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            resCode = "0";
            resDesc = e.getMessage();
        }
        returnMap.put("resCode", resCode);
        returnMap.put("resDesc", resDesc);
        return returnMap;
    }
}
