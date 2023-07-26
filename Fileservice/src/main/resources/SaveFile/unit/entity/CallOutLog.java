package com.tuniu.mob.ocsfaq.faq.entity;

import java.util.Date;

public class CallOutLog {

    private Integer id;

    private String workNum;

    private String callParam;

    private String callResponse;

    private  Integer salerId;


    private Integer orderId;

    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    private Date addTime;

    public Integer getSalerId() {
        return salerId;
    }

    public void setSalerId(Integer salerId) {
        this.salerId = salerId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWorkNum() {
        return workNum;
    }

    public void setWorkNum(String workNum) {
        this.workNum = workNum == null ? null : workNum.trim();
    }

    public String getCallParam() {
        return callParam;
    }

    public void setCallParam(String callParam) {
        this.callParam = callParam == null ? null : callParam.trim();
    }

    public String getCallResponse() {
        return callResponse;
    }

    public void setCallResponse(String callResponse) {
        this.callResponse = callResponse == null ? null : callResponse.trim();
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", workNum=").append(workNum);
        sb.append(", callParam=").append(callParam);
        sb.append(", callResponse=").append(callResponse);
        sb.append(", orderId=").append(orderId);
        sb.append(", addTime=").append(addTime);
        sb.append("]");
        return sb.toString();
    }

}
