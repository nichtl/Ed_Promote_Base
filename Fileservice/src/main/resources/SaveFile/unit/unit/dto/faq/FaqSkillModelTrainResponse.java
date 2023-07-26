package com.tuniu.mob.ocsfaq.faq.unit.dto.faq;

import com.tuniu.mob.ocsfaq.faq.unit.dto.Response;

public class FaqSkillModelTrainResponse extends Response {

    private FaqSkillModelTrainResult result;


    class FaqSkillModelTrainResult {
        private Long modelId;

        public Long getModelId() {
            return modelId;
        }

        public void setModelId(Long modelId) {
            this.modelId = modelId;
        }
    }


    public FaqSkillModelTrainResult getResult() {
        return result;
    }

    public void setResult(FaqSkillModelTrainResult result) {
        this.result = result;
    }
}
