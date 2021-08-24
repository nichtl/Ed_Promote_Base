package com.learn_Dynamic_Proxy.myaop.annotation;

import java.beans.Transient;
import java.lang.annotation.*;
/*后置通知*/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface After {
}
