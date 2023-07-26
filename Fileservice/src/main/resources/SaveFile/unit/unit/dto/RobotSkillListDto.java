package com.tuniu.mob.ocsfaq.faq.unit.dto;

import cn.hutool.core.util.StrUtil;
import com.tuniu.mob.ocsfaq.faq.unit.utils.UnitUtils;

public class RobotSkillListDto extends PageDto {


    private String serviceId;

    public RobotSkillListDto() {
    }

    public RobotSkillListDto(Integer pageNo, Integer pageSize, String serviceId) {
        super(pageNo, pageSize);
        this.serviceId = serviceId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public boolean valid() {
        UnitUtils.checkPageDto(this);
        if (this.getServiceId() == null || StrUtil.isBlank(this.getServiceId())) {
            return false;
        }
        return true;
    }
}
