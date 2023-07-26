package com.tuniu.mob.ocsfaq.faq.unit.dto.faq;

import java.util.List;

public class FaqSkillTagListDto {


    private Long skillId;


    private List<Long> faqIds;

    private Integer pageNo;

    private Integer pageSize;

    public FaqSkillTagListDto() {
    }

    public FaqSkillTagListDto(Long skillId, Integer pageNo, Integer pageSize) {
        this.skillId = skillId;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public FaqSkillTagListDto(Long skillId, List<Long> faqIds, Integer pageNo, Integer pageSize) {
        this.skillId = skillId;
        this.faqIds = faqIds;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }

    public List<Long> getFaqIds() {
        return faqIds;
    }

    public void setFaqIds(List<Long> faqIds) {
        this.faqIds = faqIds;
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

    public boolean valid() {

        if (skillId == null || skillId <= 0) {
            return false;
        }
        if (pageNo == null || pageNo <= 0 || pageSize == null || pageSize <= 0) {
            return false;
        }

        return true;
    }
}
