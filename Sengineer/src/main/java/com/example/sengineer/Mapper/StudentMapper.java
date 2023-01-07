package com.example.sengineer.Mapper;

import com.example.sengineer.pojo.conformity.Course_all;
import com.example.sengineer.pojo.conformity.Course_grade;
import com.example.sengineer.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentMapper {

    //获取所有学生的所有信息
    List<Student> stulist();
    //根据stu_id 获取学生的信息
    Student stuCen(String stu_id);
    //登录验证
    String getStuPwdBystuId(String stu_id);
    //学生修改信息
    boolean updatePerInfo(Student student);
    //获得学生成绩信息
    List<Course_grade> getGrade(@Param("stu_id") String stu_id,@Param("year") String year ,@Param("semester") Integer semester);
    //获取学生的课程信息

    List<Course_all> getScore(@Param("stu_id") String stu_id,@Param("year") String year ,@Param("semester") Integer semester);


}
