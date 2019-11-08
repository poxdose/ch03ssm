package com.qf.service.impl;

import com.qf.mapper.HolidayMapper;
import com.qf.pojo.Holiday;
import com.qf.service.HolidayService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class HolidayServiceImpl implements HolidayService {

    @Autowired
    private HolidayMapper holidayMapper;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;

    public RuntimeService getRuntimeService() {
        return runtimeService;
    }

    public void setRuntimeService(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    public TaskService getTaskService() {
        return taskService;
    }

    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    public HolidayMapper getHolidayMapper() {
        return holidayMapper;
    }

    public void setHolidayMapper(HolidayMapper holidayMapper) {
        this.holidayMapper = holidayMapper;
    }

    /**
     * 发起请假
     * @param holiday
     * @return
     */
    @Override
    public int addHoliday(Holiday holiday) {
        holidayMapper.addHoliday(holiday);
        /**
         * 启动流程
         * startProcessInstanceByKey(String,String,map)
         * 参数说明：
         * 第一个string表示流程的key
         * 第二个string表示业务的key，即bussiness key，往往业务表的主键值
         * 第3个map表示流程变量
         */
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("stuName",holiday.getUser().getUname());
        map.put("teacherName","chris");
        map.put("managerName","manager");
        map.put("bossName","boss");
        int days = getDays(holiday.getStartDate(),holiday.getEndDate());
        map.put("days",days);
        //发起流程实例
        runtimeService.startProcessInstanceByKey("holiday",holiday.getHid()+"",map);
        //完成任务
        Task task = taskService.createTaskQuery().taskAssignee(holiday.getUser().getUname()).singleResult();
        taskService.complete(task.getId());
        return holiday.getHid();
    }

    /**
     * 根据日期获取天数
     * @param startDate
     * @param endDate
     * @return
     */
    private int getDays(String startDate, String endDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date start = null;
        Date end = null;
        try {
            start = simpleDateFormat.parse(startDate);
            end = simpleDateFormat.parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long daysTime = end.getTime() - start.getTime();
        int days = (int)daysTime/(24*60*60*1000);
        return days;
    }

    @Override
    public List<Holiday> getApproveHolidayList(String uname) {
        //待办任务集合
        List<Task> list = taskService.createTaskQuery().taskAssignee(uname).list();
        List<String> bussinessKeys = new ArrayList<String>();
        //循环遍历获取bussiness keys,即请假条的id
        for (Task task : list){
            ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                    .processInstanceId(task.getProcessInstanceId()).singleResult();
            bussinessKeys.add(processInstance.getBusinessKey());
        }
        List<Holiday> approveHolidayList = new ArrayList<>();
        if(bussinessKeys.size() != 0){
            approveHolidayList = holidayMapper.getApproveHolidayList(bussinessKeys);
        }
        return approveHolidayList;
    }

    @Override
    public int updateHoliday(int hid, int state,String uname) {
        //完成任务
        Task task = taskService.createTaskQuery().processInstanceBusinessKey(hid + "").taskAssignee(uname).singleResult();
        taskService.complete(task.getId());
        //审核通过
        return holidayMapper.updateHoliday(hid,state);
    }
}
