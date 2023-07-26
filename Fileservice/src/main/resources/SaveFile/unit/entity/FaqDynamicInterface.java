package com.tuniu.mob.ocsfaq.faq.entity;



import java.util.Date;

public class FaqDynamicInterface {
    /**
     * 主键
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     * 接口名称
     *
     * @mbg.generated
     */
    private String name;

    /**
     * 接口描述
     *
     * @mbg.generated
     */
    private String description;

    /**
     * 接口地址
     *
     * @mbg.generated
     */
    private String address;

    /**
     * 接口类型 1 http 2 tsp 3 pebble
     *
     * @mbg.generated
     */
    private Integer type;

    /**
     * 当type =1 时 需要指定  1 get 2 post
     *
     * @mbg.generated
     */
    private Integer methodType;

    /**
     *  入参id逗号分隔
     *
     * @mbg.generated
     */
    private String inParameter;

    /**
     *  出参id 逗号分隔
     *
     * @mbg.generated
     */
    private String outParameter;

    private Date addTime;

    /**
     * 入参是否需要加密 0 不需要 1 base64 encode
     */
    private Integer inParameterNeedEncode;

    /**
     *  出参是否需要解密 0 不需要 1 base64 decode
     */
    private Integer outParameterNeedDecode;

    public Integer getInParameterNeedEncode() {
        return inParameterNeedEncode;
    }

    public void setInParameterNeedEncode(Integer inParameterNeedEncode) {
        this.inParameterNeedEncode = inParameterNeedEncode;
    }

    public Integer getOutParameterNeedDecode() {
        return outParameterNeedDecode;
    }

    public void setOutParameterNeedDecode(Integer outParameterNeedDecode) {
        this.outParameterNeedDecode = outParameterNeedDecode;
    }

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getMethodType() {
        return methodType;
    }

    public void setMethodType(Integer methodType) {
        this.methodType = methodType;
    }

    public String getInParameter() {
        return inParameter;
    }

    public void setInParameter(String inParameter) {
        this.inParameter = inParameter == null ? null : inParameter.trim();
    }

    public String getOutParameter() {
        return outParameter;
    }

    public void setOutParameter(String outParameter) {
        this.outParameter = outParameter == null ? null : outParameter.trim();
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
        return "FaqDynamicInterface{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                ", type=" + type +
                ", methodType=" + methodType +
                ", inParameter='" + inParameter + '\'' +
                ", outParameter='" + outParameter + '\'' +
                ", addTime=" + addTime +
                ", inParameterNeedEncode=" + inParameterNeedEncode +
                ", outParameterNeedDecode=" + outParameterNeedDecode +
                ", updateTime=" + updateTime +
                '}';
    }
}
