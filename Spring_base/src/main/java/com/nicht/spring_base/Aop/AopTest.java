package com.nicht.spring_base.Aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @Author Nicht
 * @Description
 * @Time 2021/10/11
 * @Link
 */
public class AopTest {
    public static void main(String[] args) {

        System.out.println(
                );
        String[] arr = new String[]{"1","2","3"};
        System.out.println(Arrays.stream(arr).filter(o->o.equals("2")).collect(Collectors.joining(",")));
        System.out.println(Arrays.toString(Arrays.stream(arr).toArray()));
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AopConfig.class);
        //Service service = applicationContext.getBean(Service.class);
        System.out.println();
    }
    public static String longToIP(long ip) {
        // Java doesn't have uint32, so here we use long to represent int32
        return  ((ip  >> 24) & 0xFF) + "." + ((ip  >> 16) & 0xFF) + "." + ((ip  >> 8) & 0xFF) + "." + (ip  & 0xFF) ; // do it!
    }

}
