package com.learn_Dynamic_Proxy.myaop.annotation.impl;

import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 类有多个切面
 * 包装为代理链维护依次执行
 * @Author ni
 * @description
 * @ 2021/2/19
 */
public class ProxyChain {
    List<AopProxy> proxies = new ArrayList<>();

    //多线程保证线程安全
    private  ThreadLocal<Integer> currentIndex = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 0; // 初始化值只有一个0  用于 维护代理链下标
        };
    };
    // 获取代理链中下一个代理
    public  AopProxy next() {
        if (currentIndex.get() >= proxies.size()){
        return  null;
        }
        AopProxy proxy = proxies.get(currentIndex.get());
        currentIndex.set(currentIndex.get()+1);
        return proxy;
    }

    // 执行代理
    public  Object handle(Object target, Method method, MethodProxy methodProxy,Object[] args){
      return  next().proceed(this,target,method,methodProxy,args);
    }

    public  List<AopProxy> getProxies(){
        return proxies;
    }

    public void setProxies(List<AopProxy> proxies){
        this.proxies= proxies;
    }

    public  void removeLocal(){
        this.currentIndex.remove();;
    }













}
