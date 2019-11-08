package com.qf.service;

import com.qf.pojo.Holiday;

import java.util.List;

public interface HolidayService {

    public int addHoliday(Holiday holiday);

    public List<Holiday> getApproveHolidayList(String uname);

    public int updateHoliday(int hid,int state,String uname);
}
