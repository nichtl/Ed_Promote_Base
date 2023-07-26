package com.tuniu.mob.ocsfaq.faq.unit.service;

public enum EvaluatorProcessor {
    ARRAY_EVAL_4_0(4,0)    ;


    private Integer fieldType;


    private Integer operateType;







    EvaluatorProcessor(Integer fieldType, Integer operateType) {
        this.fieldType = fieldType;
        this.operateType = operateType;
    }


    public Integer getFieldType() {
        return fieldType;
    }

    public void setFieldType(Integer fieldType) {
        this.fieldType = fieldType;
    }

    public Integer getOperateType() {
        return operateType;
    }

    public void setOperateType(Integer operateType) {
        this.operateType = operateType;
    }









}
