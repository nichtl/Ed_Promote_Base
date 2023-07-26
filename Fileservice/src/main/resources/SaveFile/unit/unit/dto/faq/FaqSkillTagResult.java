package com.tuniu.mob.ocsfaq.faq.unit.dto.faq;

import java.util.List;

public class FaqSkillTagResult {
    private Integer totalCount;


    private Integer currentPage;

    private List<FaqSkillTag> tags;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public List<FaqSkillTag> getTags() {
        return tags;
    }

    public void setTags(List<FaqSkillTag> tags) {
        this.tags = tags;
    }
}
