package com.example.sengineer.config;

import com.example.sengineer.Service.AdminService;
import com.example.sengineer.config.shiroUtils.CustomToken;
import com.example.sengineer.pojo.Admin;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class AdminRealm extends AuthorizingRealm {

    @Autowired
    AdminService adminService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        if(principalCollection.getPrimaryPrincipal() instanceof Admin){
            // 拿到当前登陆的这个对象
            Subject subject = SecurityUtils.getSubject();
            // 取出当前登录的对象，和下面SimpleAuthenticationInfo传的第一个关联
            //admin currentAdmin = (admin) subject.getPrincipal();
            // 设置当前用户的权限，从数据库中读出 ，所有 admin表中的用户都有 admin权限
            info.addStringPermission("admin");
            return info;
        }else return  null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        CustomToken adminToken = (CustomToken) authenticationToken;
        // 连接数据库获取登录用户的信息
        Admin admininfo = adminService.queryAdminByName(adminToken.getUsername());
        // 如果用户不存在
        if(admininfo == null){
            return null;
        }

        // 在session中设置字符，表示登录成功，目前还没有学会使用token，所以先使用session
        Subject subject1 = SecurityUtils.getSubject();
        Session session = subject1.getSession();
        session.setAttribute("loginAdmin",admininfo);

        // 设置 密码的加盐值
        ByteSource salt = ByteSource.Util.bytes(admininfo.getName());

        return new SimpleAuthenticationInfo(
                admininfo, // 当前用户 ，和上面的doGetAuthenticationInfo方法对应
                admininfo.getPassword(), // 从数据中查处的安全密码
                salt,       // 用户的密码是加盐md5 的
                getName()  // 当前 Realm 的名字
        );

    }
}
