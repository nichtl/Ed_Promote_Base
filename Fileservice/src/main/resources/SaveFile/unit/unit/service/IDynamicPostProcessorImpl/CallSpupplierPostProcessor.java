package com.tuniu.mob.ocsfaq.faq.unit.service.IDynamicPostProcessorImpl;

import cn.hutool.core.codec.Base64Decoder;
import cn.hutool.core.codec.Base64Encoder;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tuniu.mob.boot.tsp.TspClient;
import com.tuniu.mob.ocsfaq.faq.entity.FaqDynamicParameter;
import com.tuniu.mob.ocsfaq.faq.exception.FAQException;
import com.tuniu.mob.ocsfaq.faq.unit.dto.BohDepartDateResponse;
import com.tuniu.mob.ocsfaq.faq.unit.dto.OutParameter;
import com.tuniu.mob.ocsfaq.faq.unit.dto.TofSalerInfoResponse;
import com.tuniu.mob.ocsfaq.faq.unit.enums.TspServiceEnum;
import com.tuniu.mob.ocsfaq.faq.unit.service.IDynamicPostProcessor;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


/**
 * @desc 针对供应商售前售后问题做追加 呼叫按钮
 */
@Service
@Order(2)
public class CallSpupplierPostProcessor implements IDynamicPostProcessor {

    public   final  static  String Supply_Pre="#{供应商分机电话.data.售前.电话号码}";

    public   final  static  String Supply_post="#{供应商分机电话.data.售后.电话号码}";

    private  final  static  String TOF_QUERY_SALER_INFO_URL = "http://public-api.tof.tuniu.org/tof/api/query/saler/querySalerById?";

//    @Autowired
//    private TspClient tspClient;

    @Override
    public void dynamicPostHandle(Element element, OutParameter outParameterValue, FaqDynamicParameter parameter, Map<String, Object> param) throws Exception {

        Attributes attributes =  element.attributes();

        if(element ==null ||  attributes.isEmpty()){return;}


        final  String finalDynamicPlaceHolder = attributes.get("dynamic_placeholder");

        // tuniucom://callfun?param1=318052008793&param2=1101123456789123456
        if( StrUtil.isNotBlank(finalDynamicPlaceHolder) &&finalDynamicPlaceHolder.contains(Supply_Pre) || finalDynamicPlaceHolder.contains(Supply_post) ){
            String num_prefix="4001619999";
            String num_suffix=outParameterValue.getValue();
            //  element.append("<p>"+"分机号: "+num_suffix+ "</p>"+"<a href=\""+"tuniucom://callfun?param1=3"+num_prefix+"&param2=11898765432112345678\">呼叫"+"</a>");
            String encoderNumber  ="0201"+num_suffix;
            String workNum = (String) param.getOrDefault("workNum","");

            if(StrUtil.isBlank(workNum) && param.containsKey("salerId")){
                Map<String,Object> requestParam = new HashMap<>();
                requestParam.put("id",param.get("salerId"));
                String getSalerInfoUrl = TOF_QUERY_SALER_INFO_URL+ Base64Encoder.encode(JSON.toJSONString(requestParam));
                String tofSalerInfoResult =HttpUtil.get(getSalerInfoUrl,2000);

                TofSalerInfoResponse tofSalerInfoResponse = null;

                if(StrUtil.isNotBlank(tofSalerInfoResult)){
                    tofSalerInfoResult  = Base64Decoder.decodeStr(tofSalerInfoResult);
                    tofSalerInfoResponse =  JSON.parseObject(tofSalerInfoResult,TofSalerInfoResponse.class);
                }

                if(tofSalerInfoResponse!=null && tofSalerInfoResponse.getData()!=null && StrUtil.isNotBlank(tofSalerInfoResponse.getData().getWorkNum())){
                    workNum = tofSalerInfoResponse.getData().getWorkNum();
                }

            }

            if(StrUtil.isNotBlank(workNum) && StrUtil.isNotBlank(num_suffix)){
                element.append("<p>" + "分机号: " + num_suffix + "</p>" + "<a href=\"" + "javascript:void(0);" + "\"  onclick=\"" + "window.parent.storeCallOut(" +"'" +encoderNumber+"'" + "," +"'"+workNum+"'" + ")" + "\">呼叫" + "</a>");
            }else{
                throw  new FAQException("CallSpupplierPostProcessor.dynamicPostHandle error workNum="+workNum+",encoderNumber="+encoderNumber);
            }

        }
    }
}
