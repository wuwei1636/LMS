package com.example.sengineer.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
// 学生的成绩
public class Allcourse {

    // 课程id
    int course_id;
    // 课程学生的id
    String stu_id;
    // 成绩
    double grade;


}
