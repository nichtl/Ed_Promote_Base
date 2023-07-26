package com.tuniu.mob.ocsfaq.faq.unit.dto.faq;

import com.alibaba.fastjson.annotation.JSONField;

public class SlotValue {


    @JSONField(name = "original_word")
    private String faqId;

    @JSONField(name = "normalized_word")
    private String faqAnswer;


    public String getFaqId() {
        return faqId;
    }

    public void setFaqId(String faqId) {
        this.faqId = faqId;
    }

    public String getFaqAnswer() {
        return faqAnswer;
    }

    public void setFaqAnswer(String faqAnswer) {
        this.faqAnswer = faqAnswer;
    }
}
