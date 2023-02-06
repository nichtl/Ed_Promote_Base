package com.learn_netty_one.Reflection;



import com.learn_netty_one.Reflection.Annotation.AnnotationTest;
import com.learn_netty_one.Reflection.Annotation.MyAnnotationimpl;
import com.learn_netty_one.Reflection.model.UserBean;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @Author admin
 * @description 打印方法名
 * @ 2020/12/1
 */
public class Rtest1 {
    public static void main(String[] args) throws ClassNotFoundException {
        HashMap map = new HashMap();



        Method [] methods = UserBean.class.getMethods();
        Arrays.stream(methods).forEach((method) -> {
            System.out.println(method.getName());
        });
        AnnotationTest annotationTest = new AnnotationTest();
        MyAnnotationimpl myAnnotationimpl = new MyAnnotationimpl();
        myAnnotationimpl.myanno();

        //The Generics Reflection Rule of Thumb
        List<? extends String > list = new ArrayList<>();



    }










}
