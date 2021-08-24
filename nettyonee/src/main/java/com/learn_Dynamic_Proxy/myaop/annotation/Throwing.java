package com.learn_Dynamic_Proxy.myaop.annotation;

import java.lang.annotation.*;
import java.lang.reflect.Member;

/*异常通知*/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Throwing {
}
