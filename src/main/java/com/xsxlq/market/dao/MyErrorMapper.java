package com.xsxlq.market.dao;

import com.xsxlq.market.pojo.MyError;

import java.util.List;

public interface MyErrorMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MyError record);

    int insertSelective(MyError record);

    MyError selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MyError record);

    int updateByPrimaryKey(MyError record);

    List<MyError> selectAllSelective(MyError myError);
}