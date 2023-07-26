package com.tuniu.mob.ocsfaq.faq.unit.service.IDynamicPostProcessorImpl;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.tuniu.mob.ocsfaq.faq.entity.FaqDynamicParameter;
import com.tuniu.mob.ocsfaq.faq.unit.dto.OutParameter;
import com.tuniu.mob.ocsfaq.faq.unit.service.IDynamicPostProcessor;
import org.apache.commons.collections.map.HashedMap;
import org.jsoup.nodes.Element;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * @desc  针对一些出参做翻译 eg 1=是, 2=否
 */
@Service
@Order(1)
public class ParameterTranslateProcessor implements IDynamicPostProcessor {


    @Override
    public void dynamicPostHandle(Element element, OutParameter outParameterValue, FaqDynamicParameter parameter, Map<String, Object> param) throws Exception {
           if(outParameterValue==null || StrUtil.isBlank(outParameterValue.getValue())){return;}
            Map<String,String>  mappingDict  = new HashedMap();
             if(parameter!=null && StrUtil.isNotBlank( parameter.getParamDict() )){
                 // eg 1=是,2=否
                 List<String>  dictList= Splitter.on(",").splitToList( parameter.getParamDict() );

                 if(CollectionUtil.isNotEmpty(dictList)){
                     for (String dict: dictList) {
                         String [] dictArr = dict.split("=");
                         if(dictArr.length==2){
                             mappingDict.put(dictArr[0],dictArr[1]);
                         }

                     }
                 }

             }
             String oldVar = outParameterValue.getValue();
             if(mappingDict.containsKey(oldVar)){
                 outParameterValue.setValue(mappingDict.get(oldVar));
             }
    }
}
