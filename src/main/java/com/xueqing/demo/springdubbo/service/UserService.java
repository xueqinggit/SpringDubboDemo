package com.xueqing.demo.springdubbo.service;

import com.xueqing.demo.springdubbo.entity.Module;
import com.xueqing.demo.springdubbo.entity.User;
import net.sf.ehcache.Element;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


public interface UserService {
    List<User> getUsers();

    User findUserById(String id);

    void deleteUserById(String id);

    Map<Object,Element> getCache();


    List<Module> findByUserName(String username);

    User getUserByUsername(String username);

}
