package com.nicht.promote.DataStruct_Algorithrem.src.Reflection.Annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @Author admin
 * @description
 * @ 2020/12/1
 */
public class MyAnnotationimpl {
    public    void myanno() throws ClassNotFoundException {
        ClassLoader cl = this.getClass().getClassLoader();
        Class  clazz  = cl.loadClass("Reflection.Annotation.AnnotationTest");

        Annotation [] annotations =  clazz.getAnnotations();
       try {
           for (Annotation annotation : annotations) {
               if (clazz.isAnnotationPresent(MyAnnotation.class)) {
                   MyAnnotation myAnnotation = (MyAnnotation) annotation;
                   System.out.println("name : " + myAnnotation.name());
                   System.out.println("value :" + myAnnotation.value());
                   Method[] methods = clazz.getMethods();
                   AnnotationTest annotationTest = (AnnotationTest) clazz.newInstance();
                   for (Method method : methods
                   ) {
                       if (method.getName().contains("setAnnotationname")) {
                           method.invoke(annotationTest, "动态修改name");
                       }
                       if(method.getName().contains("setAnnotationdes")){
                           method.invoke(annotationTest,"动态描述");
                       }
                       if(method.getName().contains("setAnnotationvalue")){
                           method.invoke(annotationTest,"18");
                       }
                   }
                   System.out.println(annotationTest.toString());
               }
           }
       }catch (Exception e){
           e.printStackTrace();
       }
    }

}
