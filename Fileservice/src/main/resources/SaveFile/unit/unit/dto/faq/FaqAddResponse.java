package com.tuniu.mob.ocsfaq.faq.unit.dto.faq;

import com.tuniu.mob.ocsfaq.faq.unit.dto.Response;

public class FaqAddResponse extends Response {

    private FaqAddResult result;

    public FaqAddResult getResult() {
        return result;
    }

    public void setResult(FaqAddResult result) {
        this.result = result;
    }
}
