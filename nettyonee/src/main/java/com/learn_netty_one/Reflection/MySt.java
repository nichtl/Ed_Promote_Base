package com.learn_netty_one.Reflection;

import java.util.List;

/**
 * @Author admin
 * @description
 * @ 2020/12/1
 */
public class MySt {
    protected List<String> stringList = null   ;
    protected List list = null   ;


    public List<String> getStringList() {
        return stringList;
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }
}
