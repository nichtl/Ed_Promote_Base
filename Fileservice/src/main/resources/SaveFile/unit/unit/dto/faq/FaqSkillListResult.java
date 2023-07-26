package com.tuniu.mob.ocsfaq.faq.unit.dto.faq;

import com.tuniu.mob.ocsfaq.faq.unit.dto.Result;

import java.util.List;

public class FaqSkillListResult extends Result {

    private List<FaqPair> faqPairs;

    public List<FaqPair> getFaqPairs() {
        return faqPairs;
    }

    public void setFaqPairs(List<FaqPair> faqPairs) {
        this.faqPairs = faqPairs;
    }
}
