package com.tuniu.mob.ocsfaq.faq.unit.dto.faq;

import com.tuniu.mob.ocsfaq.faq.unit.dto.PageDto;

import java.util.List;

public class FaqSkillListDto extends PageDto {

    private Long skillId;

    private List<Long> sluTagIds;


    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }

    public List<Long> getSluTagIds() {
        return sluTagIds;
    }

    public void setSluTagIds(List<Long> sluTagIds) {
        this.sluTagIds = sluTagIds;
    }


    public boolean valid() {
        if (this.skillId == null || this.getSkillId() <= 0) {
            return false;
        }
        if (this.getPageNo() == null || this.getPageNo() <= 0) {
            return false;
        }

        if (this.getPageSize() == null || this.getPageSize() <= 0) {
            return false;
        }
        return true;


    }
}
