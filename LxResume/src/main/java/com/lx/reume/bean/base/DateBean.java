package com.lx.reume.bean.base;

import java.util.Date;

public class DateBean extends BaseBean{
    public Date getBeginDay() {
        return beginDay;
    }

    public void setBeginDay(Date beginDay) {
        this.beginDay = beginDay;
    }

    public Date getEndDay() {
        return endDay;
    }

    public void setEndDay(Date endDay) {
        this.endDay = endDay;
    }

    private Date beginDay;
    private Date endDay;


}
