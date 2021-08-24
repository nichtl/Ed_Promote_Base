package com.learn_netty_one.Reflection.Generics;

/**
 * @Author Nicht
 * @description
 * @ 2020/12/2
 */
public class Plate<T> {
private  T item;

    public Plate(T t) {
        this.item = t;
    }

    public T get() {
        return item;
    }

    public void set(T item) {
        this.item = item;
    }
}
