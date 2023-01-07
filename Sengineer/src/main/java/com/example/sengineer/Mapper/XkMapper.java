package com.example.sengineer.Mapper;

import com.example.sengineer.pojo.Allcourse;
import com.example.sengineer.pojo.conformity.Course_all;
import com.example.sengineer.pojo.course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
// 选课相关操作
public interface XkMapper {

    // 获取所有课程
    List<course> courses();
    //通过 course_id 来获取 课程信息
    course getCourseById(int course_id);
    List<course> getCourseByMore(@Param("course_id") int course_id,@Param("course_name") String course_name ,@Param("teacher_name") String teacher_name);

    // 根据course_id 来获取所有选这门课的学生
    List<Allcourse>  getAllcourseById(int course_id);

    // 通过 是否是专业课 ， 课程属性（必修/选修） ， 课程名字 进行检索
    List<Course_all> getCourseByThree(@Param("specialized") Integer specialized ,@Param("isrequired") Integer isrequired ,@Param("course_name") String course_name);

    // 根据课程号和学生学号来获取信息
    Allcourse getGradeByTwo(@Param("course_id") int course_id , @Param("stu_id") String stu_id);

    // 学生选课后增加学生课程(并不增加成绩)
    boolean insertStuCla(@Param("course_id") int course_id , @Param("stu_id") String stu_id );

    // 退选课
    boolean deleteStuCla(@Param("course_id") int course_id , @Param("stu_id") String stu_id );

    // 根据选课修改课程的学生人数
    boolean addStuNum(@Param("course_id")int course_id);

    boolean delStuNum(@Param("course_id")int course_id);


    // 删除所有这门课的学生信息
    boolean delByCourseId(@Param("course_id") int course_id);
    //     删除这个学生的所有课程信息
    boolean delByStuId(@Param("stu_id")String stu_id);

    // 根据课程id获取课程的人数
    int CourStuSum(@Param("course_id")int course_id);




}
