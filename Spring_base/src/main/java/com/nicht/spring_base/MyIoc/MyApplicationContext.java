package com.nicht.spring_base.MyIoc;

/**
 * @Author Nicht
 * @Description
 * @Time 2021/10/9
 * @Link
 */
public class MyApplicationContext {
    private  Class configClass; //配置类

    public MyApplicationContext(Class configClass) {
        this.configClass = configClass;
        //解析配置类

    }
    public  Object getBean(String beanName){
        return  null;
    }
}
