package com.xsxlq.market.shiro;

import com.xsxlq.market.pojo.LoginLog;
import com.xsxlq.market.pojo.ShopUser;
import com.xsxlq.market.service.LoginLogService;
import com.xsxlq.market.service.ShopUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 自定义 Realm
 */
public class UserRealm extends AuthorizingRealm {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ShopUserService shopUserService;

    @Autowired
    private LoginLogService loginLogService;


    /**
     * 执行授权逻辑
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        System.out.println("执行授权...");
        logger.info("执行授权...");
        //给资源进行授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //添加资源的授权字符串
//        info.addStringPermission("user:admin");

        //获取当前登录用户
        Subject subject = SecurityUtils.getSubject();
        ShopUser user = (ShopUser) subject.getPrincipal();

        info.addStringPermission(user.getPerms());
        return info;
    }

    /**
     * 执行认证逻辑
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        System.out.println("执行认证...");
        logger.info("执行认证...");
        //编写Shiro判断逻辑，判断用户名和密码
        //1.判断用户名
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //获取用户对象，用于添加登录时间日志
        ShopUser user = shopUserService.selectByPhone(token.getUsername());
        if(user == null){
            // 用户名不存在
            return null;
        }else if(user.getStatus()!= 1){
            //用户非正常状态
            logger.info("用户状态："+user.getStatus());
            throw new LockedAccountException();
        }else if(token.getUsername().equals(user.getPhone())){
            //记录登录时间
            LoginLog loginLog = new LoginLog();
            loginLog.setId(user.getId());
            loginLog.setLoginLogTime(new Timestamp(new Date().getTime()));
            loginLogService.insertSelective(loginLog);
        }
        return new SimpleAuthenticationInfo(user,user.getPwd(),"");
    }
}
