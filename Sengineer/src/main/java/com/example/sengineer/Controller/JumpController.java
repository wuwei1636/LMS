package com.example.sengineer.Controller;

import com.example.sengineer.Service.StudentServce;
import com.example.sengineer.Service.TeacherService;
import com.example.sengineer.Service.XkService;
import com.example.sengineer.pojo.Student;
import com.example.sengineer.pojo.Teacher;
import com.example.sengineer.pojo.conformity.Course_all;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class JumpController {

    @Autowired
    HttpServletRequest request; //通过注解获取一个request

    @Autowired
    StudentServce studentServce;

    @Autowired
    TeacherService teacherService;

    @Autowired
    private XkService xkService;


    /**
        注销
    */
    //@RequestMapping("/logout")
    //public String logout(){
    //    return "redirect:/toLogin";
    //}


    /**
     * 学生部分的跳转
     * @Author lks
     * @return
     */


    @GetMapping("/toStudentClass")
    public String toStudentClass(){
        return "/Student/student-class";
    }

    @GetMapping("/toStudentGrade")
    public String toStudentGrade(){
        return "/Student/student-grade";
    }

    @GetMapping("/toStudentMsg")
    public String toStudentMsg(Model model){
        Student loginUser = (Student) request.getSession().getAttribute("loginUser");
        model.addAttribute("StuMsg",loginUser);
        return "/Student/student-msg";
    }


    @GetMapping("/toStudentChoose")
    public String toStudentChoose(){
        return "/Student/student-chooseclass";
    }


    @ApiOperation("修改学生的信息")
    @PostMapping("/changeStu")
    public String change(Student student, Model model){
        Student loginUser = (Student) request.getSession().getAttribute("loginUser");
        student.setStu_id(loginUser.getStu_id());
        boolean b = studentServce.updatePerInfo(student);
        String flag = "修改成功";
        if(b == false) flag = "修改失败，请稍后再试";
        // 如果修改成功就修改session中的值
        if(b){
            loginUser.setId_card(student.getId_card());
            loginUser.setEmail(student.getEmail());
            loginUser.setPhone(student.getPhone());
        }
        model.addAttribute("isChange",flag);
        return "redirect:/toStudentMsg";
    }

    /**
     * 教师部分的跳转
     */

    @GetMapping("/toTeacherCourse")
    public String toTeacherCourse(){
        return "/Teacher/teacher-course";
    }

    @RequestMapping("/toTeacherCourseView/{course_id}")
    public String toTeacherCourseView(@PathVariable("course_id") int course_id){
        // 将课程的course_id 放在 session中
        request.getSession().setAttribute("course_id",course_id);
        return "/Teacher/teacher-course-view";
        //return mv;
    }

    @GetMapping("/toTeacherCourseUpdate/{stu_id}")
    public String toTeacherCourseUpdate(@PathVariable("stu_id") String stu_id){
        request.getSession().setAttribute("stu_id",stu_id);
        return "/Teacher/teacher-course-update";
    }

    @GetMapping("/toTeacherMsg")
    public String toTeacherMSg(Model model){
        Teacher loginTeacher = (Teacher) request.getSession().getAttribute("loginTeacher");
        model.addAttribute("TeaMsg",loginTeacher);
        return "/Teacher/teacher-msg";
    }

    @PostMapping("/changeTeacher")
    public String changeTeacher(Teacher teacher){
        Teacher loginTeacher = (Teacher) request.getSession().getAttribute("loginTeacher");
        teacher.setTeacher_id(loginTeacher.getTeacher_id());
        boolean b = teacherService.updateTPerInfo(teacher);
        String flag = "修改成功";
        if(b == false) flag = "修改失败，请稍后再试";
        System.out.println(flag);
        // 如果修改成功就修改session中的值
        if(b){
            loginTeacher.setId_card(teacher.getId_card());
            loginTeacher.setEmail(teacher.getEmail());
            loginTeacher.setPhone(teacher.getPhone());
        }
        return "redirect:/toTeacherMsg";
    }


    /**
     * 管理员部分的跳转
     */

    /**
     管理员对课程的操作
     */

    @GetMapping("/toAdminCourAdd")
    public String toAdminCourAdd(){
        return "Admin/admin-courmsg-add.html";
    }

    @GetMapping("/toAdminCoursemsg")
    public String toAdminCoursemsg(){
        return "/Admin/admin-courmsg";
    }

    @GetMapping("/toAdminCoursemsgView/{course_id}")
    public String toAdminCoursemsgView(@PathVariable("course_id") String course_id){
        request.getSession().setAttribute("course_id",course_id);
        return "/Admin/admin-courmsg-view";
    }

    @GetMapping("/toAdminCourseUpdate/{stu_id}")
    public String toAdminCourseUpdate(@PathVariable("stu_id") String stu_id){
        request.getSession().setAttribute("stu_id",stu_id);
        return "/Admin/admin-course-update";
    }

    /**
     管理员对学生的操作
     */

    @GetMapping("/toAdminStuAdd")
    public String toAdminStuAdd(){
        return "/Admin/admin-stumsg-add";
    }

    @GetMapping("/toAdminStumsg")
    public String toAdminStumsg(){
        return "/Admin/admin-stumsg";
    }

    @GetMapping("/toAdminStumsgView/{stu_id}")
    public String toAdminStumsgView(@PathVariable("stu_id") String stu_id,Model model){
        request.getSession().setAttribute("stu_id",stu_id);
        Student student = studentServce.stuCen(stu_id);
        model.addAttribute("adStu",student);
        return "/Admin/admin-stumsg-view";
    }

    @PostMapping("/changeAdminStu")
    public String changeAdminStu(Student student){
        System.out.println(student);
        String stu_id = (String) request.getSession().getAttribute("stu_id");
        student.setStu_id(stu_id);
        boolean b = studentServce.updatePerInfo(student);
        String flag = "修改成功";
        if(b == false) flag = "修改失败，请稍后再试";
        System.out.println(flag);
        return "redirect:/toAdminStumsg";
    }


    /**
        管理员对教师的操作
     */


    @GetMapping("/toAdminTeaAdd")
    public String toAdminTeaAdd(){
        return "/Admin/admin-teamsg-add";
    }

    @GetMapping("/toAdminTeammsg")
    public String toAdminTeammsg(){
        return "/Admin/admin-teamsg";
    }

    @GetMapping("/toAdminTeammsgView/{teacher_id}")
    public String toAdminTeammsgView(@PathVariable("teacher_id") String teacher_id,Model model){
        request.getSession().setAttribute("teacher_id",teacher_id);
        Teacher teacher = teacherService.teacherCen(teacher_id);
        model.addAttribute("adTea",teacher);
        return "/Admin/admin-teamsg-view";
    }

    @PostMapping("/changeAdminTeacher")
    public String changeAdminTeacher(Teacher teacher){
        System.out.println(teacher);
        String teacher_id = (String) request.getSession().getAttribute("teacher_id");
        teacher.setTeacher_id(teacher_id);
        boolean b = teacherService.updateTPerInfo(teacher);
        String flag = "修改成功";
        if(b == false) flag = "修改失败，请稍后再试";
        System.out.println(flag);
        return "redirect:/toAdminTeammsg";
    }


}
