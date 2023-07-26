package com.tuniu.mob.ocsfaq.faq.unit.dto;

import com.tuniu.mob.ocsfaq.faq.pebbleService.entity.Response;
import com.tuniu.mob.ocsfaq.faq.unit.enums.UnitResponseEnum;

import java.util.List;

public class BohDepartDateResponse extends com.tuniu.mob.ocsfaq.faq.pebbleService.entity.Response {

   private BohDepartDateResponseData data;


    public BohDepartDateResponseData getData() {
        return data;
    }

    public void setData(BohDepartDateResponseData data) {
        this.data = data;
    }

    public static BohDepartDateResponse getDefaultResponse() {
        BohDepartDateResponse response = new BohDepartDateResponse();
        response.setErrorCode(UnitResponseEnum.ERROR.getErrorCode());
        response.setMsg(UnitResponseEnum.ERROR.getErrorMsg());
        response.setSuccess(false);
        return response;
    }
}
