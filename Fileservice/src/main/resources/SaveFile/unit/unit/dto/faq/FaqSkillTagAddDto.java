package com.tuniu.mob.ocsfaq.faq.unit.dto.faq;

import com.tuniu.mob.ocsfaq.utils.StringUtils;

public class FaqSkillTagAddDto {
    private Long skillId;

    private String tagName;

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }


    public boolean valid() {

        if (skillId == null || skillId <= 0) {
            return false;
        }
        if (StringUtils.isEmpty(tagName)) {
            return false;
        }
        return true;
    }
}
