package com.nicht.promote.DataStruct_Algorithrem.src.Reflection;


import com.nicht.promote.DataStruct_Algorithrem.src.Reflection.model.UserBean;
import com.nicht.promote.DataStruct_Algorithrem.src.Reflection.model.privateBean;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @Author Nicht
 * @description
 * @ 2020/12/1
 */

public class Rtest2 {
    /*
    * getFields()与getDeclaredFields()区别:getFields()只能访问类中声明为公有的字段,私有的字段它无法访问，
    * 能访问从其它类继承来的公有方法.getDeclaredFields()能访问类中所有的字段,与public,private,protect无关，
    * 不能访问从其它类继承来的方法
    * getMethods()与getDeclaredMethods()区别:getMethods()只能访问类中声明为公有的方法,
    * 私有的方法它无法访问,能访问从其它类继承来的公有方法.getDeclaredFields()能访问类中所有的字段,
    * 与public,private,protect无关,不能访问从其它类继承来的方法
    * getConstructors()与getDeclaredConstructors()区别:getConstructors()只能访问类中声明为public的构造函数.
    * getDeclaredConstructors()能访问类中所有的构造函数,与public,private,protect无关
    * */
    public static void main(String[] args)  {
         try {
             // 运行时 根据全类名（全路径加类名） 动态获取类
             Class aClass  = Class.forName("Reflection.model.UserBean");
             // 获取实例
             UserBean userBean = (UserBean) aClass.newInstance() ;
             // getSimpleName() 简单类名 “UserBean”  getName() "全路径 类名"  "Reflection.model.UserBean"
             System.out.println("动态实例 user 全路径类名 "+ aClass.getName() );
             System.out.println("动态实例 user 类名  " +   aClass.getSimpleName());
             //modifiers
             /** PUBLIC: 1
             PRIVATE: 2
             PROTECTED: 4
             STATIC: 8
             FINAL: 16
             SYNCHRONIZED: 32
             VOLATILE: 64
             TRANSIENT: 128
             NATIVE: 256
             INTERFACE: 512
             ABSTRACT: 1024
             STRICT: 2048**/
             int modifiers  =  aClass.getModifiers() ;
             System.out.println( "类的修饰符" + modifiers );
             Package aPackage =aClass.getPackage();
             System.out.println("获取类的package info  " + aPackage.getName() );
             Method [] methods  = aClass.getMethods();
             for (Method method : methods) {
                  if(isSetter(method)) {
                      if(method.getName().equals("setName") ){
                          method.invoke(userBean,"zhangsan");
                          // invoke(obj 目标类的实例 ,args // 传入参数)
                      }
                      System.out.println("Setter   " + method.getName());
                  }
                 if(isGetter(method)) {
                     System.out.println("Getter   "+ method.getName());
                 }
             }
             userBean.setLoginName("loginname");
             userBean.setRoleName("rolename");
             for (Method method : methods) {
                 if(isGetter(method)) {
                     if (method.getName().contains("Name")) {
                         System.out.println("动态 : " +method.getName() +" : "+ method.invoke(userBean));
                     }
                 }
             }
             System.out.println(userBean.getName());
             // getDeclaredField 私有字段与方法
             privateBean privatebean  = new privateBean("defaultvalue");
             Class  pbclazz = Class.forName("Reflection.model.privateBean");
             Field privateFiled = privatebean.getClass().getDeclaredField("stringdefaultvalue");
             /* 将此对象的 accessible 标志设置为指示的布尔值。值为 true 则指示反射的对象在使用时应该取消 Java 语言访问检查。
             值为 false 则指示反射的对象应该实施 Java 语言访问检查;实际上setAccessible是启用和禁用访问安全检查的开关,
             并不是为true就能访问为false就不能访问 ；https://blog.csdn.net/buyaoshuohua1/article/details/104175188
             由于JDK的安全检查耗时较多.所以通过setAccessible(true)的方式关闭安全检查就可以达到提升反射速度的目的*/
             privateFiled.setAccessible(true);
             String fieldValue  = (String) privateFiled.get(privatebean);
             System.out.println("filedValue = " + fieldValue);
             //获取私有字段数组
             Field []  fields = privatebean.getClass().getDeclaredFields();
             for (int i = 0; i < fields.length; i++) {
                 fields[i].setAccessible(true);
                 // 注意调用  pbclazz.newInstance() 调用的是无参构造方法
                 // 使用有参构造需要使用 getDeclaredConstructor 先获取对应参数的有参构造方法才能Instance 使用
                 Object  name = fields[i].get(pbclazz.getDeclaredConstructor(String.class).newInstance("defaultvalue"));
                 System.out.println("stringdefaultvalue : " + name );
             }
             // The Generics Reflection Rule of Thumb   泛型反射法则
             // 获得 泛型返回类型 List<String>   class java.lang.String
             Method  method  =  MySt.class.getMethod("getStringList",null); // 反射获得方法
             Type returnType =  method.getGenericReturnType();// 此Method对象表示的方法的正式返回类型

             /*List< ? extends BaseBean> list = new ArrayList<>();
             list.get(1);*/
             Type  newtype = Long.class;
             Type  oldtype = String.class;
             if(returnType instanceof ParameterizedType){
                 //Method  method  =  MySt.class.getMethod("getStringList",null); // 反射获得方法
                 //Type returnType =  method.getGenericReturnType();// 此Method对象表示的方法的正式返回类型
                 // returnType 是自己去主动获得的
                 // ParameterizedType 是怎么获得或者说找到这个returnType的来源的method对象的泛型呢？？
                 // ParameterizedType 参数化类型
                 ParameterizedType  type = (ParameterizedType) returnType;
                 Type []  typeArguments  = type.getActualTypeArguments();
                 for(Type typeArgument : typeArguments) {
                      Class typeArgClass = (Class) typeArgument;
                      System.out.println("修改前type  = " + typeArgClass);
                 }
                  //利用ParameterizedType实现type修改
                 for (int i = 0; i < typeArguments.length; ++i) {
                     if(typeArguments[i] == oldtype){
                         System.out.println("替换type String  -->  Long");
                         typeArguments[i] = newtype;
                     }
                 }
                 for(Type typeArgument : typeArguments) {
                     Class typeArgClass = (Class) typeArgument;
                     System.out.println("修改后 type = " + typeArgClass);
                 }
             }

         }
         catch(ClassNotFoundException e){
              e.printStackTrace();
         }
         catch(NoSuchFieldException e){
             e.printStackTrace();
         }
         catch(NoSuchMethodException e) {
             e.printStackTrace();
         }
         catch(Exception e){
             e.printStackTrace();
         }
    }
    public static boolean isGetter(Method method){
        if(!method.getName().startsWith("get"))      return false;
        if(method.getParameterTypes().length != 0)   return false;
        if(void.class.equals(method.getReturnType()))return false;
        return true;
    }

    public static boolean isSetter(Method method){
        if(!method.getName().startsWith("set"))    return false;
        if(method.getParameterTypes().length != 1) return false;
        return true;
    }










}
