package com.example.sengineer.Service.impl;

import com.example.sengineer.Mapper.StudentMapper;
import com.example.sengineer.Mapper.XkMapper;
import com.example.sengineer.Service.XkService;
import com.example.sengineer.pojo.Allcourse;
import com.example.sengineer.pojo.conformity.Course_all;
import com.example.sengineer.pojo.conformity.Course_grade;
import com.example.sengineer.pojo.course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class XkServiceImpl implements XkService {

    @Autowired
    private XkMapper xkMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<course> courses() {
        return xkMapper.courses();
    }

    @Override
    public List<course>  getCourseByMore(int course_id,String course_name,String teacher_name) {
        return xkMapper.getCourseByMore(course_id, course_name, teacher_name);
    }

    @Override
    // 先筛选出学生所有选的课，然后根据course_id 判断这门课是不是被选择过
    public boolean judgeIsChosed(int course_id, String stu_id) {

        // 获取该课程的所有信息
        List<Course_grade> MyCourses = studentMapper.getGrade(stu_id,null,null);
        for (Course_grade myCours : MyCourses) {
            if(myCours.getCourse_id() == course_id) return true;
        }

        return false;
    }


    // 学生选课
    public String addStuCour(int course_id, String stu_id) {

        String ReMsg = "";

        // 判断这门课是不是被选过
        boolean isChoosed = judgeIsChosed(course_id,stu_id);
        // 没有被选过就添加选课
        if(isChoosed == false){
            boolean addCla = xkMapper.insertStuCla(course_id,stu_id);
            if(addCla){
                ReMsg = "选课成功";
                //人数增加一个
                xkMapper.addStuNum(course_id);
            }
            else ReMsg = "选课失败";
        }else {
            ReMsg = "你已经选过这门课";
        }

        return ReMsg;
    }

    public  String delStuCour(int course_id, String stu_id){

        String ReMsg = "";

        // 判断这门课是不是被选过

        boolean isChoosed = judgeIsChosed(course_id,stu_id);
        // 被选过就退选课
        if(isChoosed == true){
            // 获取学生的成绩信息，判断是不是正在上这门课
            Allcourse gradeByTwo = xkMapper.getGradeByTwo(course_id, stu_id);

            if(gradeByTwo.getGrade() == 0){
                boolean delCla = xkMapper.deleteStuCla(course_id,stu_id);
                if(delCla){
                    ReMsg = "退选成功";
                    //人数减少一个
                    xkMapper.delStuNum(course_id);
                }
                else ReMsg = "退选失败";
            }else {
                ReMsg = "你正在上这门课或者上完这门课，不允许退选";
            }

        }else {
            ReMsg = "你还没有选过这门课，请先选课";
        }

        return ReMsg;
    }

    @Override
    public boolean delByCourseId(int course_id) {
        return xkMapper.delByCourseId(course_id);
    }

    @Override
    public boolean delByStuId(String stu_id) {
        return xkMapper.delByStuId(stu_id);
    }

    @Override
    public int CourStuSum(int course_id) {
        return xkMapper.CourStuSum(course_id);
    }

    // 根据course_id 来获取所有选这门课的学生
    @Override
    public List<Allcourse> getAllcourseById(int course_id) {
        return xkMapper.getAllcourseById(course_id);
    }

    @Override
    public List<Course_all> getCourseByThree(Integer specialized, Integer isrequired, String course_name) {
        List<Course_all> courseByThree = xkMapper.getCourseByThree(specialized, isrequired, course_name);
        return courseByThree;
    }
}
