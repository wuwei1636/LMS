package com.example.sengineer;

import com.example.sengineer.Mapper.AdminMapper;
import com.example.sengineer.Mapper.StudentMapper;
import com.example.sengineer.Mapper.TeacherMapper;
import com.example.sengineer.Mapper.XkMapper;
import com.example.sengineer.Service.AdminService;
import com.example.sengineer.Service.StudentServce;
import com.example.sengineer.Service.TeacherService;
import com.example.sengineer.Service.XkService;
import com.example.sengineer.pojo.*;
import com.example.sengineer.pojo.conformity.Course_all;
import com.example.sengineer.pojo.conformity.Course_grade;
import com.example.sengineer.pojo.conformity.Stu_grade;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SengineerApplicationTests {
    
    @Autowired
    private XkService xkService;

    @Autowired
    private XkMapper xkMapper;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private AdminService adminService;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private StudentServce studentServce;

    @Autowired
    private StudentMapper studentMapper;


    // 测试学生是否选择课程
    @Test
    void contextLoads() {
        //System.out.println(xkService.judgeIsChosed(1001,"201203002"));

        //String s = xkService.delStuCour(1001, "201203002");
        //System.out.println(s);

        System.out.println(xkMapper.delStuNum(1001));

        // 选课搜索测试
        //System.out.println(xkMapper.getGradeByTwo(1001, "201203002"));
        //List<Course_all> courseByThree = xkMapper.getCourseByThree(1, 1, null);
        //System.out.println(courseByThree);
        //Allcourse gradeByTwo = xkMapper.getGradeByTwo(1001, "201203002");
        //boolean b = xkMapper.insertStuCla(1001, "201203002");
        //boolean s = xkMapper.deleteStuCla(1001, "201203002");
        //System.out.println(s);


    }

    @Test
    void tete(){
        System.out.println(xkMapper.CourStuSum(1001));
    }

    @Test
    void student(){
        List<Course_grade> score = studentMapper.getGrade("201203001","",null);
        for (Course_grade course_all : score) {
            System.out.println(course_all);
        }
        System.out.println("=================");
        List<Course_all> grade = studentMapper.getScore("201203001","",null);
        for (Course_all course_grade : grade) {
            System.out.println(course_grade);
        }

    }

    @Test
    void tech(){

        List<Stu_grade> getall = teacherService.getStuGraCour(1001,"201203001",
                "曹",null,"计算机与信息工程学院",null);
        System.out.println(getall);

        //List<course> teach = teacherService.getAllTeach("100201001","2022",1);
        //System.out.println(teach);
        //for (course course : teach) {
        //    // 获取所有选了这门课的学生
        //    List<Allcourse> all = xkService.getAllcourseById(course.getCourse_id());
        //    System.out.println(all);
        //    for (Allcourse allcourse : all) {
        //        boolean flag = false;
        //        // 获取课程 id
        //        int q = allcourse.getCourse_id();
        //        // 获取学生的学号
        //        String m = allcourse.getStu_id();
        //        double h = 99.0;
        //        flag = teacherService.updateGrade(q,m,h);
        //        if(flag) System.out.println("成绩修改成功");
        //        else System.out.println("成绩修改失败");
        //    }
        //}
        //System.out.println(teach);
    }

    @Test
    void Admin(){

        /**
         *
         * 学生测试
         *
         * **/

        //String apwd = adminService.getAPwdByName("admin");
        //
        //List<student> stu1 = adminService.getStuByFac("计算机科学与技术");
        //
        //
        //System.out.println(apwd);
        //System.out.println("==================================");
        //for (student student : stu1) {
        //    System.out.println(student);
        //}

//        Student stu1 = new Student(1,"2012030021","李坤松","2012030021","",
//                "2班","计算机与信息工程学院","计算机与技术","","","2020");
//        boolean b = adminService.InsertStu(stu1);
//        System.out.println(b);
        ////adminService.updateStuPInfo(stu1);
        //adminService.delStu("2012030021");

        /**
         *
         * 教师测试
         *
         * **/

//        System.out.println(adminService.getStuByFac(null, null, "2020", "计算机与信息工程学院"));

//        Teacher tea1 = new Teacher(2,"100201008","likunsong","100201007","400210570"
//                ,"计算机科术","教授","1537625@qq.com","1481636");
//        //
//        //teacher tea2 = new teacher();
//        //tea2.setName("makabaka");
//        //tea2.setTeacher_id(100201008);
//        //tea2.setPassword("100201008");
//        //tea2.setFaculty("计算机科学与技术");
//
//        boolean flag = adminService.InsertTea(tea1);
//        System.out.println(flag);

        //boolean flag = adminService.updateTeaPInfo(tea1);
        //System.out.println(flag);

        //adminService.delTea(100201008);


        /**
         * 课程测试
         * **/

        course cor = new course
                (1,10004,"汇编设计","熊立轩","1002",0,18,4,"2022",4,"2022","周五[3-5]",
                        true,true,"考察");

        System.out.println(adminService.InsertCourse(cor));
        //adminService.updateCourse(cor);
        System.out.println(adminService.delCourse(10004));
    }

    // 密码进行md5 加盐加密
    @Test
    void md(){
        //List<student> allstu = studentServce.stulist();
        //for (student student1 : allstu) {
        //    String pwd1 = md5(student1.getPassword(),student1.getStu_id());
        //    //System.out.println(pwd1);
        //    student1.setPassword(pwd1);
        //    studentServce.updatePerInfo(student1);
        //}
        //System.out.println(allstu);
        System.out.println(md5("201203001","201203001"));
        //List<teacher> alltea = teacherService.teacherList();
        ////System.out.println(alltea);
        //for (teacher tea1 : alltea) {
        //    String pwd = md5(tea1.getPassword(),tea1.getTeacher_id());
        //    tea1.setPassword(pwd);
        //    teacherService.updateTPerInfo(tea1);
        //}
    }
    public static final String md5(String password, String salt){
        //加密方式
        String hashAlgorithmName = "MD5";
        //盐：为了即使相同的密码不同的盐加密后的结果也不同
        ByteSource byteSalt = ByteSource.Util.bytes(salt);
        //密码
        Object source = password;
        //加密次数
        int hashIterations = 10;
        SimpleHash result = new SimpleHash(hashAlgorithmName, source, byteSalt, hashIterations);
        return result.toString();
    }

}
