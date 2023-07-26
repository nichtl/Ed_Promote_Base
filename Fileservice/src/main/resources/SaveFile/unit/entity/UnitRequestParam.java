package com.tuniu.mob.ocsfaq.faq.entity;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.annotation.JSONField;

public class UnitRequestParam {
    @JSONField(name = "terminal_id")
    private String terminalId;

    private String query;

    @JSONField(name="hyper_params")
    private HyperParams hyperParams;


    public HyperParams getHyperParams() {
        return hyperParams;
    }

    public void setHyperParams(HyperParams hyperParams) {
        this.hyperParams = hyperParams;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }


    public boolean valid() {
        return StrUtil.isNotBlank(terminalId) || StrUtil.isNotBlank(query);
    }
}

