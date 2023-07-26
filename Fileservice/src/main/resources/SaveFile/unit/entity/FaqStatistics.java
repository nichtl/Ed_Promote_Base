package com.tuniu.mob.ocsfaq.faq.entity;

import java.util.Date;

public class FaqStatistics {

    private Integer id;

    private Integer faqId;

    private Integer cYear;

    private Integer cMonth;

    private Integer cDay;

    private Date addTime;

    private Date updateTime;

    /**
     * 用户问题日志id  多个逗号拼接
     *
     * @mbg.generated
     */
    private String unitLogIds;

    /**
     * 该问题被匹配到的次数
     *
     * @mbg.generated
     */
    private Integer matchCount;

    /**
     * 数据源来源区分 1 客服侧 2 用户侧 默认客服侧
     *
     * @mbg.generated
     */
    private Integer sourceType;


    /**
     * remark 备注
     * @return
     */

    private String  remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFaqId() {
        return faqId;
    }

    public void setFaqId(Integer faqId) {
        this.faqId = faqId;
    }

    public Integer getcYear() {
        return cYear;
    }

    public void setcYear(Integer cYear) {
        this.cYear = cYear;
    }

    public Integer getcMonth() {
        return cMonth;
    }

    public void setcMonth(Integer cMonth) {
        this.cMonth = cMonth;
    }

    public Integer getcDay() {
        return cDay;
    }

    public void setcDay(Integer cDay) {
        this.cDay = cDay;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUnitLogIds() {
        return unitLogIds;
    }

    public void setUnitLogIds(String unitLogIds) {
        this.unitLogIds = unitLogIds == null ? null : unitLogIds.trim();
    }

    public Integer getMatchCount() {
        return matchCount;
    }

    public void setMatchCount(Integer matchCount) {
        this.matchCount = matchCount;
    }

    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", faqId=").append(faqId);
        sb.append(", cYear=").append(cYear);
        sb.append(", cMonth=").append(cMonth);
        sb.append(", cDay=").append(cDay);
        sb.append(", addTime=").append(addTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", unitLogIds=").append(unitLogIds);
        sb.append(", matchCount=").append(matchCount);
        sb.append(", sourceType=").append(sourceType);
        sb.append("]");
        return sb.toString();
    }

}
