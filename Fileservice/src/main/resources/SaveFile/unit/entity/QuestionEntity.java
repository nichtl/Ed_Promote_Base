package com.tuniu.mob.ocsfaq.faq.entity;

/**
 * @author fanguotao
 * @create 2015/8/29
 * @description 问题实体类 接入百度unit faq功能 2022-09-05
 */
public class QuestionEntity {
    //问题Id
    private Integer id;
    //问题标题
    private String question;
    //问题答案
    private String answer;
    //问题关键字
    private String keyword;
    //分类Id
    private Integer categoryId;
    //排序字段
    private Integer sort;
    //创建时间
    private String createTime;
    //创建时间
    private String modifyTime;
    //是否删除
    private boolean deleteFlag;
    // 是否为百度unit faq
    private Integer unitFlag;
    // 百度unit 问答对id 区分不同技能下相同问题
    private Integer unitFaqId;

    // 是否推荐问题标记 0否 1是
    private Integer recommendFlag;
    // 关联问题id列表
    private String relationFaqIds;
    // 相似问题列表
    private String sameQuestions;
    // unit标签ID列表(逗号拼接)
    private String unitTagIds;
    // 变量答案
    private  String dynamicAnswer;
    // 启用变量答案
    private  Boolean  dynamicAnswerFlag;

    public String getUnitTagIds() {
        return unitTagIds;
    }

    public void setUnitTagIds(String unitTagIds) {
        this.unitTagIds = unitTagIds;
    }

    public Integer getRecommendFlag() {
        return recommendFlag;
    }

    public void setRecommendFlag(Integer recommendFlag) {
        this.recommendFlag = recommendFlag;
    }

    public String getRelationFaqIds() {
        return relationFaqIds;
    }

    public void setRelationFaqIds(String relationFaqIds) {
        this.relationFaqIds = relationFaqIds;
    }

    public String getSameQuestions() {
        return sameQuestions;
    }

    public void setSameQuestions(String sameQuestions) {
        this.sameQuestions = sameQuestions;
    }

    public Integer getUnitFaqId() {
        return unitFaqId;
    }

    public void setUnitFaqId(Integer unitFaqId) {
        this.unitFaqId = unitFaqId;
    }

    public Integer getUnitFlag() {
        return unitFlag;
    }

    public void setUnitFlag(Integer unitFlag) {
        this.unitFlag = unitFlag;
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
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public boolean isDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getDynamicAnswer() {
        return dynamicAnswer;
    }

    public void setDynamicAnswer(String dynamicAnswer) {
        this.dynamicAnswer = dynamicAnswer;
    }

    public Boolean getDynamicAnswerFlag() {
        return dynamicAnswerFlag;
    }

    public void setDynamicAnswerFlag(Boolean dynamicAnswerFlag) {
        this.dynamicAnswerFlag = dynamicAnswerFlag;
    }
}
