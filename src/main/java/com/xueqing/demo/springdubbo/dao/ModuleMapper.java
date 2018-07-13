package com.xueqing.demo.springdubbo.dao;

import com.xueqing.demo.springdubbo.entity.Module;

public interface ModuleMapper {
    int deleteByPrimaryKey(Integer mid);

    int insert(Module record);

    int insertSelective(Module record);

    Module selectByPrimaryKey(Integer mid);

    int updateByPrimaryKeySelective(Module record);

    int updateByPrimaryKey(Module record);
}