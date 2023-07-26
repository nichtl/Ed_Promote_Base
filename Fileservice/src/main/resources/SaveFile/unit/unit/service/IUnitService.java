package com.tuniu.mob.ocsfaq.faq.unit.service;

import com.tuniu.mob.ocsfaq.faq.entity.UnitRequest;
import com.tuniu.mob.ocsfaq.faq.rasa.dto.RasaResponse;
import com.tuniu.mob.ocsfaq.faq.rasa.dto.RasaSimilaerResponse;
import com.tuniu.mob.ocsfaq.faq.rasa.dto.RasaTalkDto;
import com.tuniu.mob.ocsfaq.faq.unit.dto.*;
import com.tuniu.mob.ocsfaq.faq.unit.dto.faq.*;

public interface IUnitService {

    FaqAnswerResponse unitUtterance(UnitRequest request) throws Exception;


    /**
     * 查询机器器⼈人列列表
     * @param pageDto
     * @return
     * @throws Exception
     */
    RobotListResponse queryRobotList(PageDto pageDto) throws Exception;


    RobotAddResponse addRobot(RobotAddDto robotAddDto) throws Exception;


    Response updateRobot(RobotUpdateDto updateDto) throws Exception;


    Response deleteRobot(RobotDeleteDto deleteDto) throws Exception;


    RobotDetailResponse queryRobotDetail(RobotDetailDto detailDto) throws Exception;


    /**
     * 查询机器器⼈人技能列列表
     * @param robotSkillListDto
     * @return
     * @throws Exception
     */
    RobotSkillListResponse queryRobotSkillList(RobotSkillListDto robotSkillListDto) throws Exception;


    Response addRobotSkill(RobotSkillAddDto addDto) throws Exception;


    Response sortRobotSkill(RobotSkillSortDto sortDto) throws Exception;


    Response deleteSkill(RobotSkillDeteleDto deteleDto) throws Exception;


    FaqSkilllistResponse quertFaqSkillList(FaqSkillListDto listDto) throws Exception;


    /**
     * 新建问答对
     * @param addDto
     * @return
     * @throws Exception
     */
    FaqAddResponse addFaqSkill(FaqAddDto addDto) throws Exception;


    FAqBatchAddResponse batchAddFaqSkill(FaqBatchAddDto addDto) throws Exception;


    FaqDetailResponse queryFaqSkillDetail(FaqDetailDto detailDto) throws Exception;


    /**
     * 修改问答对
     * @param updateDto
     * @return
     * @throws Exception
     */
    Response updateFaqSkill(FaqSkillUpdateDto updateDto) throws Exception;

    /**
     * 批量删除问答对
     * @param deleteDto
     * @return
     * @throws Exception
     */
    Response batchDeleteFaqSkill(FaqBatchDeleteDto deleteDto) throws Exception;

    Response updateTags(FaqUpdateTagsDto updateTags) throws Exception;

    FaqSearchResponse faqSkillSearch(FaqSearchDto searchDto) throws Exception;

    FaqSkillModelListResponse faqSkillModelList(FaqSkillModelListDto faqSkillModelListDto) throws Exception;

    FaqSkillModelTrainResponse faqSkillModelTrain(FaqSkillModelTrainDto trainDto) throws Exception;

    Response faqSkillModelDelete(FaqSkillModelDeleteDto deleteDto) throws Exception;

    /**
     * 查询标签列列表
     * @param tagListDto
     * @return
     * @throws Exception
     */
    FaqSkillTagListResponse faqSkillTagList(FaqSkillTagListDto tagListDto) throws Exception;


    /**
     * 新建标签
     * @param addDto
     * @return
     * @throws Exception
     */
    FaqSkillTagAddResponse faqSkillTagAdd(FaqSkillTagAddDto addDto) throws Exception;


    Response faqSkillTagBtachDelete(FaqSkillTagBatchDeleteDto deleteDto) throws Exception;


    RasaResponse rasaTalk(RasaTalkDto talkDto) throws  Exception;

    RasaSimilaerResponse rasaSimilar(RasaTalkDto talkDto) throws  Exception;

}
