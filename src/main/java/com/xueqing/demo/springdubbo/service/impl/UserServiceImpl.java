package com.xueqing.demo.springdubbo.service.impl;

import com.xueqing.demo.springdubbo.dao.RoleDao;
import com.xueqing.demo.springdubbo.dao.UserDao;
import com.xueqing.demo.springdubbo.entity.Module;
import com.xueqing.demo.springdubbo.entity.Role;
import com.xueqing.demo.springdubbo.entity.User;
import com.xueqing.demo.springdubbo.service.UserService;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl  implements UserService {

    @Autowired
    UserDao userDao;


    @Autowired
    private CacheManager cacheManager;
    /**
     * 缓存的key
     */
    public static final String CACHE_KEY   = "cache";
    @Override
    @Cacheable(value = CACHE_KEY ,key = "'users'")
    public List<User> getUsers() {
        List<User> users = userDao.getUsers();
//        List<User> query = jdbcTemplate.query("select * from user", new BeanPropertyRowMapper<User>(User.class));
        return users;
    }

    @Override
    @Cacheable(value = CACHE_KEY, key = "#id")
    public User findUserById(String id) {
        return userDao.findUserById(id);
    }

    @CacheEvict(value = CACHE_KEY, key = "#id")
    @Override
    public void deleteUserById(String id) {
        userDao.deleteUserById(id);
    }




    @Override
    public  Map<Object,Element>  getCache(){
        System.out.println("GETCACHE");
        Cache cache = cacheManager.getCache(CACHE_KEY);
        Map<Object,Element> elements = cache.getAll(cache.getKeys());
        if(elements!=null){
            for (Map.Entry<Object, Element> entry : elements.entrySet()) {
                System.out.println("key= " + entry.getValue().getObjectKey());
                System.out.println("value= " + entry.getValue().getObjectValue());
            }
        }

        return elements;
    }


    @Override
    public List<Module> findByUserName(String username) {
        return userDao.findByUserName(username);
    }

    @Override
    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }



    @Autowired
    JdbcTemplate jdbcTemplate;

    class UserRowMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            User user = new User();
            user.setPassword(password);
            user.setUsername(username);
            return user;
        }
    }
}
