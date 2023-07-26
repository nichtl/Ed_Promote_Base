package com.tuniu.mob.ocsfaq.faq.unit.dto.faq;

import com.tuniu.mob.ocsfaq.faq.unit.dto.Response;

import java.util.List;

public class FaqSkillModelListResponse extends Response {


    private List<FaqSkillModelListResult> result;


    public List<FaqSkillModelListResult> getResult() {
        return result;
    }

    public void setResult(List<FaqSkillModelListResult> result) {
        this.result = result;
    }
}
