package com.learn_Dynamic_Proxy.myioc;

import com.learn_Dynamic_Proxy.myaop.annotation.impl.ProxyChain;

import java.util.HashMap;
import java.util.Map;

/**
 * ioc 管理代理链
 * 切面上下文 生成被代理类和代理链的关系，再生成被代理后的对象放入ioc中。
 * @Author ni
 * @description
 * @ 2021/2/19
 */

public class AopCache {

    private static Map<Class<?>, ProxyChain> cache = new HashMap<>();

    /*
    * 代理链
    * */
    public static void add(Class<?> clazz){};





























}
