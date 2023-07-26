package com.tuniu.mob.ocsfaq.faq.unit.dto;

public class RobotSkill {
    /**
     * 技能ID
     */
    private Long skillId;
    //技能名称
    private String skillName;
    //技能描述
    private String skillDesc;
    //技能类别： user(开发者⾃自定义)、 built(预置)
    private String skillCategory;
    //技能类型： dialogue(对话)、 faq(问答)、 ddqa(⽂文档问答)、kbqa（表格问答）
    private String skillType;
    //技能状态 包括： new(新建)、 starting(启动中)、 failed(模型⽣生
    //效失败)、 loading(模型⽣生效中)、 running(运⾏行行中)、stopped(停⽌止)
    private String skillStatus;
    //技能中当前的模型版本
    private String skillVersion;
    //创建时间
    private String createTime;

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getSkillDesc() {
        return skillDesc;
    }

    public void setSkillDesc(String skillDesc) {
        this.skillDesc = skillDesc;
    }

    public String getSkillCategory() {
        return skillCategory;
    }

    public void setSkillCategory(String skillCategory) {
        this.skillCategory = skillCategory;
    }

    public String getSkillType() {
        return skillType;
    }

    public void setSkillType(String skillType) {
        this.skillType = skillType;
    }

    public String getSkillStatus() {
        return skillStatus;
    }

    public void setSkillStatus(String skillStatus) {
        this.skillStatus = skillStatus;
    }

    public String getSkillVersion() {
        return skillVersion;
    }

    public void setSkillVersion(String skillVersion) {
        this.skillVersion = skillVersion;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
