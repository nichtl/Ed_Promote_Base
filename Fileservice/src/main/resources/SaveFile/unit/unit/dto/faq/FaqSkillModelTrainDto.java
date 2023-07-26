package com.tuniu.mob.ocsfaq.faq.unit.dto.faq;

public class FaqSkillModelTrainDto {

    private Long skillId;


    private String modelDesc;

    //开发者训练参数，json结构，⽬目前包含研发环境的选择，开发者可选择空闲研发环境，也不不可传该部分参数，系统会默认占⽤用⼀一个空闲研发环境，具体样例例如下
    private Object trainOption;


    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }

    public String getModelDesc() {
        return modelDesc;
    }

    public void setModelDesc(String modelDesc) {
        this.modelDesc = modelDesc;
    }

    public Object getTrainOption() {
        return trainOption;
    }

    public void setTrainOption(Object trainOption) {
        this.trainOption = trainOption;
    }

    public boolean valid() {

        if (skillId == null || skillId <= 0) {
            return false;
        }
        return true;
    }
}
