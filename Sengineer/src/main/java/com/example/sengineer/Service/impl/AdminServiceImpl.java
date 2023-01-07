package com.example.sengineer.Service.impl;

import com.example.sengineer.Mapper.AdminMapper;
import com.example.sengineer.Mapper.StudentMapper;
import com.example.sengineer.Mapper.TeacherMapper;
import com.example.sengineer.Mapper.XkMapper;
import com.example.sengineer.Service.AdminService;
import com.example.sengineer.config.shiroUtils.MD5;
import com.example.sengineer.pojo.Admin;
import com.example.sengineer.pojo.conformity.Course_all;
import com.example.sengineer.pojo.course;
import com.example.sengineer.pojo.Student;
import com.example.sengineer.pojo.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminMapper adminMapper;

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    TeacherMapper teacherMapper;

    @Autowired
    XkMapper xkMapper;


    @Override
    public String getAPwdByName(String name) {
        return adminMapper.getAPwdByName(name);
    }

    @Override
    public Admin queryAdminByName(String name) {
        return adminMapper.queryAdminByName(name);
    }

    @Override
    public List<Student> getStuByFac(String stu_id, String name,String year_age, String faculty) {
        return adminMapper.getStuByFac(stu_id, name, year_age, faculty);
    }

    @Override
    public boolean InsertStu(Student student) {
        // 如果必要信息没有填写，那么就返回失败
        if(student.getStu_id() == null || student.getName() == null
        || student.getPassword() == null || student.getClasses() == null ||
        student.getFaculty() == null || student.getMajor() == null || student.getYear_age() == null||
        student.getStu_id().equals("") || student.getName().equals("") || student.getPassword().equals("") ||
        student.getClasses().equals("") || student.getFaculty().equals("") || student.getMajor().equals("") ||
                student.getYear_age().equals("")){
            System.out.println("信息不齐全");
            return false;
        }
        if(studentMapper.stuCen(student.getStu_id()) != null){
            return false;
        }

        String MD5pwd = MD5.md5(student.getPassword(),student.getStu_id());
        student.setPassword(MD5pwd);
        return adminMapper.InsertStu(student);
    }

    @Override
    public boolean updateStuPInfo(Student student) {
        String MD5pwd = MD5.md5(student.getPassword(),student.getStu_id());
        student.setPassword(MD5pwd);
        return adminMapper.updateStuPInfo(student);
    }

    @Override
    public boolean delStu(String stu_id) {

        for (Course_all courseAll : studentMapper.getScore(stu_id, null, null)) {
            if (xkMapper.delStuNum(courseAll.getCourse_id())) {
                System.out.println("学生数-1"+courseAll.getCourse_id());
            }else{
                System.out.println("失败"+courseAll.getCourse_id());
            }
        }

        if (!xkMapper.delByStuId(stu_id)) {
            return false;
        }

        return adminMapper.delStu(stu_id);
    }

    @Override
    public List<Teacher> getTeaByFac(String teacher_id , String name , String faculty , String professional_title) {
        return adminMapper.getTeaByFac(teacher_id, name, faculty, professional_title);
    }

    @Override
    public boolean InsertTea(Teacher teacher) {
//         如果必要信息没有填写，那么就返回失败
        if(teacher.getTeacher_id() == null || teacher.getName() == null ||
        teacher.getPassword() == null || teacher.getFaculty() == null || teacher.getProfessional_title() == null
        || teacher.getTeacher_id().equals("") || teacher.getName().equals("") || teacher.getPassword().equals("")
                || teacher.getFaculty().equals("") || teacher.getProfessional_title().equals("")){
            return false;
        }
        if(teacherMapper.teacherCen(teacher.getTeacher_id()) != null){
            return false;
        }
        String MD5pwd = MD5.md5(teacher.getPassword(),teacher.getTeacher_id());
        teacher.setPassword(MD5pwd);
        return adminMapper.InsertTea(teacher);
    }

    @Override
    public boolean updateTeaPInfo(Teacher teacher) {
        return adminMapper.updateTeaPInfo(teacher);
    }

    @Override
    public boolean delTea(String teacher_id) {
        for (course allTeach : teacherMapper.getAllTeach(teacher_id, null, null)) {
            delCourse(allTeach.getCourse_id());
        }
        return adminMapper.delTea(teacher_id);
    }

    @Override
    public List<course> getAllC() {
        return adminMapper.getAllC();
    }

    @Override
    public boolean InsertCourse(course course) {
        if( String.valueOf(course.getCourse_id()).equals("") ||
        course.getCourse_name() == null || course.getCourse_name().equals("") ||
                course.getTeacher_id() == null || course.getTeacher_id().equals("") ||
                course.getTeacher_name() == null || course.getTeacher_name().equals("") ||
                 String.valueOf(course.getCredit()).equals("") ||
                 String.valueOf(course.getCredit_hour()).equals("") ||
                course.getYear() == null || course.getYear().equals("") ||
                 String.valueOf(course.getSemester()).equals("") ||
                course.getClassroom() == null || course.getClassroom().equals("") ||
                course.getSession_time() == null || course.getSession_time().equals("")

        ){
            System.out.println("不全部");
            return  false;
        }
        if(xkMapper.getCourseById(course.getCourse_id()) != null){
            System.out.println("有相同");
            return false;
        }

        return adminMapper.InsertCourse(course);
    }

    @Override
    public boolean delCourse(int course_id) {

        if (!xkMapper.delByCourseId(course_id)) {
            return false;
        }
        return adminMapper.delCourse(course_id);
    }

    @Override
    public boolean updateCourse(course course) {
        return adminMapper.updateCourse(course);
    }
}
