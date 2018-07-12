package com.xueqing.demo.springdubbo.dao;

import com.xueqing.demo.springdubbo.entity.Module;
import com.xueqing.demo.springdubbo.entity.User;

import java.util.List;

public interface UserDao {
    List<User> getUsers();

    User findUserById(String id);

    void deleteUserById(String id);

    List<Module> findByUserName(String username);

    User getUserByUsername(String username);
}
