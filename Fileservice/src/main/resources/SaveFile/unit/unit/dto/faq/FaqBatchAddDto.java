package com.tuniu.mob.ocsfaq.faq.unit.dto.faq;

import org.springframework.util.CollectionUtils;

import java.util.List;

public class FaqBatchAddDto {

    private Long skillId;

    private List<FaqBatchAddDto> faqList;

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }

    public List<FaqBatchAddDto> getFaqList() {
        return faqList;
    }

    public void setFaqList(List<FaqBatchAddDto> faqList) {
        this.faqList = faqList;
    }

    public boolean valid() {
        if (this.getSkillId() == null || this.getSkillId() <= 0) {
            return false;
        }
        if (CollectionUtils.isEmpty(this.getFaqList())) {
            return false;
        }
        return true;
    }
}
