package com.tuniu.mob.ocsfaq.faq.unit.dto.faq;

public class FaqSkillModelDeleteDto {
    // 处于初始化和训练中的模型不不可删除；
    // 已绑定研发环境和⽣生产环境的模型不不可删除
    private Long skillId;


    private Long modelId;


    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public boolean valid() {

        if (skillId == null || skillId <= 0 || modelId == null || modelId <= 0) {
            return false;
        }
        return true;
    }

}
