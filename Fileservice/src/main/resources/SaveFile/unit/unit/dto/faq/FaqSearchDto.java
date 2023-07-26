package com.tuniu.mob.ocsfaq.faq.unit.dto.faq;

import com.tuniu.mob.ocsfaq.faq.unit.dto.PageDto;

import java.util.List;

public class FaqSearchDto extends PageDto {

    private Long skillId;

    private String searchKey;

    private List<Long> sluTagIds;

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public List<Long> getSluTagIds() {
        return sluTagIds;
    }

    public void setSluTagIds(List<Long> sluTagIds) {
        this.sluTagIds = sluTagIds;
    }
}
