package com.tuniu.mob.ocsfaq.faq.unit.dto.faq;

import com.tuniu.mob.ocsfaq.faq.unit.dto.Response;

public class FaqDetailResponse extends Response {
    private FaqPair result;

    public FaqPair getResult() {
        return result;
    }

    public void setResult(FaqPair result) {
        this.result = result;
    }
}
