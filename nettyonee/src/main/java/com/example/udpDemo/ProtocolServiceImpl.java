//package com.example.udpDemo;
//
//import lombok.extern.slf4j.Slf4j;
//import org.jeecg.modules.gateway.modules.entity.GatewayParam;
//import org.jeecg.modules.gateway.modules.service.IGatewayParamService;
//import org.jeecg.modules.gateway.protocolimpl.ProtocolService;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import java.util.List;
//
//
//@Slf4j
//@Service
//public class ProtocolServiceImpl implements ProtocolService {
//    @Resource
//    IGatewayParamService gatewayParamService;
//
//    @Override
//    public Boolean initLink() throws Exception {
//        return ProtocolService.super.initLink();
//    }
//
//    @Override
//    public List<GatewayParam> acquisitionParameter(List<GatewayParam> params) {
////        String json = HttpUtil.get(meikuangAPI,3000);
////        JSONObject responseJSON = JSONObject.parseObject(json);
////        params.parallelStream().forEach(param -> param.setParamValue(responseJSON.getString(param.getKey())));
//        return params;
//
//    }
//
//
//    @Override
//    public Boolean controlParameter(List<GatewayParam> params) {
//        log.info("无南向控制接口");
//        return true;
//    }
//
//}
