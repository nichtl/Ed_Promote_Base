package com.nicht.promote.DataStruct_Algorithrem.src.Reflection;


import com.nicht.promote.DataStruct_Algorithrem.src.Reflection.Annotation.AnnotationTest;
import com.nicht.promote.DataStruct_Algorithrem.src.Reflection.Annotation.MyAnnotationimpl;
import com.nicht.promote.DataStruct_Algorithrem.src.Reflection.model.UserBean;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author admin
 * @description 打印方法名
 * @ 2020/12/1
 */
public class Rtest1 {
    public static void main(String[] args) throws ClassNotFoundException {
        Method [] methods = UserBean.class.getMethods();
        AnnotationTest annotationTest = new AnnotationTest();
        MyAnnotationimpl myAnnotationimpl = new MyAnnotationimpl();
        myAnnotationimpl.myanno();
        System.out.println( annotationTest.toString() );
        //The Generics Reflection Rule of Thumb
        List<? extends String > list = new ArrayList<>();

    }










}
