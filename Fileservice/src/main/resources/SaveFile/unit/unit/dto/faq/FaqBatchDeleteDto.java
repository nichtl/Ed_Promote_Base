package com.tuniu.mob.ocsfaq.faq.unit.dto.faq;

import org.apache.commons.collections.CollectionUtils;

import java.util.List;

public class FaqBatchDeleteDto {

    private Long skillId;


    private List<Long> faqIds;

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

    public boolean valid() {
        if(skillId == null || CollectionUtils.isEmpty(faqIds)){
            return false;
        }
        return true;
    }
}
