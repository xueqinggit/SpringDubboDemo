package com.xueqing.demo.springdubbo.shiro;

import com.xueqing.demo.springdubbo.entity.Module;
import com.xueqing.demo.springdubbo.entity.User;
import com.xueqing.demo.springdubbo.service.UserService;
import com.xueqing.demo.springdubbo.utils.ShiroUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by imooc on 2018/2/24.
 */

public class UserRealm extends AuthorizingRealm {
    @Resource
    private UserService userService;
    /**
     * 授权(验证权限时使用)
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        User user = ShiroUtils.getUserEntity();
        List<Module> modules = userService.findByUserName(user.getUsername());
        Set<String> set = new HashSet<>();
        for(Module m:modules){
            set.add(m.getMname());
        }
        info.setStringPermissions(set);

        return info;
    }

    /**
     * 认证(登录时调用)
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //得到用户名
        String username = (String) authenticationToken.getPrincipal();

        //通过username 查找user
        User user = userService.getUserByUsername(username);

        //getName 获取名称  第二个为数据库密码
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), getName());

        //之后会调用密码比对器进行密码校验  可以直接在这里写  也可以自定义密码比对器  需继承SimpleCredentialsMatcher
        return info;
    }
}
