package com.example.sengineer.config.shiroUtils;

public enum LoginType {

    STUDENT("Student"), ADMIN("Admin"),TEACHER("Teacher");

    private String type;    //定义的是登陆的类型

    private LoginType(String type){
        this.type=type;
    }


    public String toString() {
        return this.type.toString();
    }

}
