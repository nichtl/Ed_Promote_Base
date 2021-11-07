package com.nicht.spring_base.Aop;

/**
 * @Author Nicht
 * @Description
 * @Time 2021/10/12
 * @Link
 */
public class Apple extends Fruit{
    private String AppleName;


    public Apple() {
        System.out.println("子类初始化");
    }

    public Apple(String appleName) {
       this();
    }

    public String getAppleName() {
        return AppleName;
    }

    public void setAppleName(String appleName) {
        AppleName = appleName;
    }
}
