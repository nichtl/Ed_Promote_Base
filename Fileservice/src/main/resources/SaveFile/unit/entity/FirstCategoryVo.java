package com.tuniu.mob.ocsfaq.faq.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class FirstCategoryVo {
    //类别Id
    private Integer id;
    //类别名称
    private String name;

    private String parentId;
    //类别图标
    private String icon;

    // 一级菜单映射到百度uinit对话机器人id
    private String unitServiceId;


    //分类id 在线客服 v10.80 产品咨询 对菜单按类别分组
    private Integer  productCategoryId;

    //分类名称
    private String   productCategoryName;


    private Object  children;

    public Integer getId() {
        return id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Object getChildren() {
        return children;
    }

    public void setChildren(Object children) {
        this.children = children;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUnitServiceId() {
        return unitServiceId;
    }

    public void setUnitServiceId(String unitServiceId) {
        this.unitServiceId = unitServiceId;
    }

    public Integer getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Integer productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getProductCategoryName() {
        return productCategoryName;
    }

    public void setProductCategoryName(String productCategoryName) {
        this.productCategoryName = productCategoryName;
    }
}
