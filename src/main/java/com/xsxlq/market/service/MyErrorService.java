package com.xsxlq.market.service;

import com.xsxlq.market.pojo.MyError;

import java.util.List;

public interface MyErrorService {

    List<MyError> selectAllSeletive(MyError myError);

    int insertSelective(MyError myError);
}
