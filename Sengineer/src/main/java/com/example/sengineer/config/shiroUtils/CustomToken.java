package com.example.sengineer.config.shiroUtils;

import org.apache.shiro.authc.UsernamePasswordToken;

public class CustomToken extends UsernamePasswordToken {

    //定义登陆的类型是为了在后面的校验中 去选择使用哪一个realm
    private String loginType;

    public CustomToken(String userName,String password,String loginType){
        super(userName,password);
        this.loginType=loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getLoginType() {
        return loginType;
    }

}
