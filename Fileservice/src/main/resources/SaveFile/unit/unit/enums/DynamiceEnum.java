package com.tuniu.mob.ocsfaq.faq.unit.enums;

/**
 * @Description
 * @Date 2022/12/1
 */
public enum DynamiceEnum {
    GET(1,"get","METHOD_TYPE"),
    POST(2,"post","METHOD_TYPE"),
    HTTP(1,"http","REQUEST_TYPE"),
    TSP(2,"tsp","REQUEST_TYPE"),
    INTERFACE_PARAM(1,"接口传入","PARAM_TYPE"),
    CONST_VALUE(2,"固定值","PARAM_TYPE"),
    CONTEXT_OUT_PARAM(3,"配置的上文接口出参","PARAM_TYPE"),
    NONE(-1,"none")
    ;





    private  Integer code;

    private  String description;

    private  String type;


    public Integer getCode() {

        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

     DynamiceEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    DynamiceEnum(Integer code, String description, String type) {
        this.code = code;
        this.description = description;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static DynamiceEnum getDynamiceEnumByCodeAndType(Integer code, String    type) {
        DynamiceEnum res = NONE;
        for (DynamiceEnum e: DynamiceEnum.values()) {
             if(e.getType().equals(type) && e.getCode().equals(code)) {
                 res = e;
                 break;
             }
        }
        return  res;
    }



}
