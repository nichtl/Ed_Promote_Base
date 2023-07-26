package com.tuniu.mob.ocsfaq.faq.unit.dto;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class CustomerSupplyUnitTalkDto {



    private  String customerQuestion;

    private  Integer orderId;

    private  Integer salerId;

    private  String nickname;

    private  Integer agencyId;

    private  Integer resourceId;

    private  Integer resourceType;

    private  String question;

    private  String unitServiceId;

    private  Integer  productId;

    private  String  departureDate;

    private String workNum;


    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getWorkNum() {
        return workNum;
    }

    public void setWorkNum(String workNum) {
        this.workNum = workNum;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getUnitServiceId() {
        return unitServiceId;
    }

    public void setUnitServiceId(String unitServiceId) {
        this.unitServiceId = unitServiceId;
    }

    public String getCustomerQuestion() {
        return customerQuestion;
    }

    public void setCustomerQuestion(String customerQuestion) {
        this.customerQuestion = customerQuestion;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getSalerId() {
        return salerId;
    }

    public void setSalerId(Integer salerId) {
        this.salerId = salerId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Integer agencyId) {
        this.agencyId = agencyId;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public Integer getResourceType() {
        return resourceType;
    }

    public void setResourceType(Integer resourceType) {
        this.resourceType = resourceType;
    }

    public boolean valid() {
        return StrUtil.isNotBlank(this.getCustomerQuestion()) ;
    }
}
