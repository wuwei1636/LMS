package com.example.sengineer.Controller;

import com.example.sengineer.Service.AdminService;
import com.example.sengineer.Service.StudentServce;
import com.example.sengineer.Service.TeacherService;
import com.example.sengineer.pojo.Student;
import com.example.sengineer.pojo.Teacher;
import com.example.sengineer.pojo.course;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Api(tags = "管理员的controller类")
@RestController
public class AdminController {

    @Autowired
    private StudentServce studentServce;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private AdminService adminService;

    @ApiOperation("根据条件获取教师的信息")
    @PostMapping("/getTeaBy")
    public List<Teacher> getAllTea(String teacher_id , String name , String faculty , String professional_title){
        List<Teacher> teaByFac = adminService.getTeaByFac(teacher_id, name, faculty, professional_title);
        return teaByFac;
    }

    @ApiOperation("新增教师")
    @PostMapping("/insertTea")
    public String insertTea(String teacher_id, String name,String password, String id_card,
                            String faculty,String professional_title, String email,
                            String phone){
        String Msg = "";
        Teacher teacher = new Teacher();
        teacher.setId(1);
        teacher.setTeacher_id(teacher_id);
        teacher.setName(name);
        teacher.setPassword(password);
        teacher.setId_card(id_card);
        teacher.setFaculty(faculty);
        teacher.setProfessional_title(professional_title);
        teacher.setEmail(email);
        teacher.setPhone(phone);

        boolean b = adminService.InsertTea(teacher);
        if(b == false){
            Msg = "添加失败，您输入的工号已存在";
        }else {
            Msg = "添加成功";
        }
        return Msg;
    }

    @ApiOperation("删除教师")
    @PostMapping("/delTea")
    public String delTea(String teacher_id){
        String Msg = "";
        boolean b = adminService.delTea(teacher_id);
        if(b){
            Msg = "删除成功";
        }else {
            Msg = "删除失败";
        }
        System.out.println(Msg);
        return Msg;

    }

    @ApiOperation("根据条件获取学生的信息")
    @PostMapping("/getStuBy")
    public List<Student> getAllStu(String stu_id, String name,String year_age, String faculty){
        List<Student> stuByFac = adminService.getStuByFac(stu_id, name, year_age, faculty);
        return stuByFac;
    }

    @ApiOperation("新增学生")
    @PostMapping("/insertStu")
    public String insertStu(String stu_id, String name,String password, String id_card,
                            String classes, String faculty,String major, String email,
                            String phone, String year_age){
        Student student = new Student();
        student.setId(1);
        student.setStu_id(stu_id);
        student.setName(name);
        student.setPassword(password);
        student.setId_card(id_card);
        student.setClasses(classes);
        student.setFaculty(faculty);
        student.setMajor(major);
        student.setEmail(email);
        student.setPhone(phone);
        student.setYear_age(year_age);
        String Msg = "";
        boolean b = adminService.InsertStu(student);
        if(b == false){
            Msg = "添加失败,您输入的学号已存在";
        }else {
            Msg = "添加成功";
        }
        return Msg;
    }


    @ApiOperation("删除学生")
    @PostMapping("/delStu")
    public String delStu(String stu_id){
        String Msg = "";
        boolean b = adminService.delStu(stu_id);
        if(b){
            Msg = "删除成功";
        }else {
            Msg = "删除失败";
        }
        System.out.println(Msg);
        return Msg;
    }


    @ApiOperation("新增课程")
    @PostMapping("/insertCour")
    public String insertCour(
            int course_id, String course_name,String teacher_id, String teacher_name,
            double credit_hour, int credit,String year, int semester,
            String classroom,String session_time, boolean isrequired, boolean specialized, String assessment_method
    ){
        course cr = new course();
        cr.setId(1);
        cr.setCourse_id(course_id);
        cr.setCourse_name(course_name);
        cr.setTeacher_id(teacher_id);
        cr.setTeacher_name(teacher_name);
        cr.setStu_num(0);
        cr.setCredit_hour(credit_hour);
        cr.setCredit(credit);
        cr.setYear(year);
        cr.setSemester(semester);
        cr.setClassroom(classroom);
        cr.setSession_time(session_time);
        cr.setIsrequired(isrequired);
        cr.setSpecialized(specialized);
        cr.setAssessment_method(assessment_method);


        String Msg = "";
        boolean b = adminService.InsertCourse(cr);
        if(b == false){
            Msg = "添加失败,您输入的课程号已存在";
        }else {
            Msg = "添加成功";
        }
        return Msg;


    }


    @ApiOperation("删除课程")
    @PostMapping("/delCour")
    public String delCour(int course_id){
        String Msg = "";
        boolean b = adminService.delCourse(course_id);
        if(b){
            Msg = "删除成功";
        }else {
            Msg = "删除失败";
        }
        System.out.println(Msg);
        return Msg;
    }

}
