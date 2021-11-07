package com.nicht.spring_base.Aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Author Nicht
 * @Description
 * @Time 2021/10/11
 * @Link
 */
@Configuration
@EnableAspectJAutoProxy//开启切面代理最终会生成一个代理类到ioc中管理
@ComponentScan("com.nicht.spring_base.Aop")
public class AopConfig {
    public AopConfig(){
        System.out.println("------AopConfig--------");
    }

    @Bean
    public Service getService(){
        return new Service();
    }

    @Bean
    public LogAspects logAspects(){
        return new LogAspects();
    }
}