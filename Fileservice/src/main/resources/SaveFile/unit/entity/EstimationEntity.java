/**
 * Copyright (C) 2006-2015 Tuniu All rights reserved
 */
package com.tuniu.mob.ocsfaq.faq.entity;

/**
 *
 * Date: 2015-09-01
 * 问题评论表操作接口
 * @author qiuyang4
 */
public class EstimationEntity {

    //评论id
    private Integer id;
    //用户id
    private Integer userId;
    //问题id
    private Integer questionId;
    //是否有用：0代表未评价，1代表有用，2代表没有用
    private Integer estimation;

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

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getEstimation() {
        return estimation;
    }

    public void setEstimation(Integer estimation) {
        this.estimation = estimation;
    }
}
