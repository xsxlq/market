package com.xsxlq.market.util;

import com.xsxlq.market.controller.UploadController;
import com.xsxlq.market.pojo.MyError;
import com.xsxlq.market.service.MyErrorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

@Component
public class ExceptionUtil {
    @Autowired
    private MyErrorService myErrorService;
    private Logger logger = LoggerFactory.getLogger(UploadController.class);

    /**
     * 异常信息入表
     * @param ex
     * @return
     */
    public  Map putException2Table(Exception ex){
        Map map = new HashMap();
        map.put("code", 100);
        map.put("msg", ex.getMessage());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream pout = new PrintStream(out);
        ex.printStackTrace(pout);
        String ret = new String(out.toByteArray());
        pout.close();
        try {
            out.close();
        } catch (Exception e) {
        }
        MyError myError = new MyError();
        //信息截取部分，防止大数据入表失败
        if(ret.length() > 19999){
            myError.setContent(ret.substring(0,19999));
        }else{
            myError.setContent(ret);
        }
        myErrorService.insertSelective(myError);
        return map;
    }
}
