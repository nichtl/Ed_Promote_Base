package com.learn_Dynamic_Proxy.myaop.annotation.impl;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 *
 * 根据代理链生成代理后的对象
 * @Author ni
 * @description
 * @ 2021/2/19
 */
public class ProxyManager {

@SuppressWarnings("unchecked")
public  static<T> T getProxies(Class<T> clazz, ProxyChain chain) {
 return (T) Enhancer.create(clazz, new MethodInterceptor() {
        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            return chain.handle(o,method,methodProxy,objects);
        }
    });


}















}
