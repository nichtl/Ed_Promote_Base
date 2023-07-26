package com.tuniu.mob.ocsfaq.faq.unit.service;

import java.util.Map;

public    interface IDynamicFaqService  {

    /**
     * @desc 将带有动态参数的富文本取值替换 出现异常是上层直接使用默认的文本答案做处理
     * @param content 带有动态占位符的html富文本
     * @param param 顶层接口传入参数
     * @return  转换后的富文本
     */
    String  convertDynamicContent(String content, Map<String, Object> param) throws  Exception;
}
