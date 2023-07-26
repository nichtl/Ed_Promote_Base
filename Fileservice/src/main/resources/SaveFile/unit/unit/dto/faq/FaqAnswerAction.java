package com.tuniu.mob.ocsfaq.faq.unit.dto.faq;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class FaqAnswerAction {


    private Integer confidence;


    private String say;


    private String type;
    // 识别到多个问题时返回给用户选择
    private List<FaqAnswerOption> options;

    @JSONField(name = "action_id")
    private String actionId;

    public List<FaqAnswerOption> getOptions() {
        return options;
    }

    public void setOptions(List<FaqAnswerOption> options) {
        this.options = options;
    }

    public Integer getConfidence() {
        return confidence;
    }

    public void setConfidence(Integer confidence) {
        this.confidence = confidence;
    }

    public String getSay() {
        return say;
    }

    public void setSay(String say) {
        this.say = say;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getActionId() {
        return actionId;
    }

    public void setActionId(String actionId) {
        this.actionId = actionId;
    }


}