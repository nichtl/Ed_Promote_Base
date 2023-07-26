package com.tuniu.mob.ocsfaq.faq.unit.dto.faq;

import java.util.List;

public class FaqPair {
    //问答对id
    private Long faqId;
    //类别: user(⾃定义)、 system(系统)
    private String faqCategory;
    //标准问题
    private String faqStdQuestion;
    //问题列表
    private List<FaqQuestion> faqQuestions;

    // 回答列表
    private List<FaqAnswer> faqAnswers;

    //问答对标签列列表
    private List<SluTag> sluTags;
    //创建时间
    private String createTime;
    //更更新时间
    private String updateTime;

    // 问题模板数量
    private String patternCount;

    public Long getFaqId() {
        return faqId;
    }

    public void setFaqId(Long faqId) {
        this.faqId = faqId;
    }

    public String getFaqCategory() {
        return faqCategory;
    }

    public void setFaqCategory(String faqCategory) {
        this.faqCategory = faqCategory;
    }

    public String getFaqStdQuestion() {
        return faqStdQuestion;
    }

    public void setFaqStdQuestion(String faqStdQuestion) {
        this.faqStdQuestion = faqStdQuestion;
    }

    public List<FaqQuestion> getFaqQuestions() {
        return faqQuestions;
    }

    public void setFaqQuestions(List<FaqQuestion> faqQuestions) {
        this.faqQuestions = faqQuestions;
    }

    public List<FaqAnswer> getFaqAnswers() {
        return faqAnswers;
    }

    public void setFaqAnswers(List<FaqAnswer> faqAnswers) {
        this.faqAnswers = faqAnswers;
    }

    public List<SluTag> getSluTags() {
        return sluTags;
    }

    public void setSluTags(List<SluTag> sluTags) {
        this.sluTags = sluTags;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getPatternCount() {
        return patternCount;
    }

    public void setPatternCount(String patternCount) {
        this.patternCount = patternCount;
    }
}
