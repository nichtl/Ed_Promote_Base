package com.tuniu.mob.ocsfaq.faq.unit.dto.faq;

public class SluTag {
    private Long tahId;

    private Integer priority;


    private String tagName;

    public Long getTahId() {
        return tahId;
    }

    public void setTahId(Long tahId) {
        this.tahId = tahId;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
