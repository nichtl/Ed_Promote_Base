/**
 * Copyright (C) 2006-2015 Tuniu All rights reserved
 */
package com.tuniu.mob.ocsfaq.faq.entity;

/**
 *
 * Date: 2015-09-01
 * faq log记录实体
 * @author qiuyang4
 */
public class LogEntity {

    //操作id
    private Integer id;
    //用户id
    private Integer userId;
    //操作类型：1 添加、2 删除、3 修改、4 查询、5 排序、 6 导入、7 导出
    private Integer operationType;
    //操作时间
    private String operationTime;

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

    public Integer getOperationType() {
        return operationType;
    }

    public void setOperationType(Integer operationType) {
        this.operationType = operationType;
    }

    public String getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(String operationTime) {
        this.operationTime = operationTime;
    }
}
