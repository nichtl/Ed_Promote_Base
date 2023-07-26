package com.tuniu.mob.ocsfaq.faq.unit.dto.faq;

import com.tuniu.mob.ocsfaq.faq.unit.dto.Response;

public class FAqBatchAddResponse extends Response {
    private FaqBatchAddResult result;

    public FaqBatchAddResult getResult() {
        return result;
    }

    public void setResult(FaqBatchAddResult result) {
        this.result = result;
    }
}
