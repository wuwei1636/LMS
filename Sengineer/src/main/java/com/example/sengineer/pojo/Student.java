package com.example.sengineer.pojo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("学生实体类")
public class Student {

    int id;
    String stu_id;
    String name;
    String password;
    //身份证
    String id_card;
    //班级
    String classes;
    //院系
    String faculty;
    // 专业
    String major;
    String email;
    String phone;
    String year_age;

}
