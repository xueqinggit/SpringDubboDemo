package com.xueqing.demo.springdubbo.dao;

import com.xueqing.demo.springdubbo.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RoleDao {

    List<Role> getRoles();
}
