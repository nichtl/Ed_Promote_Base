package com.tuniu.mob.ocsfaq.faq.unit.dto.faq;

import org.springframework.util.CollectionUtils;

import java.util.List;

public class FaqSkillTagBatchDeleteDto {
    private Long skillId;

    private List<Long> tagIds;

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }

    public List<Long> getTagIds() {
        return tagIds;
    }

    public void setTagIds(List<Long> tagIds) {
        this.tagIds = tagIds;
    }

    public boolean valid() {

        if (skillId == null || skillId <= 0) {
            return false;
        }
        if (CollectionUtils.isEmpty(tagIds)) {
            return false;
        }
        return true;
    }
}
