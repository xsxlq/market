package com.xsxlq.market.controller;

import com.xsxlq.market.pojo.Users;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TestController {

    @RequestMapping("hello")
    public String hello(Model model){
        List<String> list = new ArrayList<>();
        list.add("123");
        list.add("456");
        list.add("789");
        model.addAttribute("hello",list);
        return "hello";
    }

}
