package com.example.sengineer.pojo.conformity;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("学生的成绩信息")
public class Course_grade {

    //课程id
    int course_id;
    // 成绩
    double grade;
    // 课程名字
    String course_name;
    // 课时
    double credit_hour;
    // 学分
    int credit;
    // 上课日期
    Year year;
    // 上课的学期
    int semester;
    // 是否必修
    boolean isrequired;
    // 是否专业课
    boolean specialized;
    // 考核方式
    String assessment_method;

}
