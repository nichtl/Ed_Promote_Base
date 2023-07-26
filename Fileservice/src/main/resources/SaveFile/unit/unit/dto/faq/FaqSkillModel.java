package com.tuniu.mob.ocsfaq.faq.unit.dto.faq;

import java.util.List;

public class FaqSkillModel {

    private Integer modelId;


    private String modelVersion;


    private String modelDesc;


    private String status;


    private List<String> sandboxResourceIds;


    private List<String> onlineResourceIds;


    private String createTime;


    private String updateTime;


    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public String getModelVersion() {
        return modelVersion;
    }

    public void setModelVersion(String modelVersion) {
        this.modelVersion = modelVersion;
    }

    public String getModelDesc() {
        return modelDesc;
    }

    public void setModelDesc(String modelDesc) {
        this.modelDesc = modelDesc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getSandboxResourceIds() {
        return sandboxResourceIds;
    }

    public void setSandboxResourceIds(List<String> sandboxResourceIds) {
        this.sandboxResourceIds = sandboxResourceIds;
    }

    public List<String> getOnlineResourceIds() {
        return onlineResourceIds;
    }

    public void setOnlineResourceIds(List<String> onlineResourceIds) {
        this.onlineResourceIds = onlineResourceIds;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
