package com.tuniu.mob.ocsfaq.faq.unit.dto.faq;

import java.util.List;

public class FaqBatchAddQuestion {
    private String faqStdQuestion;

    private List<FaqQuestion> faqQuestions;

    private List<FaqAnswer> faqAnswers;

    private List<FaqPattern> faqPatterns;

    private List<Long> sluTagIds;

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
}
