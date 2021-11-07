package com.nicht.spring_base.Aop;

/**
 * @Author Nicht
 * @Description
 * @Time 2021/10/11
 * @Link
 */
public class Service  {
    public Service(){
        System.out.println("=======Service=======");
    }

    //业务方法
    public int doService(int i) {
        int result=5/i;
        System.out.println("运行doService");
        return result;
    }
}
