package com.example.sengineer.Controller;

import com.example.sengineer.Service.StudentServce;
import com.example.sengineer.pojo.conformity.Course_all;
import com.example.sengineer.pojo.conformity.Course_grade;
import com.example.sengineer.pojo.Student;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.Year;
import java.util.List;

@Api(tags = "学生的controller类")
@RestController
public class StudentController {

    @Autowired
    private StudentServce studentServce;

    @Autowired
    HttpServletRequest request; //通过注解获取一个request

    @ApiOperation("获取所有学生的个人信息")
    @PostMapping("/AllStu")
    public List<Student> getAllStudent(){
        List<Student> stulist = studentServce.stulist();
        return stulist;
    }

    @ApiOperation("根据stu_id获取学生的个人信息")
    @PostMapping("/getStuCen")
    public Student getStuCen(String stu_id){
        return studentServce.stuCen(stu_id);
    }


    @ApiOperation("查询学生的成绩")
    @PostMapping("/getGrade")
    public List<Course_grade> Grade(String year, Integer semester){
        Student student = (Student) request.getSession().getAttribute("loginUser");
        String stu_id = student.getStu_id();
        List<Course_grade> grade = studentServce.getGrade(stu_id,year,semester);
        return grade;
    }

    @ApiOperation("查询学生的课程信息")
    @PostMapping("/getScore")
    public List<Course_all>  Score(String year , Integer semester){
        Student student = (Student) request.getSession().getAttribute("loginUser");
        String stu_id = student.getStu_id();
        List<Course_all> score = studentServce.getScore(stu_id, year, semester);
        return score;
    }


}
