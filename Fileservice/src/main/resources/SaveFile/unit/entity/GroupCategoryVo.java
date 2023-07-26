package com.tuniu.mob.ocsfaq.faq.entity;

import java.util.List;

public class GroupCategoryVo {

    private  String categoryName;

    private List<FirstCategoryVo> categoryInfo;

    public GroupCategoryVo() {
    }

    public GroupCategoryVo(String categoryName, List<FirstCategoryVo> categoryInfo) {
        this.categoryName = categoryName;
        this.categoryInfo = categoryInfo;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<FirstCategoryVo> getCategoryInfo() {
        return categoryInfo;
    }

    public void setCategoryInfo(List<FirstCategoryVo> categoryInfo) {
        this.categoryInfo = categoryInfo;
    }
}
