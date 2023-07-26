package com.tuniu.mob.ocsfaq.faq.unit.service;

import com.tuniu.mob.ocsfaq.faq.vo.ParamParserNode;
import ognl.OgnlContext;

public interface IEvaluatorProcessor {

    /**
     *
     * @param node
     * @param context
     * @return
     */
    public  String eval(ParamParserNode node , OgnlContext context);


}
