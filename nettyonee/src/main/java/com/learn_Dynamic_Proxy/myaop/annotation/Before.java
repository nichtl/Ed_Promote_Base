package com.learn_Dynamic_Proxy.myaop.annotation;


import java.lang.annotation.*;
/*前置通知*/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Before {

}
