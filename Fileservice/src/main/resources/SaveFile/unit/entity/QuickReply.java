package com.tuniu.mob.ocsfaq.faq.entity;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class QuickReply  extends  PageEntity{
    private Integer id;

    /**
     * 按照供应商id区分
     *
     * @mbg.generated
     */
    private String userId;

    /**
     * 快捷回复分类
     *
     * @mbg.generated
     */
    private Integer replyCategoryId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    /**
     * 快捷回复内容
     *
     * @mbg.generated
     */
    private String replyContent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getReplyCategoryId() {
        return replyCategoryId;
    }

    public void setReplyCategoryId(Integer replyCategoryId) {
        this.replyCategoryId = replyCategoryId;
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

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent == null ? null : replyContent.trim();
    }

    public   boolean addValid(){

        if(StrUtil.isBlank(this.getReplyContent()) || StrUtil.isBlank(this.getUserId())  || this.getReplyCategoryId() == null){
            return  false;
        }
        return  true;

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", replyCategoryId=").append(replyCategoryId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", replyContent=").append(replyContent);
        sb.append("]");
        return sb.toString();
    }
}
