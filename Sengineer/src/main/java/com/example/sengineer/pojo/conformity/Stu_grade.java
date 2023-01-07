package com.example.sengineer.pojo.conformity;


import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("学生的个人信息和成绩")
public class Stu_grade {

    String stu_id;
    String name;
    String year_age;
    //院系
    String faculty;
    // 专业
    String major;
    // 成绩
    double grade;

}
