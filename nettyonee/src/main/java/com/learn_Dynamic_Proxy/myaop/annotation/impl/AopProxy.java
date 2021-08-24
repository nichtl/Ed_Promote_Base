package com.learn_Dynamic_Proxy.myaop.annotation.impl;

import com.learn_Dynamic_Proxy.myaop.annotation.After;
import com.learn_Dynamic_Proxy.myaop.annotation.Before;
import com.learn_Dynamic_Proxy.myaop.annotation.Throwing;
import com.utils.CodeUtils;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.nio.charset.CoderMalfunctionError;
import java.util.ArrayList;
import java.util.List;

/**
 * 针对类单个切面情况
 * aspect代理类将切面类切入被切面类
 * @Author ni
 * @description
 * @ 2021/2/19
 */
public class AopProxy<B> {
    private Object proxy;   //切面类
    private Method beforeMethod; //切面类中的前置方法
    private Method afterMethod; //切面类中的后置方法
    private Method throwingMethod; //切面类中的异常方法
    //执行

    public  Object proceed(ProxyChain proxyChain, Object target, Method method, MethodProxy methodProxy,Object[] params){
     Object result = null;
     before(method,params);
     try {
         AopProxy proxy= proxyChain.next();
         if(null!= proxy){
             result = proxy.proceed(proxyChain, target, method, methodProxy, params);
         }else {
             result = methodProxy.invokeSuper(target,params); //
         }
         after(method,params);
      }catch (Throwable e) {
         throwing(method,params,e);
     }finally {
       proxyChain.removeLocal();
     }
       return  result;
    }

    public void before(Method method, Object[] args){
        if(CodeUtils.isNotNull(beforeMethod)) {
            return;
        }
        try {
              beforeMethod.setAccessible(true);
              beforeMethod.invoke(proxy, new Object[]{method, args});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void after(Method method, Object[] args){
        if(CodeUtils.isNotNull(afterMethod)) {
            return;
        }
        try {
            afterMethod.setAccessible(true);
            afterMethod.invoke(proxy, new Object[]{method, args});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void throwing(Method method, Object[] args, Throwable e){
        if(CodeUtils.isNotNull(throwingMethod)) {
            return;
        }
        try {
            throwingMethod.setAccessible(true);
            throwingMethod.invoke(proxy, new Object[]{method, args});
        } catch (Exception el) {
            el.printStackTrace();
        }
    }


    private  void setValue(Class<?> clazz){
        Method[]  methods = clazz.getDeclaredMethods();
        for (Method  method : methods){
            if(method.isAnnotationPresent(Before.class)){
             this.beforeMethod=method;
            }else if (method.isAnnotationPresent(After.class)){
                this.beforeMethod=method;
            } else if(method.isAnnotationPresent(Throwing.class)){
                this.throwingMethod=method ;
            }
        }
    }
    public B getProxyB(){return  (B)proxy;}

    public Object getProxy() {
        return proxy;
    }

    public void setProxy(Object proxy) {
        this.proxy = proxy;
    }

    public Method getBeforeMethod() {
        return beforeMethod;
    }

    public void setBeforeMethod(Method beforeMethod) {
        this.beforeMethod = beforeMethod;
    }

    public Method getAfterMethod() {
        return afterMethod;
    }

    public void setAfterMethod(Method afterMethod) {
        this.afterMethod = afterMethod;
    }

    public Method getThrowingMethod() {
        return throwingMethod;
    }

    public void setThrowingMethod(Method throwingMethod) {
        this.throwingMethod = throwingMethod;
    }

    public AopProxy(Object proxy) {
        this.proxy = proxy;
        setValue(proxy.getClass());
    }
}
