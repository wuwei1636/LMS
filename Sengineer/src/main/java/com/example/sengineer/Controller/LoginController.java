package com.example.sengineer.Controller;

import com.example.sengineer.config.shiroUtils.CustomToken;
import com.example.sengineer.config.shiroUtils.LoginType;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    private static final String LOGIN_TYPE_stu= LoginType.STUDENT.toString();
    private static final String LOGIN_TYPE_admin= LoginType.ADMIN.toString();
    private static final String LOGIN_TYPE_teacher= LoginType.TEACHER.toString();

    @RequestMapping({"/index","/"})
    public String toIndex(){
        return "login";
    }


    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/admin")
    public String admin(){
        return "admin";
    }

    @RequestMapping("/stuLogin")
     public String stuLogin(String username , String password , Model model){
        Subject subject = SecurityUtils.getSubject();
        CustomToken stuToken = new CustomToken(username,password,LOGIN_TYPE_stu);
        System.out.println(stuToken);
        try{
            subject.login(stuToken);

            return "redirect:/toStudentMsg";
        }catch (UnknownAccountException e){ // 用户名不存在
            model.addAttribute("msg","用户名错误");

            return "login";
        }catch (IncorrectCredentialsException e){ //密码不存在

            model.addAttribute("msg","密码错误");
            return "login";
        }
     }

    @RequestMapping("/teacherLogin")
    public String teacherLogin(String username , String password , Model model){
        Subject subject = SecurityUtils.getSubject();
        CustomToken teacherToken = new CustomToken(username,password,LOGIN_TYPE_teacher);
        try{
            subject.login(teacherToken);
            return "redirect:/toTeacherMsg";
        }catch (UnknownAccountException e){ // 用户名不存在
            model.addAttribute("msg1","用户名错误");
            return "login";
        }catch (IncorrectCredentialsException e){ //密码不存在
            model.addAttribute("msg1","密码错误");
            return "login";
        }
    }

    @RequestMapping("/adminLogin")
    public String adminLogin(String username , String password , Model model){
        Subject subject = SecurityUtils.getSubject();
        CustomToken adminToken = new CustomToken(username,password,LOGIN_TYPE_admin);
        try{
            subject.login(adminToken);
            return "redirect:/toAdminTeammsg";
        }catch (UnknownAccountException e){ // 用户名不存在
            model.addAttribute("msg3","用户名错误");
            return "login";
        }catch (IncorrectCredentialsException e){ //密码不存在
            model.addAttribute("msg3","密码错误");
            return "login";
        }
    }


    @RequestMapping("/noauth")
    public String Unauthorized(){
        return "404";
    }

}
