package com.example.sengineer.Service.impl;

import com.example.sengineer.Mapper.TeacherMapper;
import com.example.sengineer.Mapper.XkMapper;
import com.example.sengineer.Service.TeacherService;
import com.example.sengineer.config.shiroUtils.MD5;
import com.example.sengineer.pojo.Allcourse;
import com.example.sengineer.pojo.conformity.Stu_grade;
import com.example.sengineer.pojo.course;
import com.example.sengineer.pojo.Teacher;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private XkMapper xkMapper;

    @Override
    public List<Teacher> teacherList() {
        return teacherMapper.teacherList();
    }

    @Override
    public Teacher teacherCen(String teacher_id) {
        return teacherMapper.teacherCen(teacher_id);
    }

    @Override
    public String getTeaPwdById(String teacher_id) {
        return teacherMapper.getTeaPwdById(teacher_id);
    }

    @Override
    public boolean updateTPerInfo(Teacher teacher) {
        Teacher teacher1 = teacherMapper.teacherCen(teacher.getTeacher_id());

        // 如果这三个有一个是空的，则 默认是之前的
        if(teacher.getTeacher_id() == null) teacher.setTeacher_id(teacher1.getTeacher_id());
        if(teacher.getName() == null) teacher.setName(teacher1.getName());
        if(teacher.getFaculty() == null) teacher.setFaculty(teacher1.getFaculty());
        if(teacher.getProfessional_title() == null) teacher.setProfessional_title(teacher1.getProfessional_title());
        if(teacher.getId_card() == null) teacher.setPassword(teacher1.getPassword());
        if(teacher.getEmail() == null) teacher.setEmail(teacher1.getEmail());
        if(teacher.getPhone() == null) teacher.setPhone(teacher1.getPhone());

        return teacherMapper.updateTPerInfo(teacher);
    }


    @Override
    public List<course> getAllTeach(String teacher_id,String year ,Integer semester) {
        return teacherMapper.getAllTeach(teacher_id , year , semester);
    }

    @Override
    public List<Stu_grade> getStuGraCour(int course_id, String stu_id, String name, String year_age, String faculty, String major) {
        if(stu_id != null && stu_id != ""){
            List<Stu_grade> stuGraCour = teacherMapper.getStuGraCour(course_id, null, name, year_age, faculty, major);
            List<Stu_grade> stu_gradeList = new ArrayList<>();
            int sum = 0;
            for (Stu_grade stu_grade : stuGraCour) {
                String geStuid = stu_grade.getStu_id();
                if(geStuid.equals(stu_id)){
                    stu_gradeList.add(sum ++ , stu_grade);
                }
            }
            return stu_gradeList;
        }else{
            List<Stu_grade> stuGraCour = teacherMapper.getStuGraCour(course_id, stu_id, name, year_age, faculty, major);
            return stuGraCour;
        }

    }

    @Override
    public Allcourse getStuGraBytwo(int course_id, String stu_id) {
        Allcourse gradeByTwo = xkMapper.getGradeByTwo(course_id, stu_id);
        return gradeByTwo;
    }

    @Override
    public boolean updateGrade(@Param("course_id") int course_id , @Param("stu_id")  String stu_id , @Param("grade") double grade) {
        return teacherMapper.updateGrade(course_id,stu_id,grade);
    }
}
