package com.tuniu.mob.ocsfaq.faq.unit.dto;

public class PageDto {

    private Integer pageNo;

    private Integer pageSize;

    public PageDto() {
    }

    public PageDto(Integer pageNo, Integer pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
