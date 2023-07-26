package com.tuniu.mob.ocsfaq.faq.unit.service.impl;

import com.alibaba.fastjson.JSON;
import com.tuniu.mob.ocsfaq.faq.dao.FaqDynamicParameterMapper;
import com.tuniu.mob.ocsfaq.faq.entity.FaqDynamicParameter;
import com.tuniu.mob.ocsfaq.faq.unit.service.IFaqParamterService;
import com.tuniu.mob.ocsfaq.faq.vo.EnterParameterDto;
import com.tuniu.mob.ocsfaq.faq.vo.GroupCountVo;
import com.tuniu.mob.ocsfaq.faq.vo.OutParameterNodeDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description
 * @Date 2022/11/22
 */
@Service
public class FaqParamterServiceImpl implements IFaqParamterService {

    private static final  Logger log  = LoggerFactory.getLogger(FaqParamterServiceImpl.class);



    @Resource
    FaqDynamicParameterMapper parameterMapper;



    /**
     * @Description 接口配置的出参 先插入一级字段 再递归插入子字段
     * @param outParams
     * @return 字段树 一级字段id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Integer> insertOutParameters(List<OutParameterNodeDto> outParams) {
        FaqDynamicParameter parameter = new FaqDynamicParameter();
        // 一级字段id
        List<Integer> firstColumnIds = new ArrayList<Integer>();
        for (OutParameterNodeDto node: outParams) {
            BeanUtils.copyProperties(node, parameter);
            parameterMapper.insertSelective(parameter);
            firstColumnIds.add(parameter.getId());
            if (parameter.getId()!=null && !CollectionUtils.isEmpty(node.getChildren())) {
                // 不用回滚
                this.insertOutParamChildren(node.getChildren(), parameter.getId(),parameter.getId()+"-");
            }
        }
        return firstColumnIds;
    }

    /**
     * @Description  入参现在只支持一层不支持对象 数组嵌套字段
     * @param enterParams
     * @return  字段树 一级字段id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Integer> insertEnterParameters(List<EnterParameterDto> enterParams) {
        FaqDynamicParameter parameter = new FaqDynamicParameter();
        // 一级字段id
        List<Integer> firstColumnIds = new ArrayList<Integer>();
        if(CollectionUtils.isEmpty(enterParams)){return  firstColumnIds;}
        for(EnterParameterDto enterParameterDto: enterParams){
            BeanUtils.copyProperties(enterParameterDto,parameter);
            parameterMapper.insertSelective(parameter);
            firstColumnIds.add(parameter.getId());
            if (parameter.getId()!=null && !CollectionUtils.isEmpty(enterParameterDto.getChildren())) {
                // 不用回滚
                this.insertEnterParamChildren(enterParameterDto.getChildren(), parameter.getId(),parameter.getId()+"-");
            }
        }

        return firstColumnIds;
    }


    @Override
    public List<FaqDynamicParameter> selectDynamicParameterByIds(List<Integer> ids) {
        return  parameterMapper.selectByIds(ids);
    }

    @Override
    public FaqDynamicParameter selectDynamicParameterById(Integer id) {
        return parameterMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer countDynamicParameterByParentIds(Integer parentId) {
        return parameterMapper.countByParentId(parentId);
    }

    @Override
    public List<FaqDynamicParameter> selectDynamicParameterByParentIds(List<Integer> ids) {
        return parameterMapper.selectByParentIds(ids);
    }

    @Override
    public List<GroupCountVo> selectGroupCountByParentIds(List<Integer> ids) {
        return parameterMapper.countByParentIds(ids);
    }

    @Override
    public List<FaqDynamicParameter> selectDynamicParameterChildren(List<Integer> firstParamIds) {
        List<FaqDynamicParameter> trees = new ArrayList<FaqDynamicParameter>();
        for (Integer id: firstParamIds) {
            trees.addAll(parameterMapper.selectDynamicParameterChildrenById(id));
        }
        return  trees;
    }


    @Override
    public int delParamterByIds(List<Integer> ids) {
        return parameterMapper.deleteByIds(ids);
    }

    @Override
    public int updateById(FaqDynamicParameter record ) {
        return parameterMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * @Description 插入参数子节点数据 失败的直接忽略 页面上只用补录 失败数据
     * @param nodeDtos
     * @param parentId
     */
    private  void insertOutParamChildren( List<OutParameterNodeDto>  nodeDtos,Integer parentId){
        if(CollectionUtils.isEmpty(nodeDtos) || parentId ==null){
            return;
        }
        FaqDynamicParameter pm = new FaqDynamicParameter();
        for(OutParameterNodeDto  node  :nodeDtos){
            try {
                BeanUtils.copyProperties(node,pm);
                pm.setParentId(parentId);
                this.parameterMapper.insertSelective(pm);
            }catch (Exception e){
                log.error("insertOutParamChildren errorInfo={}",e.getMessage());
            }

            if(pm.getId() != null && !CollectionUtils.isEmpty(node.getChildren())){
                this.insertOutParamChildren(node.getChildren(), pm.getId());
            }

        }
    }


    /**
     * @Description 插入参数子节点数据 失败的直接忽略 页面上只用补录 失败数据
     * @param nodeDtos
     * @param parentId
     * @param parentIdPaths  级联path、
     */
    private  void insertOutParamChildren( List<OutParameterNodeDto>  nodeDtos,Integer parentId,String parentIdPaths){
        if(CollectionUtils.isEmpty(nodeDtos) || parentId ==null){
            return;
        }
        FaqDynamicParameter pm = new FaqDynamicParameter();
        for(OutParameterNodeDto  node  :nodeDtos){
            try {
                BeanUtils.copyProperties(node,pm);
                pm.setParentId(parentId);
                pm.setPath(parentIdPaths);
                this.parameterMapper.insertSelective(pm);
            }catch (Exception e){
                log.error("insertOutParamChildren errorInfo={},paramInfo = {}",e.getMessage(), JSON.toJSONString(pm));
            }

            if(pm.getId() != null && !CollectionUtils.isEmpty(node.getChildren())){
                this.insertOutParamChildren(node.getChildren(), pm.getId(),parentIdPaths+pm.getId()+"-");
            }

        }
    }


    /**
     * @Description 插入参数子节点数据 失败的直接忽略 页面上只用补录 失败数据
     * @param nodeDtos
     * @param parentId
     * @param parentIdPaths  级联path、
     */
    private  void insertEnterParamChildren( List<EnterParameterDto>  nodeDtos,Integer parentId,String parentIdPaths){
        if(CollectionUtils.isEmpty(nodeDtos) || parentId ==null){
            return;
        }
        FaqDynamicParameter pm = new FaqDynamicParameter();
        for(EnterParameterDto  node  :nodeDtos){
            try {
                BeanUtils.copyProperties(node,pm);
                pm.setParentId(parentId);
                pm.setPath(parentIdPaths);
                this.parameterMapper.insertSelective(pm);
            }catch (Exception e){
                log.error("insertOutParamChildren errorInfo={},paramInfo = {}",e.getMessage(), JSON.toJSONString(pm));
            }

            if(pm.getId() != null && !CollectionUtils.isEmpty(node.getChildren())){
                this.insertEnterParamChildren(node.getChildren(), pm.getId(),parentIdPaths+pm.getId()+"-");
            }

        }
    }








}
