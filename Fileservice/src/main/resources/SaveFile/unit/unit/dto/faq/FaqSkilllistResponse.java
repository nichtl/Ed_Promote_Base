package com.tuniu.mob.ocsfaq.faq.unit.dto.faq;

import com.tuniu.mob.ocsfaq.faq.unit.dto.Response;

public class FaqSkilllistResponse extends Response {

    private FaqSkillListResult result;

    public FaqSkillListResult getResult() {
        return result;
    }

    public void setResult(FaqSkillListResult result) {
        this.result = result;
    }
}
