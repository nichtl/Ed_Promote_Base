package com.nicht.promote.DataStruct_Algorithrem.src.Reflection.Generics;

/**
 * @Author Nicht
 * @description
 * @ 2020/12/2
 */
public class Apple extends  Fruit {
private  String appname;

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public Apple(String appname) {
        this.appname = appname;
    }

    public Apple() {
    }
}
