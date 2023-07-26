package com.tuniu.mob.ocsfaq.faq.unit.enums;

public enum UnitResponseEnum {
    SUCCESS(710000, "请求成功"),
    UNIT_REQUEST_SUCCESS_BUT_NO_ANSWER(710001,"请求成功,无满足答案"),
    ERROR(719999, "请求失败"),

    PARAMA_ERROR(719000, "参数有误,请检查"),
    UNIT_QPS_LIMIT(719001,"unit对话接口qps限制"),
    REPEAT_INTERFACE(719002,"接口已经存在"),
    UNIT_SUCCESS(0, "调用unit对话接口成功");


    private Integer errorCode;

    private String errorMsg;

    UnitResponseEnum(Integer errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

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
}
