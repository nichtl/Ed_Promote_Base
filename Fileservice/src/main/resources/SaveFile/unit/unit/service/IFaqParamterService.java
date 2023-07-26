package com.tuniu.mob.ocsfaq.faq.unit.service;

import com.tuniu.mob.ocsfaq.faq.entity.FaqDynamicParameter;
import com.tuniu.mob.ocsfaq.faq.vo.EnterParameterDto;
import com.tuniu.mob.ocsfaq.faq.vo.GroupCountVo;
import com.tuniu.mob.ocsfaq.faq.vo.OutParameterNodeDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IFaqParamterService {


     /**
      * 新增接口出参信息 返回一级节点id
      * @param outParams
      * @return
      */
     List<Integer>  insertOutParameters(List<OutParameterNodeDto> outParams);
     /**
      * 新增接口入参信息 返回一级节点id
      * @param enterParams
      * @return
      */
     List<Integer>  insertEnterParameters(List<EnterParameterDto> enterParams);


     List<FaqDynamicParameter> selectDynamicParameterByIds(List<Integer> ids);

     FaqDynamicParameter   selectDynamicParameterById(Integer id);





     List<FaqDynamicParameter> selectDynamicParameterByParentIds( List<Integer> ids);



     List<GroupCountVo>   selectGroupCountByParentIds(List<Integer> ids);


     Integer  countDynamicParameterByParentIds(Integer parentID);

     /**
      * @Description 根据第一层字段id,来查出接口下字段树
      * @param firstParamIds
      * @return
      */
     List<FaqDynamicParameter> selectDynamicParameterChildren(List<Integer> firstParamIds);


     int delParamterByIds(List<Integer> ids);


     int updateById(FaqDynamicParameter parameter);















}
