package com.qf.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UsersController {

    @Autowired
    private SecurityManager securityManager;

    public SecurityManager getSecurityManager() {
        return securityManager;
    }

    public void setSecurityManager(SecurityManager securityManager) {
        this.securityManager = securityManager;
    }

    @RequestMapping("loginPage")
    public String loginPage(){
        return "login";
    }
    //wangzhen3cm
    @RequestMapping("login")
    public String login(String username,String password){
        SecurityUtils.setSecurityManager(securityManager);
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject();
        try{
            subject.login(usernamePasswordToken);
            if(subject.isAuthenticated()){
                //shiro中的session和httpSession通用
                Session session = subject.getSession();
                return "redirect:index";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:loginPage";
    }

    @RequestMapping("unauthor")
    public String unauthor(){
        return "unauthorized";
    }
}
