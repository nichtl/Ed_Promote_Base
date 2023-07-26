package com.tuniu.mob.ocsfaq.faq.unit.enums;

public enum  ProductdetailPageEnum {
    Drive(8,"110","自驾","tuniuapp://page?iosPageName=TNBoss3NormalDriveDetailViewController&androidPageName=com.tuniu.productdetail.ui.activity.Boss3DriveProductDetailV2Activity&pluginType=13&parameters="),
    Group(1,"102","跟团","tuniuapp://page?iosPageName=TNBoss3DetailViewController&androidPageName=com.tuniu.productdetail.ui.activity.Boss3GroupProductDetailActivity&pluginType=13&parameters="),
    DIY(2,"108","自助","tuniuapp://page?iosPageName=TNBoss3DIYDetailViewController&androidPageName=com.tuniu.productdetail.ui.activity.Boss3DiyProductDetailActivity&pluginType=13&parameters=")
            ;






    private Integer classBrandId;

    private String productType;

    private String  productDetailPage;

    private String productTypeName;



    ProductdetailPageEnum(Integer classBrandId, String productType, String productDetailPage) {
        this.classBrandId = classBrandId;
        this.productType = productType;
        this.productDetailPage = productDetailPage;
    }

    ProductdetailPageEnum(Integer classBrandId, String productType,String productTypeName, String productDetailPage) {
        this.classBrandId = classBrandId;
        this.productType = productType;
        this.productDetailPage = productDetailPage;
        this.productTypeName = productTypeName;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Integer getClassBrandId() {
        return classBrandId;
    }

    public void setClassBrandId(Integer classBrandId) {
        this.classBrandId = classBrandId;
    }



    public String getProductDetailPage() {
        return productDetailPage;
    }

    public void setProductDetailPage(String productDetailPage) {
        this.productDetailPage = productDetailPage;
    }


    public static ProductdetailPageEnum getPageEnumByClassBrandId(Integer classBrandId){
        ProductdetailPageEnum res  = null;
        for (ProductdetailPageEnum e:ProductdetailPageEnum.values()) {
            if(e.getClassBrandId().equals(classBrandId)){
                res = e; break;
            }
        }

        return  res;

    }


}
