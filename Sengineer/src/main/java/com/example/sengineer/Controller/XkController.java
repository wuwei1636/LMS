package com.example.sengineer.Controller;

import com.example.sengineer.Service.XkService;
import com.example.sengineer.pojo.Student;
import com.example.sengineer.pojo.conformity.Course_all;
import com.example.sengineer.pojo.course;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(tags = "选课")
@RestController
public class XkController {

    @Autowired
    private XkService xkService;

    @Autowired
    HttpServletRequest request; //通过注解获取一个request

    @ApiOperation("获取所有的课程信息")
    @PostMapping("/getCourses")
    public List<course> getCourses(){
        return xkService.courses();
    }

    @ApiOperation("通过course_id获取课程信息")
    @PostMapping("/getCourseByMore")
    public List<course> getCourseByMore(int course_id, String course_name, String teacher_name){
        return xkService.getCourseByMore(course_id, course_name, teacher_name);
    }


    @ApiOperation("学生选课获取课程的信息")
    @PostMapping("/xkSearch")
    public List<Course_all> getCourseByThree(Integer specialized, Integer isrequired, String course_name){
        List<Course_all> courseByThree = xkService.getCourseByThree(specialized, isrequired, course_name);
        return courseByThree;
    }

    @ApiOperation("选课")
    @PostMapping("/course_select")
    public String selectCourse(int course_id){
        Student student = (Student) request.getSession().getAttribute("loginUser");
        String stu_id = student.getStu_id();
        String s = xkService.addStuCour(course_id, stu_id);

        return  s;
    }

    @ApiOperation("退选")
    @PostMapping("/delSelect")
    public String delCourse(int course_id ){
        Student student = (Student) request.getSession().getAttribute("loginUser");
        String stu_id = student.getStu_id();
        String s = xkService.delStuCour(course_id, stu_id);
        return s;
    }

}
