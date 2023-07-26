package com.tuniu.mob.ocsfaq.faq.unit.dto.faq;

import com.tuniu.mob.ocsfaq.faq.unit.dto.Response;
import com.tuniu.mob.ocsfaq.faq.unit.enums.UnitResponseEnum;

public class FaqAnswerResponse extends Response {


    public FaqAnswerResult result;

    public FaqAnswerResult getResult() {
        return result;
    }

    public void setResult(FaqAnswerResult result) {
        this.result = result;
    }


    public static FaqAnswerResponse getDefaultResponse() {
        FaqAnswerResponse response = new FaqAnswerResponse();
        response.setErrorCode(UnitResponseEnum.ERROR.getErrorCode());
        response.setErrorMsg(UnitResponseEnum.ERROR.getErrorMsg());
        return response;
    }


    public static FaqAnswerResponse getEnumResponse(UnitResponseEnum  responseEnum) {
        FaqAnswerResponse response = new FaqAnswerResponse();
        response.setErrorCode(responseEnum.getErrorCode());
        response.setErrorMsg(responseEnum.getErrorMsg());
        return response;
    }


}
