package com.example.sengineer.Service;

import com.example.sengineer.pojo.Allcourse;
import com.example.sengineer.pojo.conformity.Stu_grade;
import com.example.sengineer.pojo.course;
import com.example.sengineer.pojo.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherService {

    //获取所有老师的信息
    List<Teacher> teacherList();
    // 根据 教师id 获取教师的个人信息
    Teacher teacherCen(String teacher_id);
    // 进行登录验证
    String getTeaPwdById(String teacher_id);
    // 教师修改个人信息
    boolean updateTPerInfo(Teacher teacher);

    //根据教师名字 寻找该教师所 负责教的课程( 后续可能改成 根据 老师 id 来寻找)
    List<course> getAllTeach(@Param("teacher_id") String teacher_id,@Param("year") String year,@Param("semester") Integer semester);

    // 获取学生的信息和这门课的成绩
    List<Stu_grade> getStuGraCour(@Param("course_id") int course_id , @Param("stu_id") String stu_id, @Param("name") String name,
                                  @Param("year_age") String year_age, @Param("faculty") String faculty, @Param("major") String major);

    // 根据课程和id获取成绩
    Allcourse getStuGraBytwo(int course_id, String stu_id);

    // 更新学生课程的成绩(算是一个公共的方法)
    boolean updateGrade(@Param("course_id") int course_id ,@Param("stu_id")  String stu_id ,@Param("grade") double grade);



}
