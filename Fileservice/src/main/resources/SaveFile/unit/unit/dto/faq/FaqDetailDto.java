package com.tuniu.mob.ocsfaq.faq.unit.dto.faq;

public class FaqDetailDto {

    private Long skillId;

    private Long faqId;

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }

    public Long getFaqId() {
        return faqId;
    }

    public void setFaqId(Long faqId) {
        this.faqId = faqId;
    }

    public boolean valid() {
        if (this.getSkillId() == null || this.getSkillId() <= 0) {
            return false;
        }
        if (this.getFaqId() == null || this.getFaqId() <= 0) {
            return false;
        }

        return true;
    }
}
