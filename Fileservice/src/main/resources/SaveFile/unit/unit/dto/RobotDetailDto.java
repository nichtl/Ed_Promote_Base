package com.tuniu.mob.ocsfaq.faq.unit.dto;

import cn.hutool.core.util.StrUtil;

public class RobotDetailDto {

    private String serviceId;

    public String getServiceId() {
        return serviceId;
    }

    public RobotDetailDto(String serviceId) {
        this.serviceId = serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public boolean valid() {

        if (StrUtil.isBlank(this.getServiceId())) {
            return false;
        }

        return true;
    }
}
