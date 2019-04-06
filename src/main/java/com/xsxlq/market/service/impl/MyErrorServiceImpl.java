package com.xsxlq.market.service.impl;

import com.xsxlq.market.dao.MyErrorMapper;
import com.xsxlq.market.pojo.MyError;
import com.xsxlq.market.service.MyErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("MyErrorService")
public class MyErrorServiceImpl implements MyErrorService {

    @Autowired
    private MyErrorMapper myErrorMapper;

    @Override
    public List<MyError> selectAllSeletive(MyError myError) {
        return myErrorMapper.selectAllSelective(myError);
    }

    @Override
    public int insertSelective(MyError myError) {
        return myErrorMapper.insertSelective(myError);
    }
}
