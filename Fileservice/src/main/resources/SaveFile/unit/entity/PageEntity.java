package com.tuniu.mob.ocsfaq.faq.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class PageEntity {

    private  Integer pageSize;

    private  Integer pageNum;

    private  Integer startPosition;

    @JsonIgnore
    public Integer getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(Integer startPosition) {
        this.startPosition = startPosition;
    }

    @JsonIgnore
    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @JsonIgnore
    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public   void InitPageWithDefault (int pageNum,int pageSize){
        if(pageNum<=0){pageNum=1;}
        if(pageSize<=0){pageSize=10;}
        if(this.getPageNum()==null  || this.getPageNum()==0){
            this.setPageNum(pageNum);
        }
        if(this.getPageSize() ==null || this.getPageSize()==0){
            this.setPageSize(pageSize);
        }

        this.setStartPosition((this.getPageNum()-1)*this.getPageSize());
    }
}
