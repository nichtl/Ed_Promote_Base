package com.tuniu.mob.ocsfaq.faq.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class HyperParams {
    /**
     * 用于限定slu的解析范围，只在打上了指定tag的意图、或问答对的范围内执行slu
     */
    @JSONField(name = "slu_tags")
    private List<String>  sluTags;


    public HyperParams() {
    }

    public HyperParams(List<String> sluTags) {
        this.sluTags = sluTags;
    }

    public List<String> getSluTags() {
        return sluTags;
    }

    public void setSluTags(List<String> sluTags) {
        this.sluTags = sluTags;
    }
}
