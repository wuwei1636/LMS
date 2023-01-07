package com.example.sengineer.Service;

import com.example.sengineer.pojo.Admin;
import com.example.sengineer.pojo.course;
import com.example.sengineer.pojo.Student;
import com.example.sengineer.pojo.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminService {
    // 登录验证
    String getAPwdByName(String name);

    Admin queryAdminByName(String name);


    /**
     *  管理员对学生部分的操作
     * **/

    // 查找不同院系的学生
    List<Student> getStuByFac(String stu_id, String name,String year_age, String faculty);
    // 添加学生
    boolean InsertStu(Student student);
    // 修改学生信息
    boolean updateStuPInfo(Student student);
    // 根据学生id 删除学生的信息
    boolean delStu(String stu_id);


    /**
     *  管理员对教师部分的操作
     * **/

    // 查找不同院系的学生
    List<Teacher> getTeaByFac(String teacher_id , String name , String faculty , String professional_title);
    // 添加学生
    boolean InsertTea(Teacher teacher);
    // 修改学生信息
    boolean updateTeaPInfo(Teacher teacher);
    // 根据学生id 删除学生的信息
    boolean delTea(String teacher_id);




    /**
     *  管理员对课程部分的操作
     * **/


    // 查看所有课程
    List<course> getAllC();
    // 添加课程
    boolean InsertCourse(course course);
    // 删除课程
    boolean delCourse(int course_id);
    // 修改课程信息
    boolean updateCourse(course course);
}
