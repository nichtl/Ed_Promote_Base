package com.tuniu.mob.ocsfaq.faq.unit.dto;

import cn.hutool.core.util.StrUtil;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class RobotSkillSortDto {

    private String serviceId;
    //技能ID列列表，列列表id顺序即为排序顺序
    private List<Long> skillIds;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public List<Long> getSkillIds() {
        return skillIds;
    }

    public void setSkillIds(List<Long> skillIds) {
        this.skillIds = skillIds;
    }

    public boolean valid() {

        if (StrUtil.isBlank(this.getServiceId()) || CollectionUtils.isEmpty(skillIds)) {
            return false;
        }

        return true;
    }
}
