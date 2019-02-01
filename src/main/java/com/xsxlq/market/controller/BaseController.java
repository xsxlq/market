package com.xsxlq.market.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BaseController {
    /**
     * 测试页面跳转
     */
    @RequestMapping("/test")
    public String test(Model model){
        return "test";
    }
    /**
     * 默认首页
     * @param model
     * @return
     */
    @RequestMapping("/")
    public String index(Model model){
        return "market/index";
    }
    /**
     * 普通跳转
     */
    @RequestMapping("{page}.html")
    public String Page(@PathVariable String page){
        System.out.println("普通调整："+page);
        return "market/"+page;
    }
    @RequestMapping("{url}/{page}.html")
    public String Page(@PathVariable String url, @PathVariable String page){
        System.out.println("普通调整："+page);
        return "market/"+url+"/"+page;
    }
    @RequestMapping("{root}/{url}/{page}.html")
    public String Page(@PathVariable String root,@PathVariable String url, @PathVariable String page){
        System.out.println("普通调整："+page);
        return "market/"+root+"/"+url+"/"+page;
    }

}
