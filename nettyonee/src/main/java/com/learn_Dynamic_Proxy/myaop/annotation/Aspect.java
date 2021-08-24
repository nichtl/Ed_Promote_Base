package com.learn_Dynamic_Proxy.myaop.annotation;

import java.lang.annotation.*;

/*自定义注解 标识切面类*/
@Documented
@Retention(RetentionPolicy.RUNTIME) //
@Target(ElementType.TYPE) // 应用于类
@Inherited  // 该注解用于父类时子类同样拥有   仅限于 ElementType.TYPE
public @interface Aspect {
    //切入点
    String pointCut();

    //过滤
    String filter() default "";
}
