package com.xsxlq.market.shiro;
import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shiro 配置类
 */
@Configuration
public class ShiroConfig {
    /**
     * 创建ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //添加Shiro内置过滤器
        /**
         * 常用的过滤器
         * anon :  无需认证
         * authc:  必须认证
         * user :  如果使用rememberMe的功能可以直接访问
         * perms:  该资源必须得到资源权限才可以访问
         * role :  该资源必须得到角色权限才可以访问
         */
        Map<String,String> filterMap = new LinkedHashMap<String,String>();
        //授权过滤器
        //未授权拦截后，会自动跳转指定页面
//        filterMap.put("/page/mall_page.html","perms[user:admin]");
        //白名单:
        filterMap.put("/index.html","anon");
        filterMap.put("/page/login_page.html","anon");
        filterMap.put("/page/suggest.html","anon");
        filterMap.put("/page/about.html","anon");
        //管理员登录页面
        filterMap.put("/x-admin","anon");
        filterMap.put("/x-admin/login.html","anon");
        filterMap.put("/x-admin/x-login","anon");
        //拦截页面：
        filterMap.put("/page/*","authc");
        filterMap.put("/x-admin/*","perms[user:admin]");
        //登出
        filterMap.put("/logout","logout");

        //修改拦截后跳转的页面
        shiroFilterFactoryBean.setLoginUrl("/page/login_page.html");
        //设置未授权拦截后，跳转的页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/unAut.html");


        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);


        return shiroFilterFactoryBean;
    }

    /**
     * 创建DefaultWebSecurityManager
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联Realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    /**
     * 创建Realm
     */
    @Bean(name = "userRealm")
    public UserRealm getRealm(){
        return new UserRealm();
    }

    /**
     * 配置ShiroDialect. 用于thymeleaf和Shiro标签配合使用
     */
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
}
