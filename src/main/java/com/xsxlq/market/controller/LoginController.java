package com.xsxlq.market.controller;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户登录控制类
 */
@Controller
public class LoginController {
    Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 登录逻辑处理
     */
    @PostMapping("/login")
    @ResponseBody
    public Map<String,String> login(String phone, String password) {
        Map<String,String> returnMap = new HashMap<>();
        returnMap.put("respCode","1");
        returnMap.put("respDesc","OK！");
        /**
         * 使用Shiro编写认证操作
         */
        //1.获取Subject
        Subject subject = SecurityUtils.getSubject();
        //2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(phone, password);
        //3.执行登录方法
        try {
            subject.login(token);
            logger.info("login 控制器--登录成功！");
            return returnMap;
        } catch (UnknownAccountException e) {
            logger.info("用户名不存在");
            returnMap.put("respCode","0");
            returnMap.put("respDesc","用户名不存在!");
            return returnMap;
        }catch ( LockedAccountException lae ) {
            logger.info("账户被冻结！");
            returnMap.put("respCode","0");
            returnMap.put("respDesc","该账户已被冻结，如有疑问，请联系管理员!");
            return returnMap;
        } catch (IncorrectCredentialsException e) {
            logger.info("密码错误");
            returnMap.put("respCode","0");
            returnMap.put("respDesc","密码错误");
            return returnMap;
        }
    }

    @PostMapping("/x-admin/x-login")
    public String X_login(String phone, String password, Model model) {
        Map<String,String> respMap = new HashMap<>();
        respMap.put("respCode","1");
        respMap.put("respDesc","登录成功");
        /**
         * 使用Shiro编写认证操作
         */
        //1.获取Subject
        Subject subject = SecurityUtils.getSubject();
        //2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(phone, password);
        //3.执行登录方法
        try {
            subject.login(token);
            logger.debug("login 控制器--登录成功！");
            model.addAttribute("result",respMap);
            //登陆成功
            return "admin/x-admin/index";
        } catch (UnknownAccountException e) {
            logger.debug("用户名不存在");
            //用户名不存在
            respMap.put("respCode","0");
            respMap.put("respDesc","用户名不存在");
            model.addAttribute("result",respMap);
            return "admin/x-admin/login";
        } catch (IncorrectCredentialsException e) {
            logger.debug("密码错误");
            //密码错误
            respMap.put("respCode","0");
            respMap.put("respDesc","密码错误");
            model.addAttribute("result",respMap);
            return "admin/x-admin/login";
        }
    }
}
