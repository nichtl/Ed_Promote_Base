package com.tuniu.mob.ocsfaq.faq.unit.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.codec.Base64Decoder;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.RateLimiter;
import com.tuniu.mob.ocsfaq.biz.leavemessage.constant.Constant;
import com.tuniu.mob.ocsfaq.entity.UserEntity;
import com.tuniu.mob.ocsfaq.enumeration.Version;
import com.tuniu.mob.ocsfaq.faq.entity.CallOutLog;
import com.tuniu.mob.ocsfaq.faq.entity.HyperParams;
import com.tuniu.mob.ocsfaq.faq.entity.UnitRequest;
import com.tuniu.mob.ocsfaq.faq.entity.UnitRequestParam;
import com.tuniu.mob.ocsfaq.faq.exception.ClientException;
import com.tuniu.mob.ocsfaq.faq.pebbleService.entity.*;
import com.tuniu.mob.ocsfaq.faq.rasa.dto.RasaResponse;
import com.tuniu.mob.ocsfaq.faq.rasa.dto.RasaTalkDto;
import com.tuniu.mob.ocsfaq.faq.unit.dto.BohDepartDate;
import com.tuniu.mob.ocsfaq.faq.unit.dto.BohDepartDateResponse;
import com.tuniu.mob.ocsfaq.faq.unit.dto.BohDepartDateResponseData;
import com.tuniu.mob.ocsfaq.faq.unit.dto.PageDto;
import com.tuniu.mob.ocsfaq.faq.unit.dto.faq.*;
import com.tuniu.mob.ocsfaq.faq.unit.enums.UnitEnum;
import com.tuniu.mob.ocsfaq.faq.unit.enums.UnitResponseEnum;
import com.tuniu.mob.ocsfaq.utils.StringUtils;
import org.apache.commons.collections.map.HashedMap;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.select.Elements;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class UnitUtils {

    public static void checkPageDto(PageDto pageDto) {
        if (pageDto.getPageNo() == null || pageDto.getPageNo() <= 0) {
            pageDto.setPageNo(Constant.PAGE_NO);
        }
        if (pageDto.getPageSize() == null || pageDto.getPageSize() <= 0) {
            pageDto.setPageSize(Constant.PAGE_SIZE);
        }
    }

    public static String getAccessToken(String tokenUrl, String key, String secret) {
        if (StrUtil.isBlank(tokenUrl) || StrUtil.isBlank(key) || StrUtil.isBlank(secret)) {
            return null;
        }
        Map<String, Object> params = new HashMap<>();
        params.put("grant_type", "client_credentials");
        params.put("client_id", key);
        params.put("client_secret", secret);


        String response = cn.hutool.http.HttpUtil.post(tokenUrl, params, 20000);
        Map<String, Object> resultMap = JSON.parseObject(response, Map.class);
        return (String) resultMap.getOrDefault("access_token", "");

    }


    public static String utterance(String talkUrl, UnitRequest request, String accessToken) {
        if (StrUtil.isBlank(accessToken)) {
            return null;
        }
        // 请求URL
        //String talkUrl = "https://aip.baidubce.com/rpc/2.0/unit/service/v3/chat";
        try {

            // 请求参数
            String params = "{\"version\":\"3.0\",\"service_id\":\"S73657\",\"session_id\":\"chat-session-id-1661409885599-4714244950d0424b8b5616e259c01d99\",\"log_id\":\"2\",\"request\":{\"terminal_id\":\"12331\",\"query\":\"测试\"}}";
            String result = HttpUtil.post(talkUrl, accessToken, "application/json", JSON.toJSONString(request));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public void limit() {

        RateLimiter limiter = RateLimiter.create(10.0);


    }
    public static  List<String>  getPlayTopicByPrdId(Integer productId){

        List<String>  topic = Lists.newArrayList();
        if(productId == null || productId<=0){
            return  topic;
        }
        Map<String,Object> param = new HashMap<>();
        param.put("ids",Arrays.asList(productId.toString()));
        param.put("queryFields",Arrays.asList("play_travelTopicCombination",
                "play_jiaotongCombination",
                "play_chanpinCombination","play_xianluCombination","suitCrowdsCombination"));
        param.put("tagQuery",true);
        param.put("terminalType",3);
        param.put("priceLimit",false);

        String  encodeParam  = JSON.toJSONString(param);

        HttpResponse response  = cn.hutool.http.HttpUtil.createGet(UnitEnum.PRODUCT_SEARCH.getUrlSuffix())
                .body(cn.hutool.core.codec.Base64.encode(encodeParam,StandardCharsets.UTF_8)).timeout(3000).execute();
        String res = cn.hutool.core.codec.Base64.decodeStr(response.body(), StandardCharsets.UTF_8);

        List<ProductCondition> conditions =Lists.newArrayList();
        if(StrUtil.isNotBlank(res)){

            Map<String, Object>  result =  JSON.parseObject(res,Map.class);

            if((boolean)result.getOrDefault("success",false)){
                JSONArray data = (JSONArray) result.get("data");
                if(data!=null &&  data.getJSONObject(0)!=null){
                    JSONObject realData = data.getJSONObject(0);
                    conditions = JSON.parseArray(JSON.toJSONString( realData.getJSONArray("customConditionInfo")),ProductCondition.class);
                }
            }

        }

        if(CollectionUtil.isNotEmpty(conditions)){
            conditions.forEach(e->{
                if(CollectionUtil.isNotEmpty(e.getConditionChild())){
                    e.getConditionChild().forEach(child->{
                        topic.add(child.getConditionName());
                    });
                }
            });
        }

        return  topic;



    }
    public static boolean dateStrIsEqueal(String d1,String d2){

        try {
            Date date1 = DateUtil.parse(d1, DatePattern.NORM_DATE_PATTERN);
            Date date2 = DateUtil.parse(d2, DatePattern.NORM_DATE_PATTERN);
            int r = DateUtil.compare(date1, date2, DatePattern.NORM_DATE_PATTERN);
            return  r==0;
        } catch (Exception e){
            //logger.error("UnitDynamicController.dateStrIsEqueal.errorInfo={},d1={},d2={}",e.getMessage(),d1,d2);
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
//        CallOutLog log = new CallOutLog();
//
//        String  encode64Str = "eyJjYWxsT3V0VXJsIjoidGVzdCIsImVuY29kZU51bWJlciI6IjAwMjAyOTAwMSIsIndvcmtOdW0iOjAsIm91dGJvdW5kVHlwZSI6MSwiY2FsbFJlc3BvbnNlIjoiIiwiY3VycmVudFRhbGtQYWdlUGFyYW0iOiJleUoxYVdRaU9pSXpNalUyTlNJc0luUnZhMlZ1SWpwdWRXeHNMQ0p1YVdOcmJtRnRaU0k2SXVXK2tPV0prVGdpTENKeUlqb3dMakkzT1RVNE9ESTJNemN4TnpZd056VXNJbTl5WkdWeVNXUWlPakV5TlRBNE16VTFOVEFzSW5KbGMyOTFjbU5sU1dRaU9pSXhOalV4TWpRM01UUTJJaXdpY21WemIzVnlZMlZVZVhCbElqb2lNamNpTENKeVpYTnZkWEpqWlU1aGJXVWlPaUk4NXBpRzVwaU9LK1drcCtlUWhpdmt1TDNtc1ovbGo0enBvNTQyNXBlbDVyaTRQdVM0Z09TN3QrV0ZxT1dRcXpEb2g2cm90TG5rdUtqb3NhcmxqWTdwaFpMbHVwZmt1S2pubjdQbW5wY3I1NDZKNmI2WjZadXE1Ykd4SytlOWtlZTZvdWVRaHVhRHMrbUNwaXZtdExIbXRiZm1sNFhtaTQza3VLamt1TDNtc0xUcGg1SG1zcGtyNXBtdjVZeTY1NVMxNTVPMjZMMm1LK1dLcU9pOXB1aS9sT2FZaGlJc0ltRm5aVzVqZVVsa0lqb2lNakkwTURVaUxDSmhaMlZ1WTNsT1lXMWxJam9pS09XTm9PUzlqU25sbzducGdKVGxnWWZtbko4aUxDSnpZV3hsY2tsa0lqb2lNekkxTmpVaUxDSjFjMlZ5VG1GdFpTSTZJblIxYm1sMUxUTXlOVFkxSWl3aVpHVndZWEowZFhKbFJHRjBaU0k2SWpJd01qTXRNRGN0TVRZaUxDSmhaMlZ1WTNsUWNtVldhWEowZFdGc1RuVnRZbVZ5Y3lJNkl1YWRqdWlBbFNBNklEVXdNRFU0UEdKeVBpSXNJbUZuWlc1amVVRm1kR1Z5Vm1seWRIVmhiRTUxYldKbGNuTWlPaUxtblk3b2dKVWdPaUExTURBMU9EeGljajRpTENKeWIyOXRTV1FpT2pFM056WTBNREo5In0=";
//        Map<String,Object>   logParam =  JSON.parseObject(Base64Decoder.decode(encode64Str),Map.class);
//        log.setWorkNum(String.valueOf(logParam.getOrDefault("workNum",0)));
//        log.setCallParam((String) logParam.getOrDefault("callOutUrl",""));
//
//        Map<String, Object>   currentPageParam = new HashedMap();
//        if(logParam.containsKey("currentTalkPageParam")
//                && StrUtil.isNotBlank((String)logParam.getOrDefault("currentTalkPageParam",null))){
//            String base64Str = (String) logParam.getOrDefault("currentTalkPageParam","");
//            String decodeStr = Base64Decoder.decodeStr(base64Str);
//            currentPageParam  = JSON.parseObject(decodeStr,Map.class);
//
//        }
//
//        log.setSalerId(Integer.parseInt( (String) currentPageParam.getOrDefault("salerId","0") ));
//        log.setOrderId((Integer) currentPageParam.getOrDefault("orderId",0));
//        currentPageParam.getOrDefault("1",1);
//        logParam.put("currentTalkPageParam",currentPageParam);
//        System.out.println(JSON.toJSONString(logParam));
        //        String accessToken = getAccessToken("https://aip.baidubce.com/oauth/2.0/token", "bldYtfwbr4C1gPPbwVYTW7ms", "F1NW7VuAVH3couW2OHas2qxED6xbjIZd");
//        String talkUrl = "https://unit.su.baidubce.com/rpc/2.0/unit/service/v3/chat";
//        String searchUrl = "https://unit.su.baidubce.com/rpc/2.0/unit/v3/faqskill/faqPair/search";
//        String updateUrl = "http://api-p.tuniu.com/ocsfaq/manager/unit/updateCustomerQuestion";
        RasaTalkDto talkDto = new RasaTalkDto();
        talkDto.setText("供应商");
        talkDto.setSender("tester");
         String result = cn.hutool.http.HttpUtil.post("http://aiss-rqa.api.tuniu.org/webhooks/rasaqa/webhook",JSON.toJSONString(talkDto),30000);
        RasaResponse rasaResponse = new RasaResponse();
        if(StrUtil.isNotBlank(result)){
            rasaResponse = JSON.parseObject(result, RasaResponse.class);

        }

        rasaResponse.getCustom();

        //        try {
//            String getFaqListUrl = "http://api-p.tuniu.com/ocsfaq/manager/unit/customer/all/question?resourceType=27&pageSize=100&pageNum=1";
//            String faqListStr = cn.hutool.http.HttpUtil.get(getFaqListUrl);
//            CustomerQuestionResponse faqResponse = JSON.parseObject(faqListStr, CustomerQuestionResponse.class);
//
//            Map<String, Object> param = new HashMap<>();
//
//            param.put("skillId", 1253029);
//            param.put("pageNo", 1);
//            param.put("pageSize", 10);
//            if (CollectionUtil.isNotEmpty(faqResponse.getData())) {
//
//                for (CustomerQuestionVo e : faqResponse.getData()) {
//                    param.put("searchKey", e.getQuestion());
//
//                    String res = HttpUtil.post(searchUrl, accessToken, "application/json", JSON.toJSONString(param));
//
//                    Map<String, Object> resMap = JSON.parseObject(res, Map.class);
//
//                    if (resMap.get("result") != null) {
//                        JSONObject obj1 = (JSONObject) resMap.get("result");
//                        List<FaqPair> faqPairs = JSONArray.parseArray(obj1.getJSONArray("faqPairs").toJSONString(), FaqPair.class);
//                        if (CollectionUtil.isNotEmpty(faqPairs)) {
//                            if(faqPairs.size()>1){
//                                faqPairs = faqPairs.stream().sorted(Comparator.comparingLong(FaqPair::getFaqId).reversed()).collect(Collectors.toList());
//                            }
//
//                            Long unitFaqId = faqPairs.get(0).getFaqId();
//
//                            Map<String, Object> updateParam = new HashMap<>();
//                            updateParam.put("id", e.getId());
//                            updateParam.put("unitFaqId", unitFaqId);
//                             String ct =   cn.hutool.http.HttpUtil.get(updateUrl,updateParam);
//                             System.out.println(ct);
//                        }
//                    }
//                }
//
//
//            }
//        } catch (Exception e){
//            System.out.println(e.getMessage());
//        }









             //请求参数
//            String params = "{\"version\":\"3.0\",\"service_id\":\"S83292\",\"session_id\":\"chat-session-id-1661409885599-4714244950d0424b8b5616e259c01d99\",\"log_id\":\"4\",\"request\":{\"terminal_id\":\"12331\",\"query\":\"测试\"}}";
//            String result = HttpUtil.post(talkUrl, accessToken, "application/json", params);
//            UnitRequest request = new UnitRequest();
//            request.setVersion("3.0");
//            request.setServiceId("S76775");
//            request.setSessionId("chat-session-id-1661409885599-4714244950d0424b8b5616e259c01d99");
//            request.setLogId("5");
//
//            UnitRequestParam unitRequestParam = new UnitRequestParam();
//            unitRequestParam.setTerminalId("12331");
//            unitRequestParam.setQuery("当地天气");
//              HyperParams hyperParams = new HyperParams();
//                 hyperParams.setSluTags(Arrays.asList("供应商跟团资源"));
//            request.setRequest(unitRequestParam);
//
//
//            String result2 = HttpUtil.post(talkUrl, accessToken, "application/json", JSON.toJSONString(request));
//
//            FaqAnswerResponse faqAnswerResponse = JSON.parseObject(result, FaqAnswerResponse.class);
//
//            System.out.println(DateUtil.now());
//            System.out.println(result2);

//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//       String authUrl = "http://public-api.bj.mob.tuniu.org/iapi/appserver/view/authXmppUser?";
//
//            UserAuthRequest request = new UserAuthRequest();
//            request.setSessionID("4af9290993271fd0747adacc67295cd2_");
//            request.setUserId("");
//              HttpCtrlVO ctv =    new HttpCtrlVO();
//               ctv.setConnectTimeOut(1000);
//               ctv.setConnectionRequestTimeOut(1000);
//               ctv.setSocketTimeOut(1000);
//               ctv.setStaleConnectionCheckEnabled(false);
//            String tg =  HttpClientManager.get("http://public-api.bj.mob.tuniu.org/iapi/appserver/view/" + "authXmppUser", request, new TypeReference<HttpResponseVO<UserAuthData>>() {
//            }.getType(),ctv );
//
//        System.out.println(tg);
//
//
//        Map<String,Object>  param1=Maps.newHashMap();
//        param1.put("sessionID", "4af9290993271fd0747adacc67295cd2_");
//        String res =  HttpAsyncClientUtil.getParamBase64ResultBase64(authUrl, JSON.toJSONString(param1), 2);
       // System.out.println( res);

//        UserEntity userEntity=null;
//        try {
//            Map<String, Object> params=new HashMap<String, Object>();
//            params.put("ssosessionid","4af9290993271fd0747adacc67295cd2_");
//            String resw =   cn.hutool.http.HttpUtil.get("http://xchat.tuniu.com/iapi/webApi/user",params);
//            System.out.println(resw);
//        } catch (Exception e) {
//            throw new ClientException("校验登录信息失败",e);
//        }

//        String url = "https://aip.baidubce.com/rpc/2.0/unit/v3/";
//        Map<String,Object> params = new HashMap<>();
//        params.put("skillId",1225730);
//        params.put("pageNo",1);
//        params.put("pageSize",100);
//        String result = HttpUtil.post(url , accessToken, "application/json", JSON.toJSONString(params));
//        PageDto pageDto = new PageDto();
//        pageDto.setPageNo(0);
//        pageDto.setPageSize(10);
//
//        String result= HttpUtil.post(url+ UnitEnum.ROBOT_LIST.getUrlSuffix(),accessToken,JSON.toJSONString(pageDto));

//        // test unit api
//        FaqSkillTagListDto param = new FaqSkillTagListDto();
//        param.setSkillId(1225730l);
//        param.setPageNo(1);
//        param.setPageSize(100);
//        param.setFaqIds(Lists.newArrayList(2717793l,2440412l));
//        String requestStr = HttpUtil.post("https://aip.baidubce.com/rpc/2.0/unit/v3/tag/list", accessToken, JSON.toJSONString(param));
//        System.out.println("requestStr" +":"+requestStr);
//        String ds ="[{\"id\":808,\"name\":\"跟团订单咨询\",\"parentId\":\"0\",\"icon\":\"http://m.tuniucdn.com/fb2/t1/G7/M00/19/8C/CkC9r2NSMKiIWlokAAAZt9Arx38AAKkHgP_1-MAABnP948.png\",\"unitServiceId\":\"S76434\",\"productCategoryName\":\"\",\"children\":null},{\"id\":816,\"name\":\"门票\",\"parentId\":\"0\",\"icon\":\"http://m.tuniucdn.com/fb2/t1/G7/M00/19/88/CkC9r2NNAOuIcGDsAAAZFv5mPaYAAKjswP_v_cAABku352.png\",\"unitServiceId\":\"S76775\",\"productCategoryName\":\"\",\"children\":null},{\"id\":820,\"name\":\"门票订单咨询\",\"parentId\":\"0\",\"icon\":\"http://m.tuniucdn.com/fb2/t1/G7/M00/19/88/CkC9r2NNAOuIcGDsAAAZFv5mPaYAAKjswP_v_cAABku352.png\",\"unitServiceId\":\"S76750\",\"productCategoryName\":\"\",\"children\":null},{\"id\":822,\"name\":\"国内机票\",\"parentId\":\"0\",\"icon\":\"http://m.tuniucdn.com/fb2/t1/G7/M00/19/A0/CkC9Y2NZ4vyIYcN2AAAbqnP80pEAAKmEwP_2X4AABvC996.png\",\"unitServiceId\":\"S76801\",\"productCategoryName\":\"\",\"children\":null},{\"id\":824,\"name\":\"国际.港澳台机票\",\"parentId\":\"0\",\"icon\":\"http://m.tuniucdn.com/fb2/t1/G7/M00/19/A0/CkC9r2NZ40iIPv-WAAAcn6KD7L8AAKmFwGkLX4AABy3056.png\",\"unitServiceId\":\"S76802\",\"productCategoryName\":\"\",\"children\":null},{\"id\":825,\"name\":\"国内酒店\",\"parentId\":\"0\",\"icon\":\"http://m.tuniucdn.com/fb2/t1/G7/M00/19/A0/CkC9r2NZ44uIW5UXAAAbtTl0VmkAAKmFwGnlFkAABvN059.png\",\"unitServiceId\":\"S76803\",\"productCategoryName\":\"\",\"children\":null},{\"id\":828,\"name\":\"出境.港澳台酒店\",\"parentId\":\"0\",\"icon\":\"http://m.tuniucdn.com/fb2/t1/G7/M00/19/A0/CkC9r2NZ48-Ifll4AAAcqovUOhoAAKmFwHYKs0AABzC643.png\",\"unitServiceId\":\"S76805\",\"productCategoryName\":\"\",\"children\":null},{\"id\":830,\"name\":\"火车票\",\"parentId\":\"0\",\"icon\":\"http://m.tuniucdn.com/fb2/t1/G7/M00/19/A0/CkC9r2NZ5CyIZoPUAAAWoNbKE6kAAKmFQP_5rIAABa4358.png\",\"unitServiceId\":\"S76806\",\"productCategoryName\":\"\",\"children\":null},{\"id\":831,\"name\":\"国内租车\",\"parentId\":\"0\",\"icon\":\"http://m.tuniucdn.com/fb2/t1/G7/M00/19/A0/CkC9r2NZ5G2IcN82AAAVjsQBDHsAAKmEQP_5S0AABWm249.png\",\"unitServiceId\":\"S76856\",\"productCategoryName\":\"\",\"children\":null},{\"id\":832,\"name\":\"跟团游\",\"parentId\":\"0\",\"icon\":\"http://m.tuniucdn.com/fb2/t1/G7/M00/19/8C/CkC9r2NSMKiIWlokAAAZt9Arx38AAKkHgP_1-MAABnP948.png\",\"unitServiceId\":\"S76809\",\"productCategoryName\":\"\",\"children\":null},{\"id\":836,\"name\":\"酒景自驾游\",\"parentId\":\"0\",\"icon\":\"http://m.tuniucdn.com/fb2/t1/G7/M00/19/A0/CkC9r2NZ5SWIfzoRAAAYbaJukuEAAKmFwM0BmEAABiF641.png\",\"unitServiceId\":\"S76810\",\"productCategoryName\":\"\",\"children\":null},{\"id\":837,\"name\":\"自由行.旅拍\",\"parentId\":\"0\",\"icon\":\"http://m.tuniucdn.com/fb2/t1/G7/M00/19/A0/CkC9Y2NZ5XSIHLWCAAAWg7IZqTEAAKmFwOH1VAAABab328.png\",\"unitServiceId\":\"S76811\",\"productCategoryName\":\"\",\"children\":null},{\"id\":838,\"name\":\"签证\",\"parentId\":\"0\",\"icon\":\"http://m.tuniucdn.com/fb2/t1/G7/M00/19/A0/CkC9Y2NZ5buIXBhYAAAYLT1pSPcAAKmFwOM2DQAABhF410.png\",\"unitServiceId\":\"S76808\",\"productCategoryName\":\"\",\"children\":null},{\"id\":839,\"name\":\"邮轮游\",\"parentId\":\"0\",\"icon\":\"http://m.tuniucdn.com/fb2/t1/G7/M00/19/A0/CkC9Y2NZ5jyIKIAgAAAY81jmUSEAAKmFwOVVY0AABkL361.png\",\"unitServiceId\":\"S76812\",\"productCategoryName\":\"\",\"children\":null}]";
//
//        List<FirstCategoryVo>  categoryVos = JSON.parseArray(ds,FirstCategoryVo.class);
//        Map<String,List<FirstCategoryVo>>  menuGroup =categoryVos.stream()
//                .peek(e->{if(StrUtil.isBlank(e.getProductCategoryName())) {e.setProductCategoryName("其他");} })
//                .collect(Collectors.groupingBy(FirstCategoryVo::getProductCategoryName));
//        System.out.println("1");


        // requestStr:{"result":{"totalCount":4,"currentPage":1,"tags":[{"tagId":22848,"faqCount":0,"tagName":"crmTest","priority":-4},{"tagId":22847,"faqCount":0,"tagName":"crm标签","priority":-3},{"tagId":21876,"faqCount":2,"tagName":"交通","priority":-2},{"tagId":20707,"faqCount":1,"tagName":"标签","priority":-1}]},"error_msg":"ok","error_code":0}
        // requestStr:{"result":{"totalCount":4,"currentPage":1,"tags":[{"tagId":22848,"faqCount":0,"tagName":"crmTest","priority":-4,"tagSelect":0},{"tagId":22847,"faqCount":0,"tagName":"crm标签","priority":-3,"tagSelect":0},{"tagId":21876,"faqCount":1,"tagName":"交通","priority":-2,"tagSelect":1},{"tagId":20707,"faqCount":0,"tagName":"标签","priority":-1,"tagSelect":0}]},"error_msg":"ok","error_code":0}
        // requestStr:{"result":{"totalCount":4,"currentPage":1,"tags":[{"tagId":22848,"faqCount":0,"tagName":"crmTest","priority":-4,"tagSelect":0},{"tagId":22847,"faqCount":0,"tagName":"crm标签","priority":-3,"tagSelect":0},{"tagId":21876,"faqCount":1,"tagName":"交通","priority":-2,"tagSelect":2},{"tagId":20707,"faqCount":1,"tagName":"标签","priority":-1,"tagSelect":2}]},"error_msg":"ok","error_code":0}

//        ProductSearchRep productSearchRep  = null;
//        ProductSearchReq req  = new ProductSearchReq();
//
//
//        req.setPriceLimit(false);
//        req.setIds(Arrays.asList("210030257"));
//        req.setTagQuery(true);
//        req.setTerminalType(3);
//        //req.setBookCityCode(citycode);
//
//
//        String  encodeParam  = JSON.toJSONString(req);
//
//
//        HttpResponse response  = cn.hutool.http.HttpUtil.createGet("http://public-api.nj.dcb.tuniu.org/ror/category/ids/query")
//                .body(cn.hutool.core.codec.Base64.encode(encodeParam, StandardCharsets.UTF_8)).timeout(3000).execute();
//        String res = cn.hutool.core.codec.Base64.decodeStr(response.body(), StandardCharsets.UTF_8);
//
//
//
//
//        if(StrUtil.isNotBlank(res)){
//            productSearchRep  = JSON.parseObject(res,ProductSearchRep.class);
//
//        }
//
//          productSearchRep.getData();
//        List<String> ids = Lists.newArrayList();
//        for (int i = 2532; i <2615 ; i++) {
//           ids.add(String.valueOf(i));
//        }
//        System.out.println( Joiner.on(",").join(ids));
//
//        BiReCommendReq biReCommendReq =new BiReCommendReq();
//        biReCommendReq.setPageNum(1);
//        biReCommendReq.setPageSize(30);
//        AppResponse appResponse  = AppResponse.getDefaultResponse();
//
//        ProductSearchReq req = new ProductSearchReq();
//        ProductSearchRep productSearchRep = new ProductSearchRep() ;
//
//        if(biReCommendReq.getPageNum()==null || biReCommendReq.getPageNum()<=0){
//            biReCommendReq.setPageNum(1);
//        }
//        if(biReCommendReq.getPageSize()==null || biReCommendReq.getPageSize()<=0){
//            biReCommendReq.setPageSize(10);
//        }
//
//        Map<String,Object> params = new HashMap<>();
//        params.put("userId", "");
//        params.put("currentPage", biReCommendReq.getPageNum());
//        params.put("systemCode","mob-ocsfaq");
//        params.put("pageSize", biReCommendReq.getPageSize());
//        if(StrUtil.isNotBlank(biReCommendReq.getCityCode())) {
//            params.put("cityCode", biReCommendReq.getCityCode());
//        }
//        if(StrUtil.isNotBlank(biReCommendReq.getUserId())) {
//            params.put("userId", biReCommendReq.getUserId());
//        }
//        params.put("scene","al-chat");
//
//
//        BiRecommendResponse biRecommendResponse = new BiRecommendResponse();
//
//        String crm_request_res = cn.hutool.http.HttpUtil.post(UnitEnum.PRODUCT_RECOMMAND.getUrlSuffix(),JSON.toJSONString(params),2000);
//
//
//
//
//        if(StrUtil.isNotBlank(crm_request_res)){
//
//            biRecommendResponse = JSON.parseObject(crm_request_res, BiRecommendResponse.class);
//        }
//
//
//        List<BiProduct> products = new ArrayList<>();
//        if(biRecommendResponse.getData()!=null && CollectionUtil.isNotEmpty(biRecommendResponse.getData().getProducts())){
//            products = biRecommendResponse.getData().getProducts();
//        }
//
//
//        Map<Integer,String>  productModelTypeMap = products.stream().collect(Collectors.toMap(BiProduct::getProductId,BiProduct::getModelType,(k1, k2)->k2));
//
//        List<String>  ids  = products.stream().filter(e->e.getProductId()!=null).map(BiProduct::getProductId).map(String::valueOf).collect(Collectors.toList());
//       // req.setPriceLimit(false);
//        req.setIds(ids);
//        req.setTagQuery(true);
//        //req.setTerminalType(3);
//        //req.setBookCityCode(biReCommendReq.getCityCode()==null?0:Integer.parseInt(biReCommendReq.getCityCode()));
//
//        String  encodeParam  = JSON.toJSONString(req);
//
//        HttpResponse response  = cn.hutool.http.HttpUtil.createGet(UnitEnum.PRODUCT_SEARCH.getUrlSuffix())
//                .body(cn.hutool.core.codec.Base64.encode(encodeParam, StandardCharsets.UTF_8)).timeout(3000).execute();
//        String res = cn.hutool.core.codec.Base64.decodeStr(response.body(), StandardCharsets.UTF_8);
//
//
//        if(StrUtil.isNotBlank(res)){
//            productSearchRep  = JSON.parseObject(res,ProductSearchRep.class);
//
//        }
//        if(productSearchRep!=null && CollectionUtil.isNotEmpty(productSearchRep.getData())){
//            productSearchRep.getData().stream().forEach(e->{
//                if(productModelTypeMap.containsKey(e.getProductId())){
//                    e.setModelType(productModelTypeMap.getOrDefault(e.getProductId(),""));
//                }
//            });
//        }
//        appResponse.setErrorCode(UnitResponseEnum.SUCCESS.getErrorCode());
//        appResponse.setMsg(UnitResponseEnum.SUCCESS.getErrorMsg());
//        appResponse.setData(productSearchRep !=null ? productSearchRep.getData() :null);
//        appResponse.setSuccess(true);
       // List<String> us =  getPlayTopicByPrdId(320077011);

      //  System.out.println(        Version.getInstance("10.86.0").compareTo(Version.getInstance("10.86.0")));

//        String url = "http://public-api.pms.mcs.tuniu-sit.org/manage/product/queryCallNumberByResourceId";
//        Map<String,Object> param = new HashMap<>();
//        param.put("resourceId",1118); param.put("vendorId",4333);
//        param.put("orderStatus",0); param.put("orderId",1234567890); param.put("customerId",2345); param.put("terminal",1);
//
//        HttpResponse httpResponse  = HttpRequest.post(url).body(Base64.encode(JSON.toJSONString(param))).execute();
//
//        JSONObject  response= null;
//        boolean needBase64Decode = true;
//        if( httpResponse !=null && httpResponse.getStatus()==200 ){
//            if(needBase64Decode){
//                response    = JSONObject.parseObject(Base64.decodeStr(httpResponse.body(),StandardCharsets.UTF_8));
//            }else{
//                response    = JSONObject.parseObject(httpResponse.body());
//
//            }
//        }
//        System.out.println(response);
        List<Long> unuseId = new ArrayList<>();

//        try {
//            String getFaqListUrl = "http://api-p.tuniu.com/ocsfaq/manager/unit/customer/all/question?resourceType=27&pageSize=100&pageNum=1";
//            String faqListStr = cn.hutool.http.HttpUtil.get(getFaqListUrl);
//            CustomerQuestionResponse faqResponse = JSON.parseObject(faqListStr, CustomerQuestionResponse.class);
//
//            Map<String, Object> param = new HashMap<>();
//
//            param.put("skillId", 1253029);
//            param.put("pageNo", 1);
//            param.put("pageSize", 10);
//            if (CollectionUtil.isNotEmpty(faqResponse.getData())) {
//
//                for (CustomerQuestionVo e : faqResponse.getData()) {
//                    param.put("searchKey", e.getQuestion());
//
//                    String res = HttpUtil.post(searchUrl, accessToken, "application/json", JSON.toJSONString(param));
//
//                    Map<String, Object> resMap = JSON.parseObject(res, Map.class);
//
//                    if (resMap.get("result") != null) {
//                        JSONObject obj1 = (JSONObject) resMap.get("result");
//                        List<FaqPair> faqPairs = JSONArray.parseArray(obj1.getJSONArray("faqPairs").toJSONString(), FaqPair.class);
//                        if (CollectionUtil.isNotEmpty(faqPairs)) {
//                            if(faqPairs.size()>1){
//                                faqPairs = faqPairs.stream().sorted(Comparator.comparingLong(FaqPair::getFaqId).reversed()).collect(Collectors.toList());
//                                faqPairs.remove(0);
//
//                                unuseId.addAll(faqPairs.stream().map(FaqPair::getFaqId).collect(Collectors.toList()));
//                            }
//
//                           // Long unitFaqId = faqPairs.get(0).getFaqId();
//                        }
//                    }
//                }
//
//
//            }
//        } catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//
//
//
//        String ansUrl = "http://public-api.tof.tuniu.org/tof/manage/chat/customer/question/answer";
//        String ansurl2 = "http://api-p.tuniu.com/ocsfaq/manager/unit/customer/question/answer";
//
//        String filterids = "4933680,3232412,3284454,3284456,3284477,3284514,3284519,3284571,3343154,3343165,3343901,3374354,3513345,3513348,3513357,3513360,3513361,3513362,3513365,3513367,3513374,3513378,3513412,3513425,3513427,3513434,3513438,3513442,3513448,3513459,3513460,3513469,3513478,3513483,3513491,3513498,3513499,3513500,3513502,3517390,3517391";
//        String unitIds = "3985183,4569192,4569208,4569274,4569275,4569276,4569278,4569279,4569280,4569281,4569282,4569283,4569284,4569285,4569286,4569287,4574835,4595609,4596291,3343906,3284445,3284521,3284522,3284446,3343133,3284449,3343134,3284524,41122389,3284474,3284527,3284529,4866099,3284457,3284461,3284460,4866094,3343147,4866092,3343148,4866090,3284534,41122404,4866087,3284466,3343862,3284470,3343155,3343159,3343179,3343159,3343169,3284552,3284479,3343170,4866143,3343174,3343907,3284483,3284555,41122424,3284489,3284491,3284492,3343182,3284494,4866062,3343906,3284498,3343295,3343296,3343394,3343297,3343395,3343863,3343133,3343572,3343398,3343399,3284566,3343861,3284568,3343590,3343906,41122449,3284517,3343910,41122452,3284573,3284575,3218236,4915136,41488215,41488217,41488231";
//        Map<Long,Object> filterMap =  Splitter.on(",").splitToList(filterids).stream().map(Long::parseLong).collect(Collectors.toMap(Function.identity(),Function.identity(),(k1,k2)->k2));
//
//
//        unuseId =  unuseId.stream().filter(e->!filterMap.containsKey(e)).collect(Collectors.toList());
//
//
//        String deleUrl = "https://unit.su.baidubce.com/rpc/2.0/unit/v3/faqskill/faqPair/batchDelete";
//        FaqBatchDeleteDto deleteDto = new FaqBatchDeleteDto();
//        deleteDto.setSkillId(1253029L);
//        List<Long>  delids = Splitter.on(",").splitToList(unitIds).stream().map(Long::parseLong).collect(Collectors.toList());
//
//        deleteDto.setFaqIds(unuseId);
//        String res = HttpUtil.post(deleUrl, accessToken, "application/json", JSON.toJSONString(deleteDto));

        Map<String,Object> param = new HashMap<>();
//        param.put("uid","32565");
//        param.put("r",0.7738382706507829);
//        param.put("id","48");
//        param.put("orderId",1247399530);
//        param.put("resourceId",2083069397);
//        param.put("resourceType","27");
//        param.put("vendorId","37780");
//        param.put("customerId","8957");
//        param.put("terminal",1);
    //    HttpResponse response  =   HttpRequest.get(ansUrl).body(Base64.encode(JSON.toJSONString(param))).timeout(2000).execute();
       //  id=2&resourceType=18&pageSize=10&pageNum=1
//        param.put("id",2); param.put("resourceType",18);
//        HttpResponse response  =   HttpRequest.get(ansurl2).form(param).timeout(2000).execute();
//        if( response !=null && response.getStatus()==200 ){
//            System.out.println(response.body());
//        }
//        String Supply_Pre="#{供应商分机电话.data.售前.电话号码}";
//        String u2="#{供应商分机电话.data.售前.电话号码}";
//        System.out.println(u2.equals(Supply_Pre));


       List<FaqQuestion> qs = StringUtils.strToStringList("123&&&2711&&&2819&&&").stream().map(e->new FaqQuestion(e)).collect(Collectors.toList());


        String htmlContent = "<p><span style=\"font-family: &quot;PingFang SC&quot;, &quot;Microsoft YaHei&quot;, sans-serif; font-size: 14px; color: rgb(56, 56, 56);\" data-mce-style=\"font-family: 'PingFang SC', 'Microsoft YaHei', sans-serif; font-size: 14px; color: #383838;\">&nbsp;您好，签约前问题咨询可联系售前人员，&nbsp;<span dynamic_interface_id=\"1\" dynamic_outparam_desc=\"售前供应商联系人\" dynamic_placeholder=\"#{供应商分机电话.data.售前.管理员}\" dynamic_value=\"测试彩色\"> <span dynamic_outparam_desc=\"售前供应商联系人\" dynamic_placeholder=\"#{供应商分机电话.data.售前.管理员}\" dynamic_interface_id=\"1\" dynamic_value=\"测试彩色\"> <span dynamic_outparam_desc=\"售前供应商联系人\" dynamic_placeholder=\"#{供应商分机电话.data.售前.管理员}\" dynamic_interface_id=\"1\" dynamic_value=\"测试彩色\"> 售前供应商联系人: 测试彩色</span>&nbsp; ，&nbsp;<span dynamic_outparam_desc=\"售前供应商联系方式\" dynamic_placeholder=\"#{供应商分机电话.data.售前.电话号码}\" dynamic_interface_id=\"1\" dynamic_value=\"400161999950012\"> 售前供应商联系方式: 400161999950012<a href=\"tuniucom://callfun?param1=3400161999950012&amp;param2=11898765432112345678\">呼叫</a></span>&nbsp;</span></span></span></p>";

        org.jsoup.nodes.Document doc = Jsoup.parse(htmlContent);
        Elements elements =  doc.getElementsByAttribute("dynamic_interface_id");


        for (int i = 0; i <elements.size() ; i++) {
            org.jsoup.nodes.Element element = elements.get(i);
            Attributes attributes = element.attributes();

        }

        String  jsonstr = "{\n" +
                "  \"success\": true,\n" +
                "  \"msg\": null,\n" +
                "  \"errorCode\": 1010000,\n" +
                "  \"data\": {\n" +
                "    \"departDates\": [\n" +
                "      {\n" +
                "        \"id\": 198098200,\n" +
                "        \"productId\": 210481748,\n" +
                "        \"planDate\": \"2023-04-13 00:00:00\",\n" +
                "        \"roomGrapFlag\": 0,\n" +
                "        \"aheadDate\": 0,\n" +
                "        \"deadlineDate\": 0,\n" +
                "        \"deadlineHour\": 0,\n" +
                "        \"deadlineTime\": \"2023-04-13 00:00:00.0\",\n" +
                "        \"setGroupFlag\": 0,\n" +
                "        \"journeyId\": 0,\n" +
                "        \"signPeopleNum\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 198098201,\n" +
                "        \"productId\": 210481748,\n" +
                "        \"planDate\": \"2023-04-20 00:00:00\",\n" +
                "        \"roomGrapFlag\": 0,\n" +
                "        \"aheadDate\": 0,\n" +
                "        \"deadlineDate\": 0,\n" +
                "        \"deadlineHour\": 0,\n" +
                "        \"deadlineTime\": \"2023-04-20 00:00:00.0\",\n" +
                "        \"setGroupFlag\": 0,\n" +
                "        \"journeyId\": 0,\n" +
                "        \"signPeopleNum\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 198098202,\n" +
                "        \"productId\": 210481748,\n" +
                "        \"planDate\": \"2023-04-24 00:00:00\",\n" +
                "        \"roomGrapFlag\": 0,\n" +
                "        \"aheadDate\": 0,\n" +
                "        \"deadlineDate\": 0,\n" +
                "        \"deadlineHour\": 0,\n" +
                "        \"deadlineTime\": \"2023-04-24 00:00:00.0\",\n" +
                "        \"setGroupFlag\": 0,\n" +
                "        \"journeyId\": 0,\n" +
                "        \"signPeopleNum\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 198098203,\n" +
                "        \"productId\": 210481748,\n" +
                "        \"planDate\": \"2023-04-27 00:00:00\",\n" +
                "        \"roomGrapFlag\": 0,\n" +
                "        \"aheadDate\": 0,\n" +
                "        \"deadlineDate\": 0,\n" +
                "        \"deadlineHour\": 0,\n" +
                "        \"deadlineTime\": \"2023-04-27 00:00:00.0\",\n" +
                "        \"setGroupFlag\": 0,\n" +
                "        \"journeyId\": 0,\n" +
                "        \"signPeopleNum\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 201659726,\n" +
                "        \"productId\": 210481748,\n" +
                "        \"planDate\": \"2023-04-29 00:00:00\",\n" +
                "        \"roomGrapFlag\": 0,\n" +
                "        \"aheadDate\": 0,\n" +
                "        \"deadlineDate\": 0,\n" +
                "        \"deadlineHour\": 0,\n" +
                "        \"deadlineTime\": \"2023-04-29 00:00:00.0\",\n" +
                "        \"setGroupFlag\": 0,\n" +
                "        \"journeyId\": 0,\n" +
                "        \"signPeopleNum\": 5\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 201916867,\n" +
                "        \"productId\": 210481748,\n" +
                "        \"planDate\": \"2023-05-16 00:00:00\",\n" +
                "        \"roomGrapFlag\": 0,\n" +
                "        \"aheadDate\": 0,\n" +
                "        \"deadlineDate\": 0,\n" +
                "        \"deadlineHour\": 0,\n" +
                "        \"deadlineTime\": \"2023-05-16 00:00:00.0\",\n" +
                "        \"setGroupFlag\": 0,\n" +
                "        \"journeyId\": 0,\n" +
                "        \"signPeopleNum\": 4\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 202027508,\n" +
                "        \"productId\": 210481748,\n" +
                "        \"planDate\": \"2023-05-30 00:00:00\",\n" +
                "        \"roomGrapFlag\": 0,\n" +
                "        \"aheadDate\": 0,\n" +
                "        \"deadlineDate\": 0,\n" +
                "        \"deadlineHour\": 0,\n" +
                "        \"deadlineTime\": \"2023-05-30 00:00:00.0\",\n" +
                "        \"setGroupFlag\": 0,\n" +
                "        \"journeyId\": 0,\n" +
                "        \"signPeopleNum\": 4\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 202333058,\n" +
                "        \"productId\": 210481748,\n" +
                "        \"planDate\": \"2023-06-23 00:00:00\",\n" +
                "        \"roomGrapFlag\": 0,\n" +
                "        \"aheadDate\": 0,\n" +
                "        \"deadlineDate\": 0,\n" +
                "        \"deadlineHour\": 0,\n" +
                "        \"deadlineTime\": \"2023-06-23 00:00:00.0\",\n" +
                "        \"setGroupFlag\": 0,\n" +
                "        \"journeyId\": 0,\n" +
                "        \"signPeopleNum\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 202333059,\n" +
                "        \"productId\": 210481748,\n" +
                "        \"planDate\": \"2023-06-23 00:00:00\",\n" +
                "        \"roomGrapFlag\": 0,\n" +
                "        \"aheadDate\": 0,\n" +
                "        \"deadlineDate\": 0,\n" +
                "        \"deadlineHour\": 0,\n" +
                "        \"deadlineTime\": \"2023-06-23 00:00:00.0\",\n" +
                "        \"setGroupFlag\": 0,\n" +
                "        \"journeyId\": 0,\n" +
                "        \"signPeopleNum\": 4\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  \"productId\": 0,\n" +
                "  \"classBrandId\": 0,\n" +
                "  \"classBrandParentId\": 0\n" +
                "}";
        BohDepartDateResponse  bohDepartDateResponse = JSON.parseObject(jsonstr,BohDepartDateResponse.class);

        if (bohDepartDateResponse.isSuccess() && bohDepartDateResponse.getData().getDepartDates()!=null
        &&CollectionUtil.isNotEmpty(bohDepartDateResponse.getData().getDepartDates())) {

           List<BohDepartDate> departDates= bohDepartDateResponse.getData().getDepartDates().stream()
                    .filter(e->dateStrIsEqueal(e.getPlanDate(),"2023-04-29"))
                    .collect(Collectors.toList());

            bohDepartDateResponse.setData(new BohDepartDateResponseData(departDates));
        }

        System.out.println(dateStrIsEqueal("2023-04-29","2023-04-29"));

    }
    /**
     * {"result":{"services":[{"serviceType":"dialogueDistribution","serviceDesc":"","createTime":"2022-08-18 10:00:26","updateTime":"2022-08-18 11:15:14","serviceId":"S73782","serviceName":"test2","serviceCategory":"common"},{"serviceType":"taskflow","serviceDesc":"","createTime":"2022-08-16 16:58:15","updateTime":"2022-08-16 17:28:14","serviceId":"S73707","serviceName":"test","serviceCategory":"common"},{"serviceType":"taskflow","serviceDesc":"完成酒店的产品咨询","createTime":"2022-08-16 11:08:09","updateTime":"2022-08-16 11:08:09","serviceId":"S73675","serviceName":"酒店产品咨询","serviceCategory":"common"},{"serviceType":"dialogueDistribution","serviceDesc":"完成门票产品咨询","createTime":"2022-08-15 19:31:19","updateTime":"2022-08-18 09:57:55","serviceId":"S73657","serviceName":"门票产品咨询","serviceCategory":"common"}],"totalCount":4,"currentPage":1},"error_msg":"ok","error_code":0}
     */
    /**
     * {"result":{"totalCount":1,"currentPage":1,"faqPairs":[{"faqCategory":"user","faqStdQuestion":"测试","faqAnswers":[{"isDefault":1,"answer":"测试下问答功能","rules":[]}],"sluTags":[{"tagId":20707,"tagName":"标签","priority":-1}],"createTime":"2022-08-15 19:53:32","faqQuestions":[{"question":"测试"}],"faqId":2440412,"updateTime":"2022-08-15 19:53:32","patternCount":0}]},"error_msg":"ok","error_code":0}
     */
}
