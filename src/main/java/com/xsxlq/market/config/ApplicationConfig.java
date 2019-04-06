package com.xsxlq.market.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//https://blog.csdn.net/change_on/article/details/59529034
/**
 * 配置虚拟路径
 */
@Configuration
public class ApplicationConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /**
         * 配置server虚拟路径，handler为前台访问的目录，locations为files相对应的本地路径
         */
        registry.addResourceHandler("/upload/img/**").addResourceLocations("file:F:\\upload\\image\\");
        registry.addResourceHandler("/upload/video/**").addResourceLocations("file:G:/upload/video/");

        //阿里云(映射路径去除盘符)
        //registry.addResourceHandler("/ueditor/image/**").addResourceLocations("/upload/image/");
        //registry.addResourceHandler("/ueditor/video/**").addResourceLocations("/upload/video/");
        super.addResourceHandlers(registry);
    }
}