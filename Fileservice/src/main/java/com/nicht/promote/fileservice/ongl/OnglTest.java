package com.nicht.promote.fileservice.ongl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import ognl.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Date 2022/11/21
 */
public class OnglTest {

    public static void main(String[] args) throws OgnlException {

        System.out.println(StrUtil.isNumeric("2289"));

        String res = "{\n" +
                "              success:true,\n" +
                "              errorCode:10000,\n" +
                "              msg:\"success\",\n" +
                "              data:{\n" +
                "                  hotelInfo:{\n" +
                "                      tel:115824914901\n" +
                "                  }\n" +
                "              }\n" +
                "         \n" +
                "          }";
        System.out.println(res);

        String   jsoonStr = "{\n" +
                "\t\t  \"success\": true,\n" +
                "\t\t  \"msg\": null,\n" +
                "\t\t  \"errorCode\": 1010000,\n" +
                "\t\t  \"data\": {\n" +
                "\t\t\"productId\": 210030257,\n" +
                "\t\t\"marketChannelCode\":100,\n" +
                "\t\t    \"requestSourceCode\":3,\n" +
                "\t\t\"d\":{\"d1\":1,\"d2\":\"\"},\n" +
                "\t\t\"data\":[{\n" +
                "\t\t   \"key\":1,\n" +
                "\t\t   \"value\":2\n" +
                "\t\t}],\n" +
                "\t\t\"cc\":{\n" +
                "\t\t   \"c1\":22,\"c2\":89\n" +
                "\t\t  }\n" +
                "\t\t}\n" +
                "\t\t}";


        Map<String,Object> param = new HashMap<>();
        param = JSON.parseObject(jsoonStr,Map.class);

        OgnlContext ognlContext = (OgnlContext) Ognl.createDefaultContext(param,new DefaultMemberAccess(true),new DefaultClassResolver(),new DefaultTypeConverter());
        ognlContext.setRoot(param);
        ognlContext.put("params",param);
        Object outParam = "@java.lang.Object  val1=  #params.get(\"data\"); if(val1 !=null) { val1.get(\"cc\")} else{return null;} ";

        Object ans = Ognl.getValue(Ognl.parseExpression("#params.get('data').get('cc').get('c1') "), ognlContext, ognlContext.getRoot());

        String express = "@cn.hutool.core.convert.Convert@convert(@java.lang.Long.TYPE,\"222\")";
        Object ss = Ognl.getValue(Ognl.parseExpression(express) , ognlContext, ognlContext.getRoot());
        System.out.println("实例方法执行： " + ss);


        System.out.println(1);
    }

  //  OgnlContext onglContext = Ognl.createDefaultContext()
}
