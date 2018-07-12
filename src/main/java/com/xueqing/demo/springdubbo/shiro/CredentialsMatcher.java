package com.xueqing.demo.springdubbo.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * Created by imooc on 2018/2/24.
 */
public class CredentialsMatcher extends SimpleCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        char[] cs = (char[]) token.getCredentials();
        String pwInput = new String(cs);

        String pwSQL = (String) info.getCredentials();
        return equals(pwInput, pwSQL);
    }
}
