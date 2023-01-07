package com.example.sengineer.Controller;

import com.example.sengineer.Service.TeacherService;
import com.example.sengineer.pojo.Allcourse;
import com.example.sengineer.pojo.Teacher;
import com.example.sengineer.pojo.conformity.Stu_grade;
import com.example.sengineer.pojo.course;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(tags = "教师的controller类")
@RestController
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    HttpServletRequest request; //通过注解获取一个request

    @ApiOperation("获取所有教师的信息")
    @PostMapping("/getAllTeacher")
    public List<Teacher> getAllTeacher(){
        List<Teacher> teacherList = teacherService.teacherList();
        return teacherList;
    }

    @ApiOperation("根据teacher_id获取教师的个人信息")
    @PostMapping("/getTeaCen")
    public Teacher getTeaCen(String teacher_id){
        return teacherService.teacherCen(teacher_id);
    }



    @ApiOperation("获取老师教授的所有课程")
    @PostMapping("/getAllTeach")
    public List<course> getAllTeach(String year , Integer semester){
        Teacher loginTeacher = (Teacher) request.getSession().getAttribute("loginTeacher");
        String teacher_id = loginTeacher.getTeacher_id();
        List<course> allTeach = teacherService.getAllTeach(teacher_id,year,semester);
        return allTeach;
    }

    @ApiOperation("获取课程的学生信息和成绩")
    @PostMapping("/getStuGraCour1")
    public List<Stu_grade> getStuGraCour1(String stu_id, String name, String year_age, String faculty, String major){
        String course_ids = (String) request.getSession().getAttribute("course_id");
        int course_id = Integer.valueOf(course_ids);
        List<Stu_grade> stuGraCour = teacherService.getStuGraCour(course_id, stu_id, name, year_age, faculty, major);
        return  stuGraCour;
    }

    @ApiOperation("获取课程的学生信息和成绩")
    @PostMapping("/getStuGraCour")
    public List<Stu_grade> getStuGraCour(String stu_id, String name, String year_age, String faculty, String major){
        int course_id = (int) request.getSession().getAttribute("course_id");
        List<Stu_grade> stuGraCour = teacherService.getStuGraCour(course_id, stu_id, name, year_age, faculty, major);
        return  stuGraCour;
    }

    @ApiOperation("根据课程id和学生id获取成绩信息")
    @PostMapping("/getGraBytwo1")
    public double getGra1(){
        String course_ids = (String) request.getSession().getAttribute("course_id");
        int course_id = Integer.valueOf(course_ids);
        String stu_id = (String) request.getSession().getAttribute("stu_id");
        // 根据课程id和学生id获取成绩信息
        Allcourse stuGra = teacherService.getStuGraBytwo(course_id, stu_id);
        return stuGra.getGrade();
    }

    @ApiOperation("根据课程id和学生id获取成绩信息")
    @PostMapping("/getGraBytwo")
    public double getGra(){
        int course_id = (int) request.getSession().getAttribute("course_id");
        String stu_id = (String) request.getSession().getAttribute("stu_id");
        // 根据课程id和学生id获取成绩信息
        Allcourse stuGra = teacherService.getStuGraBytwo(course_id, stu_id);
        return stuGra.getGrade();
    }

    @ApiOperation("修改学生的成绩")
    @PostMapping("/updateGra1")
    public String updateGra1(double grade){
        String course_ids = (String) request.getSession().getAttribute("course_id");
        int course_id = Integer.valueOf(course_ids);
        String stu_id = (String) request.getSession().getAttribute("stu_id");
        boolean b = teacherService.updateGrade(course_id, stu_id, grade);
        Allcourse stuGra = teacherService.getStuGraBytwo(course_id, stu_id);
        String msg = "";
        if(b){
            msg += "修改成功,";
        }else {
            msg += "修改失败,";
        }
        msg += stuGra.getGrade()+","+course_id;
        return  msg;
    }

    @ApiOperation("修改学生的成绩")
    @PostMapping("/updateGra")
    public String updateGra(double grade){
        int course_id = (int) request.getSession().getAttribute("course_id");
        String stu_id = (String) request.getSession().getAttribute("stu_id");
        boolean b = teacherService.updateGrade(course_id, stu_id, grade);
        Allcourse stuGra = teacherService.getStuGraBytwo(course_id, stu_id);
        String msg = "";
        if(b){
            msg += "修改成功,";
        }else {
            msg += "修改失败,";
        }
        msg += stuGra.getGrade()+","+course_id;
        return  msg;
    }

}
