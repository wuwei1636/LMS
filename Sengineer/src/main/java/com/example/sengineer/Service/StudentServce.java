package com.example.sengineer.Service;

import com.example.sengineer.pojo.conformity.Course_all;
import com.example.sengineer.pojo.conformity.Course_grade;
import com.example.sengineer.pojo.Student;
import org.apache.ibatis.annotations.Param;

import java.time.Year;
import java.util.List;

public interface StudentServce {

    // 获取所有学生的信息
    List<Student> stulist() ;
    // 根据stu_id 获取学生的信息
    Student stuCen(String stu_id);
    // 登录验证
    String getStuPwdBystuId(String stu_id);
    //修改信息
    boolean updatePerInfo(Student student);
    // 获取学生的成绩
    List<Course_grade> getGrade(String stu_id,String year , Integer semester);
    // 获取学生的课程
    List<Course_all> getScore(@Param("stu_id") String stu_id, @Param("year") String year , @Param("semester") Integer semester);

}
