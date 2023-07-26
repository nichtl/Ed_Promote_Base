package com.tuniu.mob.ocsfaq.faq.unit.dto.faq;

import com.tuniu.mob.ocsfaq.faq.unit.dto.Response;

import java.util.List;

public class FaqSkillTagListResponse extends Response {


    private FaqSkillTagResult result;

    public FaqSkillTagResult getResult() {
        return result;
    }

    public void setResult(FaqSkillTagResult result) {
        this.result = result;
    }
}
