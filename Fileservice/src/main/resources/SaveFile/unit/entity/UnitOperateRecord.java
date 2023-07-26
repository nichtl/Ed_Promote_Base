package com.tuniu.mob.ocsfaq.faq.entity;

import java.util.Date;

public class UnitOperateRecord {
    private Integer id;

    /**
     * 相关操作类型  0默认  1 导入 2 faq菜单修改绑定迁移问题
     *
     * @mbg.generated
     */
    private Integer operateType;

    /**
     * 操作名称 type=1 导入文件名称type=2 一二级菜单名称-技能名称
     *
     * @mbg.generated
     */
    private String operateName;

    /**
     * 操作状态 0 初始状态 未开始 1进行中 2 操作成功结束  3 操作异常结束
     *
     * @mbg.generated
     */
    private Integer operateStatus;

    /**
     * 记录相关操作参数 可不填
     *
     * @mbg.generated
     */
    private String operateData;

    /**
     * 记录相关操作结果 可不填
     *
     * @mbg.generated
     */
    private String operateResult;

    /**
     * 操作发起人姓名
     *
     * @mbg.generated
     */
    private String addUserName;

    /**
     * 发起操作人 id
     *
     * @mbg.generated
     */
    private Integer addUserId;

    private Date addTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOperateType() {
        return operateType;
    }

    public void setOperateType(Integer operateType) {
        this.operateType = operateType;
    }

    public String getOperateName() {
        return operateName;
    }

    public void setOperateName(String operateName) {
        this.operateName = operateName == null ? null : operateName.trim();
    }

    public Integer getOperateStatus() {
        return operateStatus;
    }

    public void setOperateStatus(Integer operateStatus) {
        this.operateStatus = operateStatus;
    }

    public String getOperateData() {
        return operateData;
    }

    public void setOperateData(String operateData) {
        this.operateData = operateData == null ? null : operateData.trim();
    }

    public String getOperateResult() {
        return operateResult;
    }

    public void setOperateResult(String operateResult) {
        this.operateResult = operateResult == null ? null : operateResult.trim();
    }

    public String getAddUserName() {
        return addUserName;
    }

    public void setAddUserName(String addUserName) {
        this.addUserName = addUserName == null ? null : addUserName.trim();
    }

    public Integer getAddUserId() {
        return addUserId;
    }

    public void setAddUserId(Integer addUserId) {
        this.addUserId = addUserId;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", operateType=").append(operateType);
        sb.append(", operateName=").append(operateName);
        sb.append(", operateStatus=").append(operateStatus);
        sb.append(", operateData=").append(operateData);
        sb.append(", operateResult=").append(operateResult);
        sb.append(", addUserName=").append(addUserName);
        sb.append(", addUserId=").append(addUserId);
        sb.append(", addTime=").append(addTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}
