package com.tuniu.mob.ocsfaq.faq.unit.dto;

import java.util.Date;

public class FaqUnitCommentDto {


    private String sessionId;

    /**
     * 问题
     */
    private String question;


    /**
     * 问题id
     */
    private Integer questionId;

    /**
     * 问题评价类型
     */
    private Integer  questionCommentType;

    /**
     * 机器人满意度
     */


    private  Integer robotCommentType;

    /**
     * 用户id
     */
    private Integer userId;


    /**
     * 问题评价接口  0:无用 1有用
     * 机器人满意度评价接口  -1 未评分  0 不满意 1 一般 2 满意
     */
    private Integer commentType;

    /**
     * 主要用于区分不同来源的评价 1问题评价接口 2 机器人满意度评价接口 3询信息
     */
    private Integer sourceType;


    /**
     * 机器人满意度评价 问题是否解决标志  0 未解决 1 已经解决
     */
    private Integer solvedFlag;


    private Date    createStartTime;


    private Date    createEndTime;

    private  Integer startPosition;


    private  Integer pageSize;

    private  Integer pageNum;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(Integer startPosition) {
        this.startPosition = startPosition;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Date getCreateStartTime() {
        return createStartTime;
    }

    public void setCreateStartTime(Date createStartTime) {
        this.createStartTime = createStartTime;
    }

    public Date getCreateEndTime() {
        return createEndTime;
    }

    public void setCreateEndTime(Date createEndTime) {
        this.createEndTime = createEndTime;
    }

    public Integer getSolvedFlag() {
        return solvedFlag;
    }

    public void setSolvedFlag(Integer solvedFlag) {
        this.solvedFlag = solvedFlag;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Integer getQuestionCommentType() {
        return questionCommentType;
    }

    public void setQuestionCommentType(Integer questionCommentType) {
        this.questionCommentType = questionCommentType;
    }

    public Integer getRobotCommentType() {
        return robotCommentType;
    }

    public void setRobotCommentType(Integer robotCommentType) {
        this.robotCommentType = robotCommentType;
    }

    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    public Integer getCommentType() {
        return commentType;
    }

    public void setCommentType(Integer commentType) {
        this.commentType = commentType;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "FaqUnitCommentDto{" +
                "question='" + question + '\'' +
                ", questionId=" + questionId +
                ", userId=" + userId +
                '}';
    }


}
