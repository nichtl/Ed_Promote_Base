package com.tuniu.mob.ocsfaq.faq.unit.enums;

public enum  TspServiceEnum {
    BOH_PRODUCT_DETAIL("BOH.NM.ProductController.getDetail","产品详情接口-跟团（自驾）"),
    BOH_GET_DEPART_DATE("BOH.NM.ProductDomainController.getDepartDate","出发日期"),
    QUERY_OA_SALER_INFO("TOF.ORD.SalerQueryController.querySalerById","查询顾问信息"),
    ;





    private  String tspServiceName;


    private  String desc;


     TspServiceEnum(String tspServiceName, String desc) {
        this.tspServiceName = tspServiceName;
        this.desc = desc;
    }

    public String getTspServiceName() {
        return tspServiceName;
    }

    public void setTspServiceName(String tspServiceName) {
        this.tspServiceName = tspServiceName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
