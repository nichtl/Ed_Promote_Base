package com.nicht.spring_base;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.core.ResolvableType;
import org.springframework.lang.Nullable;

/**
 * @Author Nicht
 * @Description
 * @Time 2021/10/11
 * @Link
 */
public interface CodeRead {

    String FACTORY_BEAN_PREFIX = "&";

    Object getBean(String var1) throws BeansException;

    <T> T getBean(String var1, Class<T> var2) throws BeansException;

    Object getBean(String var1, Object... var2) throws BeansException;

    <T> T getBean(Class<T> var1) throws BeansException;

    <T> T getBean(Class<T> var1, Object... var2) throws BeansException;

    <T> ObjectProvider<T> getBeanProvider(Class<T> var1);

    <T> ObjectProvider<T> getBeanProvider(ResolvableType var1);

    boolean containsBean(String var1);

    boolean isSingleton(String var1) throws NoSuchBeanDefinitionException;

    boolean isPrototype(String var1) throws NoSuchBeanDefinitionException;

    boolean isTypeMatch(String var1, ResolvableType var2) throws NoSuchBeanDefinitionException;

    boolean isTypeMatch(String var1, Class<?> var2) throws NoSuchBeanDefinitionException;

    @Nullable
    Class<?> getType(String var1) throws NoSuchBeanDefinitionException;

    @Nullable
    Class<?> getType(String var1, boolean var2) throws NoSuchBeanDefinitionException;

    String[] getAliases(String var1);

    /**  spring中BeanFactory和FactoryBean的区别
     *  BeanFactory定义了IOC容器的最基本形式，并提供了IOC容器应遵守的的最基本的接口，
     *  也就是Spring IOC所遵守的最底层和最基本的编程规范。在Spring代码中，
     *  BeanFactory只是个接口，并不是IOC容器的具体实现，但是Spring容器给出了很多种实现，
     *  如 DefaultListableBeanFactory、XmlBeanFactory、ApplicationContext等，都是附加了其他扩展功能的实现。
     *BeanFactory是个Factory，也就是IOC容器或对象工厂，FactoryBean是个Bean。
     *在Spring中，所有的Bean都是由BeanFactory(也就是IOC容器)来进行管理的。
     *但对FactoryBean而言，这个Bean不是简单的Bean，而是一个能生产或者修饰对象生成的工厂Bean,
     *它的实现与设计模式中的工厂模式和修*饰器模式类似
     *
     * */

    public interface FactoryBean<T> {
        String OBJECT_TYPE_ATTRIBUTE = "factoryBeanObjectType";

        @Nullable
        T getObject() throws Exception;

        @Nullable
        Class<?> getObjectType();

        default boolean isSingleton() {
            return true;
        }
    }
}
