package com.qf.controller;

import com.qf.pojo.Holiday;
import com.qf.pojo.User;
import com.qf.service.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HolidayController {
    @Autowired
    private HolidayService holidayService;

    public HolidayService getHolidayService() {
        return holidayService;
    }

    public void setHolidayService(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    @RequestMapping("add")
    public String add(){
        return "holiday";
    }
    @RequestMapping("saveHoliday")
    public String saveHoliday(Holiday holiday, HttpSession session){
        User user = new User();
        user.setUid(39);
        user.setUname("xiaoming");
        holiday.setUser(user);
        holidayService.addHoliday(holiday);
        return "redirect:index";
    }

    /**
     * 查看我审批的假条列表
     * @return
     */
    @RequestMapping("getHolidays")
    public String getHolidays(HttpServletRequest request){
        List<Holiday> holidayList = holidayService.getApproveHolidayList("boss");
        for (Holiday holiday : holidayList){
            System.out.println(holiday);
        }
        request.setAttribute("holidayList",holidayList);
        return "manager_holiday";
    }

    @RequestMapping("updateHoliday")
    public String updateHoliday(int hid){
        //chris字符串到真正开发时对应的内容是当前登录账户的用户名
        holidayService.updateHoliday(hid,1,"boss");
        return "redirect:getHolidays";
    }
}
