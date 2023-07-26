package com.tuniu.mob.ocsfaq.faq.unit.dto.faq;

public class FaqAnswer {
    private String answer;

    private Integer isDefault;

    public FaqAnswer() {
    }

    public FaqAnswer(String answer, Integer isDefault) {
        this.answer = answer;
        this.isDefault = isDefault;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }
}
