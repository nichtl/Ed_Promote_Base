package com.nicht.spring_base.Aop;

/**
 * @Author Nicht
 * @Description
 * @Time 2021/10/12
 * @Link
 */
public class Fruit {
    private String FruitName;

    public Fruit() {
        this.FruitName = "Parent";
        System.out.println("父类初始化");
    }

    public String getFruitName() {
        return FruitName;
    }

    public void setFruitName(String fruitName) {
        FruitName = fruitName;
    }
}
