package com.tuniu.mob.ocsfaq.faq.unit.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.util.concurrent.RateLimiter;
import com.tuniu.mob.ocsfaq.biz.leavemessage.constant.Constant;
import com.tuniu.mob.ocsfaq.faq.entity.UnitRequest;
import com.tuniu.mob.ocsfaq.faq.rasa.dto.RasaResponse;
import com.tuniu.mob.ocsfaq.faq.rasa.dto.RasaSimilaerResponse;
import com.tuniu.mob.ocsfaq.faq.rasa.dto.RasaTalkDto;
import com.tuniu.mob.ocsfaq.faq.unit.dto.*;
import com.tuniu.mob.ocsfaq.faq.unit.dto.faq.*;
import com.tuniu.mob.ocsfaq.faq.unit.enums.UnitEnum;
import com.tuniu.mob.ocsfaq.faq.unit.enums.UnitResponseEnum;
import com.tuniu.mob.ocsfaq.faq.unit.service.IUnitService;
import com.tuniu.mob.ocsfaq.faq.unit.utils.HttpUtil;
import com.tuniu.mob.ocsfaq.faq.unit.utils.UnitUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class UnitServiceImpl implements IUnitService {
    private static final Logger log = LoggerFactory.getLogger(UnitServiceImpl.class);


    @Value("${baidu.unit.apiKey}")
    private String apiKey;

    @Value("${baidu.unit.secretKey}")
    private String secretKey;

    @Value("${baidu.unit.tokenUrl}")
    private String tokenUrl;

    @Value("${baidu.unit.talkUrl}")
    private String talkUrl;

    @Value("${baidu.unit.prefixUrl}")

    private String prefixUrl;


    @Value("${tuniu.rasa.talkUrl}")
    private  String rasaTalkUrl;

    @Value("${tuniu.rasa.similarUrl}")
    private  String rasaSimilarUrl;




    @Resource
    UnitUtils unitUtils;


    // unit 接口 qps 限制
    private final static RateLimiter limiter = RateLimiter.create(9.0);




    @Override
    public FaqAnswerResponse unitUtterance(UnitRequest request) throws Exception {

        FaqAnswerResponse faqAnswerResponse = FaqAnswerResponse.getDefaultResponse();
        if (!this.verify(request)) {
            return faqAnswerResponse;
        }
        if (limiter.tryAcquire(1,1, TimeUnit.SECONDS)) {
            String accessToken = UnitUtils.getAccessToken(tokenUrl, apiKey, secretKey);
            String result = UnitUtils.utterance(talkUrl, request, accessToken);
            faqAnswerResponse = JSON.parseObject(result, FaqAnswerResponse.class);
        } else {
            // 限流直接返回请求失败让app转人工
           faqAnswerResponse = FaqAnswerResponse.getEnumResponse(UnitResponseEnum.UNIT_QPS_LIMIT);
        }

        return faqAnswerResponse;
    }

    @Override
    public RobotListResponse queryRobotList(PageDto pageDto) throws Exception {
        checkPageDto(pageDto);
        return sendUnitRequest(UnitEnum.ROBOT_LIST, pageDto, RobotListResponse.class);
    }

    @Override
    public RobotAddResponse addRobot(RobotAddDto robotAddDto) throws Exception {

        if (!robotAddDto.valid()) {
            return null;
        }

        return sendUnitRequest(UnitEnum.ROBOT_ADD, robotAddDto, RobotAddResponse.class);
    }

    @Override
    public Response updateRobot(RobotUpdateDto updateDto) throws Exception {

        if (!updateDto.valid()) {
            return null;
        }

        return sendUnitRequest(UnitEnum.ROBOT_UPDATE, updateDto, Response.class);
    }

    @Override
    public Response deleteRobot(RobotDeleteDto deleteDto) throws Exception {

        if (!deleteDto.valid()) {
            return null;
        }

        return sendUnitRequest(UnitEnum.ROBOT_DELETE, deleteDto, Response.class);
    }

    @Override
    public RobotDetailResponse queryRobotDetail(RobotDetailDto detailDto) throws Exception {

        if (!detailDto.valid()) {
            return null;
        }
        return sendUnitRequest(UnitEnum.ROBOT_DETAIL, detailDto, RobotDetailResponse.class);
    }

    @Override
    public RobotSkillListResponse queryRobotSkillList(RobotSkillListDto robotSkillListDto) throws Exception {

        if (!robotSkillListDto.valid()) {
            return null;
        }

        return sendUnitRequest(UnitEnum.ROBOT_SKILL_LIST, robotSkillListDto, RobotSkillListResponse.class);
    }

    @Override
    public Response addRobotSkill(RobotSkillAddDto addDto) throws Exception {

        if (!addDto.valid()) {
            return null;
        }

        return sendUnitRequest(UnitEnum.ROBOT_SKILL_ADD, addDto, Response.class);
    }

    @Override
    public Response sortRobotSkill(RobotSkillSortDto sortDto) throws Exception {

        if (!sortDto.valid()) {
            return null;
        }

        return sendUnitRequest(UnitEnum.ROBOT_SKILL_SORT, sortDto, Response.class);
    }

    @Override
    public Response deleteSkill(RobotSkillDeteleDto deteleDto) throws Exception {

        if (!deteleDto.valid()) {
            return null;
        }

        return sendUnitRequest(UnitEnum.ROBOT_SKILL_REMOVE, deteleDto, Response.class);
    }

    @Override
    public FaqSkilllistResponse quertFaqSkillList(FaqSkillListDto listDto) throws Exception {


        if (!listDto.valid()) {
            return null;
        }
        return sendUnitRequest(UnitEnum.FAQ_SKILL_LIST, listDto, FaqSkilllistResponse.class);

    }

    @Override
    public FaqAddResponse addFaqSkill(FaqAddDto addDto) throws Exception {

        if (!addDto.valid()) {
            return null;
        }
        return sendUnitRequest(UnitEnum.FAQ_SKILL_ADD, addDto, FaqAddResponse.class);
    }

    @Override
    public FAqBatchAddResponse batchAddFaqSkill(FaqBatchAddDto addDto) throws Exception {
        if (!addDto.valid()) {
            return null;
        }
        return sendUnitRequest(UnitEnum.FAQ_SKILL_BATCH_ADD, addDto, FAqBatchAddResponse.class);
    }

    @Override
    public FaqDetailResponse queryFaqSkillDetail(FaqDetailDto detailDto) throws Exception {

        if (!detailDto.valid()) {
            return null;
        }
        return sendUnitRequest(UnitEnum.FAQ_SKILL_INFO, detailDto, FaqDetailResponse.class);
    }

    @Override
    public Response updateFaqSkill(FaqSkillUpdateDto updateDto) throws Exception {


        if (!updateDto.valid()) {
            return null;
        }

        return sendUnitRequest(UnitEnum.FAQ_SKILL_UPDATE, updateDto, Response.class);
    }

    @Override
    public Response updateTags(FaqUpdateTagsDto updateTags) throws Exception {


        return null;
    }

    @Override
    public Response batchDeleteFaqSkill(FaqBatchDeleteDto deleteDto) throws Exception {
        if (!deleteDto.valid()) {
            return null;
        }

        return sendUnitRequest(UnitEnum.FAQ_SKILL_BATCH_DELETE, deleteDto, Response.class);
    }


    @Override
    public FaqSearchResponse faqSkillSearch(FaqSearchDto searchDto) throws Exception {

        return null;
    }

    @Override
    public FaqSkillModelListResponse faqSkillModelList(FaqSkillModelListDto faqSkillModelListDto) throws Exception {

        if (!faqSkillModelListDto.valid()) {
            return null;
        }
        return sendUnitRequest(UnitEnum.FAQ_SKILL_MODEL_LIST, faqSkillModelListDto, FaqSkillModelListResponse.class);
    }

    @Override
    public FaqSkillModelTrainResponse faqSkillModelTrain(FaqSkillModelTrainDto trainDto) throws Exception {
        if (!trainDto.valid()) {
            return null;
        }
        return sendUnitRequest(UnitEnum.FAQ_SKILL_MODEL_TRAIN, trainDto, FaqSkillModelTrainResponse.class);
    }

    @Override
    public Response faqSkillModelDelete(FaqSkillModelDeleteDto deleteDto) throws Exception {

        if (!deleteDto.valid()) {
            return null;
        }

        return sendUnitRequest(UnitEnum.FAQ_SKILL_MODEL_DELETE, deleteDto, Response.class);
    }


    @Override
    public FaqSkillTagListResponse faqSkillTagList(FaqSkillTagListDto tagListDto) throws Exception {

        if (!tagListDto.valid()) {
            return null;
        }

        return sendUnitRequest(UnitEnum.FAQ_SKILL_TAG_LIST, tagListDto, FaqSkillTagListResponse.class);
    }

    @Override
    public FaqSkillTagAddResponse faqSkillTagAdd(FaqSkillTagAddDto addDto) throws Exception {

        if (!addDto.valid()) {
            return null;
        }

        return sendUnitRequest(UnitEnum.FAQ_SKILL_TAG_ADD, addDto, FaqSkillTagAddResponse.class);
    }

    @Override
    public Response faqSkillTagBtachDelete(FaqSkillTagBatchDeleteDto deleteDto) throws Exception {


        if (!deleteDto.valid()) {
            return null;
        }

        return sendUnitRequest(UnitEnum.FAQ_SKILL_TAG_BATCH_DELETE, deleteDto, Response.class);
    }

    @Override
    public RasaResponse rasaTalk(RasaTalkDto talkDto) throws Exception {

        RasaResponse response  =  RasaResponse.getDefaultResponse();
        if(!talkDto.valid()){return  RasaResponse.getParamErroResponse();}

        String result = cn.hutool.http.HttpUtil.post(rasaTalkUrl,JSON.toJSONString(talkDto),30000);

        if(StrUtil.isNotBlank(result)){
            response = JSON.parseObject(result,RasaResponse.class);

        }
        response.setErrorCode(UnitResponseEnum.SUCCESS.getErrorCode());
        response.setMsg(UnitResponseEnum.SUCCESS.getErrorMsg());
        response.setSuccess(true);
        return  response;
    }


    @Override
    public RasaSimilaerResponse rasaSimilar(RasaTalkDto talkDto) throws Exception {

        RasaSimilaerResponse response = RasaSimilaerResponse.getDefaultResponse();
        if(!talkDto.valid()){return  RasaSimilaerResponse.getParamErroResponse();}
        String result = cn.hutool.http.HttpUtil.post(rasaSimilarUrl,JSON.toJSONString(talkDto),30000);

        if(StrUtil.isNotBlank(result)){
            response = JSON.parseObject(result,RasaSimilaerResponse.class);

        }
        response.setSuccess(true);
        response.setMsg(UnitResponseEnum.SUCCESS.getErrorMsg());
        response.setErrorCode(UnitResponseEnum.SUCCESS.getErrorCode());
        return response;
    }

    public <T> T sendUnitRequest(UnitEnum unitEnum, Object param, Class<T> resultType) throws Exception {
        String accessToken = UnitUtils.getAccessToken(tokenUrl, apiKey, secretKey);
        String requestStr = HttpUtil.post(prefixUrl + unitEnum.getUrlSuffix(), accessToken, JSON.toJSONString(param));
        if (StringUtils.isBlank(requestStr)) {
            return null;
        }
        T res = JSON.parseObject(requestStr, resultType);
        return res;
    }

    public boolean verify(UnitRequest request) {

        if (request == null) {
            return false;
        }

        if (StringUtils.isBlank(request.getLogId())
                || StringUtils.isBlank(request.getServiceId())
                || StringUtils.isBlank(request.getVersion())
                || StringUtils.isBlank(request.getSessionId())
                || ObjectUtil.isEmpty(request.getRequest())
                || !request.getRequest().valid()
        ) {
            return false;
        }

        if (StringUtils.isBlank(request.getRequest().getTerminalId())

                || StringUtils.isBlank(request.getRequest().getQuery())
        ) {
            return false;
        }
        return true;

    }

    public void checkPageDto(PageDto pageDto) {
        if (pageDto.getPageNo() == null || pageDto.getPageNo() <= 0) {
            pageDto.setPageNo(Constant.PAGE_NO);
        }
        if (pageDto.getPageSize() == null || pageDto.getPageSize() <= 0) {
            pageDto.setPageSize(Constant.PAGE_SIZE);
        }
    }
}
