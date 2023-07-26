package com.tuniu.mob.ocsfaq.faq.unit.dto.faq;

import com.tuniu.mob.ocsfaq.faq.unit.dto.PageDto;

public class FaqSkillModelListDto extends PageDto {


    private Long skillId;


    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }

    public boolean valid() {

        if (skillId == null || skillId <= 0) {
            return false;
        }
        return true;
    }
}
