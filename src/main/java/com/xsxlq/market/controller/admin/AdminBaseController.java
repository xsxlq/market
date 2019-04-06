package com.xsxlq.market.controller.admin;

import com.xsxlq.market.pojo.ShopUser;
import com.xsxlq.market.pojo.ShopUserInfo;
import com.xsxlq.market.pojo.admin.UserTest;
import com.xsxlq.market.service.ShopUserInfoService;
import com.xsxlq.market.service.ShopUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 基础控制类--主要用于页面跳转
 */
@Controller
public class AdminBaseController {

    @RequestMapping("/x-admin")
    public String index(Model model){
        return "admin/x-admin/login";
    }
    @RequestMapping("/x-admin/login.html")
    public String index2(Model model){
        return "admin/x-admin/login";
    }
    @RequestMapping("/x-admin/{page}.html")
    public String Page(@PathVariable String page){
        return "admin/x-admin/"+page;
    }


}
