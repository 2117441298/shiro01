package com.zking.shiro;

import com.zking.model.User;
import com.zking.service.IUserServrice;
import javafx.beans.property.SimpleStringProperty;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class MyRealm extends AuthorizingRealm {
    @Autowired
    private IUserServrice userServrice;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = principalCollection.getPrimaryPrincipal().toString();
        //获取用户的权限和角色
        Set<String> role = userServrice.getRole(username);
        Set<String> permission = userServrice.getPermission(username);
        //给用户设置
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        info.setRoles(role);
        info.setStringPermissions(permission);
        //权限认证
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获得输入账号和密码
        String username = authenticationToken.getPrincipal().toString();
        String password = authenticationToken.getCredentials().toString();
        //身份验证
        User user = userServrice.login(username);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
                user.getUsername(), user.getPassword(), ByteSource.Util.bytes(user.getSalt()), this.getName()
        );
        return info;
    }
}
