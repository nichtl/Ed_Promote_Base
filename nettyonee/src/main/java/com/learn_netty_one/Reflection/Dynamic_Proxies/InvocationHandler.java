package com.learn_netty_one.Reflection.Dynamic_Proxies;


import java.lang.reflect.Method;

/**
 * @Author admin
 * @description
 * @ 2020/12/9
 */
//对动态代理的所有方法调用都转发到此InvocationHandler实现。
public interface InvocationHandler  {
    Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable;
}
