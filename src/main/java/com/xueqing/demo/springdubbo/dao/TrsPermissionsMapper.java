package com.xueqing.demo.springdubbo.dao;

import com.xueqing.demo.springdubbo.entity.TrsPermissions;

public interface TrsPermissionsMapper {
    int deleteByPrimaryKey(String transactionid);

    int insert(TrsPermissions record);

    int insertSelective(TrsPermissions record);

    TrsPermissions selectByPrimaryKey(String transactionid);

    int updateByPrimaryKeySelective(TrsPermissions record);

    int updateByPrimaryKey(TrsPermissions record);
}