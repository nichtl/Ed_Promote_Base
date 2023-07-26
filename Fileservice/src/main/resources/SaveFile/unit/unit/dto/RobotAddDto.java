package com.tuniu.mob.ocsfaq.faq.unit.dto;

import cn.hutool.core.util.StrUtil;

public class RobotAddDto {
    private String serviceName;

    private String serviceDesc;
    /**
     * 机器器⼈人类型：common(通⽤用机器器⼈人)、marketingClue(营销线索机器器⼈人)；默认为common
     */
    private String serviceCategory;
    /**
     * 机器器⼈人类别：common(通⽤用机器器⼈人)包括：dialogueDistribution（对话分发）、
     * taskflow;marketingClue包括：general(通⽤用场景)、edu（教育场景）、date（相亲场景）
     * ；默认为dialogueDistribution
     */
    private String serviceType;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceDesc() {
        return serviceDesc;
    }

    public void setServiceDesc(String serviceDesc) {
        this.serviceDesc = serviceDesc;
    }

    public String getServiceCategory() {
        return serviceCategory;
    }

    public void setServiceCategory(String serviceCategory) {
        this.serviceCategory = serviceCategory;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public boolean valid() {

        if (StrUtil.isBlank(this.getServiceName())) {
            return false;
        }

        return true;
    }


}
