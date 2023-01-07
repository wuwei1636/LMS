package com.example.sengineer.Service;


import com.example.sengineer.pojo.Allcourse;
import com.example.sengineer.pojo.conformity.Course_all;
import com.example.sengineer.pojo.course;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

public interface XkService {

    // 获取所有课程
    List<course> courses();
    //通过 course_id 来获取课程信息
    List<course> getCourseByMore(int course_id,String course_name,String teacher_name);

    // 判断学生是否选择了这门课
    boolean judgeIsChosed(int course_id, String stu_id);
    // 根据course_id 来获取所有选这门课的学生
    List<Allcourse>  getAllcourseById(int course_id);

    //通过  是否是专业课 ， 课程属性（必修/选修） ， 课程名字 进行检索
    List<Course_all> getCourseByThree(@Param("specialized") Integer specialized , @Param("isrequired") Integer isrequired , @Param("course_name") String course_name);

    //选课
    String addStuCour(int course_id, String stu_id);
    //退选
    String delStuCour(int course_id, String stu_id);

    //    更新每门课的选课人数
//    boolean updateStuNum(@Param("course_id")int course_id,@Param("stu_num")int stu_num);

    // 删除所有这门课的学生信息
    boolean delByCourseId(int course_id);
    //     删除这个学生的所有课程信息
    boolean delByStuId(String stu_id);

    // 根据课程id获取课程的人数
    int CourStuSum(int course_id);

}
