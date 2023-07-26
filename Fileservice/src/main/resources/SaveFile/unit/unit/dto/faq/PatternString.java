package com.tuniu.mob.ocsfaq.faq.unit.dto.faq;

import java.util.List;

public class PatternString {

    private Float threshold;


    private List<FaqContent> content;

    public Float getThreshold() {
        return threshold;
    }

    public void setThreshold(Float threshold) {
        this.threshold = threshold;
    }

    public List<FaqContent> getContent() {
        return content;
    }

    public void setContent(List<FaqContent> content) {
        this.content = content;
    }
}
