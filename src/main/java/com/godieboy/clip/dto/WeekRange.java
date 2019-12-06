package com.godieboy.clip.dto;

import java.util.Date;

public class WeekRange {
    private Date startDate;
    private Date endDate;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public WeekRange(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    
    
    public boolean isInRange(Date aDate){
        return aDate.compareTo(this.startDate) >=0 && aDate.compareTo(this.endDate) <= 0;
    }
    
}
