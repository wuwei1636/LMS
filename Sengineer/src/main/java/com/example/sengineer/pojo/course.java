package com.example.sengineer.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Year;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class course {

    int id;
    //课程id
    int course_id;
    // 课程名字
    String course_name;
    //老师的id
    String teacher_id;
    // 老师名字
    String teacher_name;
    // 学生人数
    int stu_num;
    // 课时
    double credit_hour;
    // 学分
    int credit;
    // 上课年份
    String year;
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
    // 考核方式
    String assessment_method;

    public boolean isIsrequired() {
        return isrequired;
    }

    public void setIsrequired(boolean isrequired) {
        this.isrequired = isrequired;
    }

    public boolean isSpecialized() {
        return specialized;
    }

    public void setSpecialized(boolean specialized) {
        this.specialized = specialized;
    }
}
