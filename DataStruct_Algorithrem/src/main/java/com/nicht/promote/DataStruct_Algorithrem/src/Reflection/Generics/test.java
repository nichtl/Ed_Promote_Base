package com.nicht.promote.DataStruct_Algorithrem.src.Reflection.Generics;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Nicht
 * @description
 * @ 2020/12/2
 */
public class test {
    /*
    List<? extend String>
    当初的问题
    虽然有了解答其实还是半知半解
    https://tieba.baidu.com/p/6711807461
    */
    public static void main(String[] args) {
        //上界
        List<? extends Fruit> flistTop = new ArrayList<Apple>();
        flistTop.add(null);
        //add Fruit对象会报错
        //flistTop.add(new Fruit());
        Fruit fruit1 = flistTop.get(0);

        //下界
        List<? super Apple> flistBottem = new ArrayList<Apple>();
       // flistBottem.add(new Fruit()); 不能add apple 的父类  只能add apple及其子类
        flistBottem.add(new Apple());
        flistBottem.add(new Jonathan());
        //get Apple对象会报错
        //Apple apple = flistBottem.get(0);
        /*
        *  记住泛型只能向上转型
        *   ? extend Fruit 泛型上界    ? extend ，只能作为生产者
        *  表示泛型 fruit类 和所有继承fruit类的子类
        *  这种泛型 可以从容器中取出东西 但是却不能放东西
        *  因为当你取出东西时 不论什么情况 都能知道 这个容器中放的必定是一个fruit
        *  因此当取出时未知的子类时都可以向上转型为fruit承接
        *  但是当你放入一个子类时虽然知道放入的一个水果，但是不知道具体到底是什么
        *  在容器里找不到对应的类承接 , ( 泛型类型擦除 ?? )
        *  拓展java泛型实现
        * (Java中的泛型基本上都是在编译器这个层次来实现的。
        * 在生成的Java字节码中是不包含泛型中的类型信息的。使用泛型的时候加上的类型参数，会在编译器在编这个过程就称为类型擦除。)
        * example                  ? super    只能作为消费者译的时候去掉。
        * List<Integer> l1 = new ArrayList<Integer>()
        * List<String>  l2 = new ArrayList<String>()
        * System.out.println(l1.getClass() == l2.getClass()); // true
        * 这是因为在实际编译过程中 Integer 和 String已经被擦出 所以 l1 l2 getClass() 都是 list.class
        * ? extend base   ? 泛型占位符
        * ? super Apple  泛型下界
        *  表示泛型 以Apple 为下类以及Apple的所有父类 一直可追溯到 Object 类
        *  可以放东西却不能取出东西
        *  你不能add Apple的父类，因为不能确定List里面存放的到底是哪个父类。
        但是可以add Apple及其子类。因为不管我的子类是什么类型，它都可以向上转型为Apple及其所有的
        父类甚至转型为Object 用来承接 ,但是当我get的时候，Apple的父类这么多，我用什么接着呢，除了Object，
        其他的都接不住。所以，归根结底可以用一句话表示，那就是编译器可以支持向上转型
        * */




    }



}
