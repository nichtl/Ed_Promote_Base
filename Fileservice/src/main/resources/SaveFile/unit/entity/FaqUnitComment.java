package com.tuniu.mob.ocsfaq.faq.entity;

import java.util.Date;

public class FaqUnitComment {
    /**
     * 问答评价id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     * faq问题文本
     *
     * @mbg.generated
     */
    private String question;

    /**
     * 问题id
     *
     * @mbg.generated
     */
    private String faqIds;

    /**
     * 用户id
     *
     * @mbg.generated
     */
    private String userId;

    private Date createTime;

    /**
     * 问题评价类型
     *
     * @mbg.generated
     */
    private Integer questionCommentType;

    /**
     * 机器人满意度评价 0 不满意 1 一般 2 满意
     */
    private Integer  robotCommentType;


    /**
     * 主要用于区分不同来源的评价 1 问题评价 2 机器人满意度评价
     */
    private Integer sourceType;


    /**
     * 机器人满意度评价 问题是否解决标志  0 未解决 1 已经解决
     */
    private Integer solvedFlag;


    public Integer getSolvedFlag() {
        return solvedFlag;
    }

    public void setSolvedFlag(Integer solvedFlag) {
        this.solvedFlag = solvedFlag;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question == null ? null : question.trim();
    }

    public String getFaqIds() {
        return faqIds;
    }

    public void setFaqIds(String faqIds) {
        this.faqIds = faqIds == null ? null : faqIds.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "FaqUnitComment{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", faqIds='" + faqIds + '\'' +
                ", userId='" + userId + '\'' +
                ", createTime=" + createTime +
                ", questionCommentType=" + questionCommentType +
                ", robotCommentType=" + robotCommentType +
                ", sourceType=" + sourceType +
                '}';
    }
}