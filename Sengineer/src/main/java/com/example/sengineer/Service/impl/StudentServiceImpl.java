package com.example.sengineer.Service.impl;

import com.example.sengineer.Mapper.StudentMapper;
import com.example.sengineer.Service.StudentServce;
import com.example.sengineer.config.shiroUtils.MD5;
import com.example.sengineer.pojo.conformity.Course_all;
import com.example.sengineer.pojo.conformity.Course_grade;
import com.example.sengineer.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentServce {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Student> stulist() {
        return studentMapper.stulist();
    }

    @Override
    public Student stuCen(String stu_id) {
        return studentMapper.stuCen(stu_id);
    }

    @Override
    public String getStuPwdBystuId(String stu_id) {

        return studentMapper.getStuPwdBystuId(stu_id);
    }

    @Override
    public boolean updatePerInfo(Student student) {

        Student stu1 = studentMapper.stuCen(student.getStu_id());
       // 默认只能修改 身份证号 ，邮箱 和 手机号 ，如果这三个中有一个是空的，则 默认是之前的
        if(student.getStu_id() == null) student.setStu_id(stu1.getStu_id());
        if(student.getName() == null) student.setName(stu1.getName());
        if(student.getFaculty() == null) student.setFaculty(stu1.getFaculty());
        if(student.getMajor() == null) student.setMajor(stu1.getMajor());
        if(student.getClasses() == null) student.setClasses(stu1.getClasses());
        if(student.getId_card() == null) student.setId_card(stu1.getId_card());
       if(student.getEmail() == null) student.setEmail(stu1.getEmail());
       if(student.getPhone() == null) student.setPhone(stu1.getPhone());

        return studentMapper.updatePerInfo(student);
    }


    @Override
    public List<Course_grade> getGrade(String stu_id, String year, Integer semester) {
        List<Course_grade> grade = studentMapper.getGrade(stu_id,year,semester);
        return grade;
    }

    @Override
    public List<Course_all> getScore(String stu_id, String year, Integer semester) {
        List<Course_all> score = studentMapper.getScore(stu_id,year,semester);
        return score;
    }


}
