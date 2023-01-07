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
@ApiModel("学生课程的信息")
public class Course_all {

    //课程id
    int course_id;
    // 课程名字
    String course_name;
    // 老师名字
    String teacher_name;
    // 课时
    double credit_hour;
    // 学分
    int credit;
    // 学生人数
    int stu_num;
    // 上课日期
    Year year;
    // 上课的学期
    int semester;
    // 教室
    String classroom;
    // 上课时间
    String session_time;
    // 是否必修
    boolean isrequired;
    // 是否专业课
    boolean specialized;

}
