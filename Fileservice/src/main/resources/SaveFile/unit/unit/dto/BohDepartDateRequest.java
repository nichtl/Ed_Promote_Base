package com.tuniu.mob.ocsfaq.faq.unit.dto;

import cn.hutool.core.util.StrUtil;

public class BohDepartDateRequest {

    private String departureDate;


    private Integer productId;

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public  boolean valid(){
        if(this.getDepartureDate()==null || StrUtil.isBlank(this.getDepartureDate()) || this.getProductId()==null || this.getProductId()<=0){
            return  false;
        }
        return  true;
    }

}
