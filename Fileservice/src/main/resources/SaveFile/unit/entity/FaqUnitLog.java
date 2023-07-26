package com.tuniu.mob.ocsfaq.faq.entity;

import java.util.Date;

public class FaqUnitLog {

    private Integer id;

    /**
     * 用户问题
     *
     * @mbg.generated
     */
    private String userQuestion;

    /**
     * 对话接口响应动作
     *
     * @mbg.generated
     */
    private String unitActionName;

    /**
     * 对话接口响应
     *
     * @mbg.generated
     */
    private String unitResponse;

    /**
     * 用户id
     *
     * @mbg.generated
     */
    private String userId;

    /**
     * 请求时间
     *
     * @mbg.generated
     */
    private Date addTime;

    /**
     * 0 app侧用户对话 1 用户侧点击问题 2 客服侧对话 3 客服侧点击问题
     */
    private Integer sourceType;

    /**
     * 供应商id
     */

    private Integer supplierId;

    /**
     * 订单id
     */
    private Integer orderId;


    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserQuestion() {
        return userQuestion;
    }

    public void setUserQuestion(String userQuestion) {
        this.userQuestion = userQuestion == null ? null : userQuestion.trim();
    }

    public String getUnitActionName() {
        return unitActionName;
    }

    public void setUnitActionName(String unitActionName) {
        this.unitActionName = unitActionName == null ? null : unitActionName.trim();
    }

    public String getUnitResponse() {
        return unitResponse;
    }

    public void setUnitResponse(String unitResponse) {
        this.unitResponse = unitResponse == null ? null : unitResponse.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
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
        sb.append(", userQuestion=").append(userQuestion);
        sb.append(", unitActionName=").append(unitActionName);
        sb.append(", unitResponse=").append(unitResponse);
        sb.append(", userId=").append(userId);
        sb.append(", addTime=").append(addTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}
