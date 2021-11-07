package com.nicht.spring_base.Aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author Nicht
 * @Description
 * @Time 2021/10/11
 * @Link
 */
public class AopTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AopConfig.class);
        Service service = applicationContext.getBean(Service.class);
        System.out.println(service.doService(1));
    }
}
