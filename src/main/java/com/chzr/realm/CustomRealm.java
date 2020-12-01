package com.chzr.realm;

import com.chzr.config.JWTToken;
import com.chzr.config.JWTUtil;
import com.chzr.entity.YPermissionEntity;
import com.chzr.entity.YRoleEntity;
import com.chzr.entity.YUserEntity;
import com.chzr.service.YPermissionService;
import com.chzr.service.YRoleService;
import com.chzr.service.YUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CustomRealm extends AuthorizingRealm {

    @Autowired
    YUserService userService;
    @Autowired
    YRoleService roleService;
    @Autowired
    YPermissionService permissionService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = JWTUtil.getUsername(principalCollection.toString());
        //查询用户名称
        YUserEntity user = userService.getUserByName(username);

        // YUserEntity user = (YUserEntity) principalCollection.getPrimaryPrincipal();
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        YRoleEntity role = roleService.getById(user.getRoleid());

        List<YPermissionEntity> perms = permissionService.getPermsByRoleId(user.getRoleid());

        if(role != null) {
            simpleAuthorizationInfo.addRole(role.getName());
        }
        if(perms != null) {
            for (YPermissionEntity p : perms) {
                simpleAuthorizationInfo.addStringPermission(p.getPerm());
            }
        }
        return simpleAuthorizationInfo;
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getCredentials();
        // 解密获得username，用于和数据库进行对比
        String username = JWTUtil.getUsername(token);
        if (username == null) {
            throw new AuthenticationException("token invalid");
        }

        YUserEntity userBean = userService.getUserByName(username);
        if (userBean == null) {
            throw new AuthenticationException("User didn't existed!");
        }

        if (! JWTUtil.verify(token, username, userBean.getPassword())) {
            throw new AuthenticationException("Username or password error");
        }

        return new SimpleAuthenticationInfo(token, token, getName());
    }
}
