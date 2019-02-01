package com.xsxlq.market.controller;

import com.xsxlq.market.dao.UsersMapper;
import com.xsxlq.market.pojo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TestController {
    @Autowired
    private UsersMapper userMapper;
    @RequestMapping("hello")
    public String hello(Model model){
        List<Users> list = new ArrayList<>();
        list = userMapper.select();
        model.addAttribute("hello",list);
        return "hello";
    }
    @RequestMapping("elementUI")
    public String elementUI(Model model){
        return "elementUI";
    }


}
