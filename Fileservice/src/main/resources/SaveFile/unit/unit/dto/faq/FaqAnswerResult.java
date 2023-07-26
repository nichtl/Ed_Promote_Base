package com.tuniu.mob.ocsfaq.faq.unit.dto.faq;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;
import java.util.Map;

public class FaqAnswerResult {
    private String version;

    /**
     * "context":{
     * "SYS_PRESUMED_SKILLS":[1226827,1225730],
     * "SYS_VARS":{}
     * }
     */
    private Map<String, Object> context;


    private String timestamp;

    @JSONField(name = "service_id")
    private String serviceId;

    @JSONField(name = "session_id")
    private String sessionId;

    @JSONField(name = "log_id")
    private String logId;

    @JSONField(name = "ref_id")
    private String refId;


    private List<FaqAnswerInnerResponse> responses;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Map<String, Object> getContext() {
        return context;
    }

    public void setContext(Map<String, Object> context) {
        this.context = context;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public List<FaqAnswerInnerResponse> getResponses() {
        return responses;
    }

    public void setResponses(List<FaqAnswerInnerResponse> responses) {
        this.responses = responses;
    }
}









