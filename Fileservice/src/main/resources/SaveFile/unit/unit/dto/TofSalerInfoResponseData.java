package com.tuniu.mob.ocsfaq.faq.unit.dto;

public class TofSalerInfoResponseData {

    private  Integer salerId;
    private  String  workNum;
    private  String  name;
    private  String  cellPhone;
    private  String  telExt;


    public Integer getSalerId() {
        return salerId;
    }

    public void setSalerId(Integer salerId) {
        this.salerId = salerId;
    }

    public String getWorkNum() {
        return workNum;
    }

    public void setWorkNum(String workNum) {
        this.workNum = workNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getTelExt() {
        return telExt;
    }

    public void setTelExt(String telExt) {
        this.telExt = telExt;
    }
}
