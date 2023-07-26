/**
 * Copyright (C) 2006-2017 Tuniu All rights reserved
 */
package com.tuniu.mob.ocsfaq.faq.entity;

import java.util.Date;

/**
 * Date: 2017-05-17
 *
 */
public class UserBehaviorEntity {
    private Integer id;
    private Integer userId;
    private Integer fstCategoryId;
    private Integer secCategoryId;
    private Integer faqId;
    private Date createTime;
    private Date updateTime;

    public static UserBehaviorEntity create() {
        UserBehaviorEntity userBehavior = new UserBehaviorEntity();
        userBehavior.setUserId(0);
        userBehavior.setFstCategoryId(0);
        userBehavior.setSecCategoryId(0);
        userBehavior.setFaqId(0);
        return userBehavior;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFstCategoryId() {
        return fstCategoryId;
    }

    public void setFstCategoryId(Integer fstCategoryId) {
        this.fstCategoryId = fstCategoryId;
    }

    public Integer getSecCategoryId() {
        return secCategoryId;
    }

    public void setSecCategoryId(Integer secCategoryId) {
        this.secCategoryId = secCategoryId;
    }

    public Integer getFaqId() {
        return faqId;
    }

    public void setFaqId(Integer faqId) {
        this.faqId = faqId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
