package com.tuniu.mob.ocsfaq.faq.unit.dto.faq;

public class FaqSkillTag {

    private Long tagId;


    private String tagName;

    // 标记的问答对数量量
    private Integer faqCount;
    //标签展现优先级
    private Integer priority;

    //传faqId时⽣生效 tag选中状态：0 ：全不不选状态 1：全选状态2：半选状态
    private Integer tagSelect;

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Integer getFaqCount() {
        return faqCount;
    }

    public void setFaqCount(Integer faqCount) {
        this.faqCount = faqCount;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getTagSelect() {
        return tagSelect;
    }

    public void setTagSelect(Integer tagSelect) {
        this.tagSelect = tagSelect;
    }
}
