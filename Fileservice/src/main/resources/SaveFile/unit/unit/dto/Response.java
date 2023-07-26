package com.tuniu.mob.ocsfaq.faq.unit.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.tuniu.mob.ocsfaq.faq.unit.enums.UnitResponseEnum;

public class Response {
    @JSONField(name = "error_code")
    private Integer errorCode;

    @JSONField(name = "error_msg")
    private String errorMsg;


    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public static Response getDefaultResponse() {
        Response response = new Response();
        response.setErrorCode(UnitResponseEnum.ERROR.getErrorCode());
        response.setErrorMsg(UnitResponseEnum.ERROR.getErrorMsg());
        return response;

    }
}
