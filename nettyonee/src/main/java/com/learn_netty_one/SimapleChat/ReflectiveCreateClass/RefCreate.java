package com.learn_netty_one.SimapleChat.ReflectiveCreateClass;

import java.lang.reflect.Constructor;

/**
 * @Author Nicht
 * @Description
 * @Time 2021/5/20
 * @Link
 */
public class RefCreate<T> {
    private   final Constructor<?extends T> constructor;



    public RefCreate(Class<? extends T> clazz) throws NoSuchMethodException {
        this.constructor = clazz.getConstructor();
    }


    private T newChannel()  throws  NoSuchMethodException{
        try {
            return  this.constructor.newInstance();
        }catch (Exception e){
            throw  new NoSuchMethodException();
        }
    }

}
