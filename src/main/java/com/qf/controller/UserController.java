package com.qf.controller;

import com.qf.pojo.User;
import com.qf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("index")
    public String index(HttpServletRequest request){
        List<User> userList = userService.getUserList();
        request.setAttribute("userList",userList);
        return "index";
    }

    @RequestMapping("addUser")
    public String addUser(){
        return "add";
    }

    @RequestMapping("saveUser")
    public String saveUser(User user){
        int i = userService.addUser(user);
        if(i > 0){
            return "redirect:index";
        }
        return "redirect:addUser";
    }

    @RequestMapping("editUser")
    public String editUser(int uid,HttpServletRequest request){
        User user = userService.getUserByUid(uid);
        request.setAttribute("user",user);
        return "edit";
    }

    @RequestMapping("updateUser")
    public String updateUser(User user){
        int i = userService.updateUser(user);
        if(i > 0){
            return "redirect:index";
        }
        return "redirect:editUser?uid="+user.getUid();
    }

    @RequestMapping("deleteUser")
    @ResponseBody
    public String deleteUser(int uid){
        int i = userService.deleteUser(uid);
        if(i > 0){
            return "success";
        }
        return "fail";
    }

}
