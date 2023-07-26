package com.tuniu.mob.ocsfaq.faq.unit.dto.faq;

import java.util.List;

public class FaqUpdateTagsDto {

    private Long skillId;

    private List<Long> faqIds;

    private List<SluTag> sluTags;

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

    public List<SluTag> getSluTags() {
        return sluTags;
    }

    public void setSluTags(List<SluTag> sluTags) {
        this.sluTags = sluTags;
    }
}
