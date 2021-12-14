package com.nicht.spring_base.Aop;

/**
 * @Author Nicht
 * @Description
 * @Time 2021/10/12
 * @Link
 */
public class Apple extends Fruit{
    private String AppleName;
    private String appleType;
    private String productPlace;


    public String getAppleType() {
        return appleType;
    }

    public String getProductPlace() {
        return productPlace;
    }

    public Apple() {
        System.out.println("子类初始化");
    }

    public Apple(String appleName) {
       this();
    }

    public String getAppleName() {
        return AppleName;
    }

    public Apple setAppleName(String appleName) {
        this.AppleName =appleName;
        return   this;
    }

    public Apple setAppleType(String appleType) {
        this.appleType = appleType;
        return  this;
    }

    public Apple setProductPlace(String productPlace) {
        this.productPlace = productPlace;
        return  this;
    }


}
