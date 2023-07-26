package com.tuniu.mob.ocsfaq.faq.unit.dto.faq;

import java.util.List;

public class FaqAnswerInnerResponse {

    private Integer status;


    private String msg;


    private String origin;

    private FaqAnswerSchema schema;

    private List<FaqAnswerAction> actions;


    public FaqAnswerSchema getSchema() {
        return schema;
    }

    public void setSchema(FaqAnswerSchema schema) {
        this.schema = schema;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public List<FaqAnswerAction> getActions() {
        return actions;
    }

    public void setActions(List<FaqAnswerAction> actions) {
        this.actions = actions;
    }
}
