package com.tuniu.mob.ocsfaq.faq.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class FaqStatisticsDto extends PageEntity {

   private  Integer startYear;

   private  Integer endYear;


   private  Integer startMonth;

   private  Integer endMonth;


   private  Integer startDay;

   private  Integer endDay;

   private  Integer matchCount;

    public Integer getMatchCount() {
        return matchCount;
    }

    public void setMatchCount(Integer matchCount) {
        this.matchCount = matchCount;
    }

    public Integer getStartYear() {
        return startYear;
    }

    public void setStartYear(Integer startYear) {
        this.startYear = startYear;
    }

    public Integer getEndYear() {
        return endYear;
    }

    public void setEndYear(Integer endYear) {
        this.endYear = endYear;
    }

    public Integer getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(Integer startMonth) {
        this.startMonth = startMonth;
    }

    public Integer getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(Integer endMonth) {
        this.endMonth = endMonth;
    }

    public Integer getStartDay() {
        return startDay;
    }

    public void setStartDay(Integer startDay) {
        this.startDay = startDay;
    }

    public Integer getEndDay() {
        return endDay;
    }

    public void setEndDay(Integer endDay) {
        this.endDay = endDay;
    }
}
