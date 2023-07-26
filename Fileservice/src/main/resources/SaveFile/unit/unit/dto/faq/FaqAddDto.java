package com.tuniu.mob.ocsfaq.faq.unit.dto.faq;

import cn.hutool.core.util.StrUtil;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class FaqAddDto {

    private Long skillId;

    private String faqStdQuestion;

    private List<FaqQuestion> faqQuestions;

    private List<FaqAnswer> faqAnswers;

    private List<FaqPattern> faqPatterns;

    private List<Long> sluTagIds;

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
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

    public List<FaqPattern> getFaqPatterns() {
        return faqPatterns;
    }

    public void setFaqPatterns(List<FaqPattern> faqPatterns) {
        this.faqPatterns = faqPatterns;
    }

    public List<Long> getSluTagIds() {
        return sluTagIds;
    }

    public void setSluTagIds(List<Long> sluTagIds) {
        this.sluTagIds = sluTagIds;
    }


    public boolean valid() {
        if (this.getSkillId() == null || this.getSkillId() <= 0) {
            return false;
        }

        if (CollectionUtils.isEmpty(this.getFaqAnswers()) || StrUtil.isBlank(this.getFaqStdQuestion())) {
            return false;
        }

        return true;
    }
}
