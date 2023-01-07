package com.example.sengineer.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.example.sengineer.config.shiroUtils.CustomModularRealmAuthenticator;
import com.example.sengineer.config.shiroUtils.RoleOrFilter;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.*;

@Configuration
public class ShiroConfig {

    // ShiroFilterFactoryBean  shiro过滤的对象:3
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        // 设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);

        // 配置 自定义 过滤器，用来同时进行多个授权
        RoleOrFilter roleOrFilter = new RoleOrFilter();
        Map<String, Filter> myFilterMap = new HashMap<>();

        myFilterMap.put("e-perms", roleOrFilter);//可以配置RoleOrFilter的Bean
        bean.setFilters(myFilterMap);

        // 添加shiro的内置过滤器
        /*
            anon: 无需认证就能访问
            authc：必须认证了才能访问
            user： 必须拥有”记住我功能“  才能使用
            perms： 拥有对某个资源的权限才能访问
            role： 拥有某个角色权限才能访问
         */

        // 拦截
        Map<String, String> filterMap = new LinkedHashMap<>();

        // 授权,正常情况下，没有授权会跳转到未授权界面
        //filterMap.put("/user/add","e-perms[student|admin]");
        //filterMap.put("/user/update","e-perms[teacher|admin]");
        //filterMap.put("/admin","e-perms[admin]");

//        filterMap.put("/**","authc");
        filterMap.put("/stuLogin","anon");
        filterMap.put("/teacherLogin","anon");
        filterMap.put("/adminLogin","anon");
        filterMap.put("/static/*","anon");
        //filterMap.put("/swagger-ui/*","anon");
        filterMap.put("/Admin/*","authc");
        filterMap.put("/Teacher/*","authc");
        filterMap.put("/Student/*","authc");

        filterMap.put("/logout","logout");




        bean.setFilterChainDefinitionMap(filterMap);


        // 设置登录的请求
        bean.setLoginUrl("/toLogin");
        // 未授权界面
        bean.setUnauthorizedUrl("/noauth");



        return bean;
    }


    // DefaultWebSecurityManager:2
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 关联 UserRealm,通过spring来进行关联，如上面的参数 @Qualifier("userRealm") ==》 下面的方法名
        securityManager.setAuthenticator(authenticator());
        List<Realm> realms = new ArrayList<>();
        realms.add(studentRealm());
        realms.add(adminRealm());
        realms.add(teacherRealm());

        securityManager.setRealms(realms);


        return securityManager;
    }


    // 创建 realm 对象，需要自定义类:1
    @Bean
    public StudentRealm studentRealm(){
        StudentRealm studentRealm = new StudentRealm();
        studentRealm.setCredentialsMatcher(getHashedCredentialsMatcher());
        return studentRealm;
    }

    @Bean
    public TeacherRealm teacherRealm(){
        TeacherRealm teacherRealm = new TeacherRealm();
        teacherRealm.setCredentialsMatcher(getHashedCredentialsMatcher());
        return teacherRealm;
    }

    @Bean
    public AdminRealm adminRealm(){
        AdminRealm adminRealm = new AdminRealm();
        adminRealm.setCredentialsMatcher(getHashedCredentialsMatcher());
        return adminRealm;
    }


    // 自定义密码加密
    @Bean(name = "matcher")
    public HashedCredentialsMatcher getHashedCredentialsMatcher(){
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        // 设置加密规则
        matcher.setHashAlgorithmName("md5");
        // 设置加密次数
        matcher.setHashIterations(10);

        return matcher;
    }

    //下面就是认证器的配置
    @Bean
    public CustomModularRealmAuthenticator authenticator(){
        CustomModularRealmAuthenticator authenticator = new CustomModularRealmAuthenticator();
        authenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
        return authenticator;
    }


    // 整合 ShiroDialect：用来整合shiro+thymeleaf
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }


}
