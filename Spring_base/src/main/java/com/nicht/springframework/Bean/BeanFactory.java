package com.nicht.springframework.Bean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author Nicht
 * @Description
 * @Time 2021/10/18
 * @Link
 */
public class BeanFactory {
    private Map<String,BeanDefinition> beanDefinitionMap  = new ConcurrentHashMap<>();

    public int findComplement(int num) {
        int mask  =  Integer.MAX_VALUE;
         while((num&mask)>0){
             mask<<=1;
         }

        return(~mask)^num;
    }

}

