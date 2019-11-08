package com.qf.mapper;

import com.qf.pojo.Holiday;

import java.util.List;

public interface HolidayMapper {
    /**
     * 发起请假
     * @param holiday
     * @return
     */
    public int addHoliday(Holiday holiday);

    /**
     * 查询待审批的假条列表
     * 参数list表示待审批的假条id集合
     * @param list
     * @return
     */
    public List<Holiday> getApproveHolidayList(List<String> list);

    /**
     * 更改假条状态
     * @param state
     * @return
     */
    public int updateHoliday(int hid,int state);
}
