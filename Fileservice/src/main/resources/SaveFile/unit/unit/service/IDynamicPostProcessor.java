package com.tuniu.mob.ocsfaq.faq.unit.service;

import com.tuniu.mob.ocsfaq.faq.entity.FaqDynamicParameter;
import com.tuniu.mob.ocsfaq.faq.unit.dto.OutParameter;

import java.util.Map;

public interface IDynamicPostProcessor {

    /**
     * @param element 当前的处理带有动态占位符的 element (已经替换过占位符)
     * @param param 顶层接口传入参数
     * @param outParameterValue  当前待替换的出参值 全部都转为字符形式 如需要可以自己在转回去
     * @param parameter  当前取值的动态字段 信息
     * @return
     */
    public  void    dynamicPostHandle(org.jsoup.nodes.Element element, OutParameter outParameterValue, FaqDynamicParameter parameter, Map<String,Object> param)throws Exception;
}
