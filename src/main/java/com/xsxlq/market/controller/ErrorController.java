package com.xsxlq.market.controller;

import com.xsxlq.market.util.ExceptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@ControllerAdvice
public class ErrorController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ExceptionUtil exceptionUtil;

    /**
     * 全局异常捕获
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Map errorHandler(Exception ex) {
        logger.info("全局捕获异常start...");
        Map map = exceptionUtil.putException2Table(ex);
        logger.error(ex.getMessage());
        ex.printStackTrace();
        logger.info("全局捕获异常end...");
        return map;
    }
}
