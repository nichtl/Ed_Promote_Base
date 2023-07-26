package com.tuniu.mob.ocsfaq.faq.unit.enums;

public enum UnitOperateEnum {
    FAQ_IMPORT(1,"导入FAQ"),
    BIND_UPDATE(2,"修改绑定"),
    OPERATE_START(1,"操作进行中"),
    OPERATE_SUCCESS(2,"操作成功结束"),
    OPERATE_ERROR(3,"操作失败" ),
    ;





    private  Integer  code;

    private  String   operateName;

    UnitOperateEnum(Integer code, String operateName) {
        this.code = code;
        this.operateName = operateName;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getOperateName() {
        return operateName;
    }

    public void setOperateName(String operateName) {
        this.operateName = operateName;
    }
}
