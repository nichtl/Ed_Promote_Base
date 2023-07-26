package com.tuniu.mob.ocsfaq.faq.unit.service;

import com.tuniu.mob.ocsfaq.faq.entity.FaqDynamicParameter;

import java.util.Map;

public interface IDynamicBeforeProcessor {
    /**
     * @param element 当前的处理带有动态占位符的 element  (还未替换占位符)
     * @param param   顶层接口传入参数
     * @param parameter  当前取值的动态字段 信息
     * @return
     */
    public  void    dynamicPreHandle(org.jsoup.nodes.Element element , FaqDynamicParameter parameter, Map<String,Object> param)throws Exception;
}
