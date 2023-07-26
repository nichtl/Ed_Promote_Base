package com.tuniu.mob.ocsfaq.faq.unit.dto.faq;


import com.tuniu.mob.ocsfaq.faq.unit.dto.Result;

import java.util.List;

/**
 * @Description
 * @Date 2022/8/31
 */
public class FaqSkillModelListResult extends Result {

    private List<FaqSkillModel> models;

    public List<FaqSkillModel> getModels() {
        return models;
    }

    public void setModels(List<FaqSkillModel> models) {
        this.models = models;
    }
}
