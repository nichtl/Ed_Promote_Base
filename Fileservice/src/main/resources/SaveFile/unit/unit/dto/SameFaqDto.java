package com.tuniu.mob.ocsfaq.faq.unit.dto;

import cn.hutool.core.collection.CollectionUtil;

import java.util.List;

public class SameFaqDto {

    private  Integer faqId;

    private List<String> questions;

    public Integer getFaqId() {
        return faqId;
    }

    public void setFaqId(Integer faqId) {
        this.faqId = faqId;
    }

    public List<String> getQuestions() {
        return questions;
    }

    public void setQuestions(List<String> questions) {
        this.questions = questions;
    }

    public  boolean  valid(){
        if(this.getFaqId() ==null || this.getFaqId()<=0 || CollectionUtil.isEmpty(this.getQuestions())){
            return  false;
        }
        return  true;
    }
}
