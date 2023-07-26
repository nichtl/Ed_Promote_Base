package com.tuniu.mob.ocsfaq.faq.unit.dto;

import cn.hutool.core.util.StrUtil;

public class RobotSkillDeteleDto {

    private String serviceId;
    //技能ID列列表，列列表id顺序即为排序顺序
    private Integer skillId;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getSkillId() {
        return skillId;
    }

    public void setSkillId(Integer skillId) {
        this.skillId = skillId;
    }

    public boolean valid() {

        if (StrUtil.isBlank(this.getServiceId()) || skillId == null || skillId <= 0) {
            return false;
        }

        return true;
    }
}
