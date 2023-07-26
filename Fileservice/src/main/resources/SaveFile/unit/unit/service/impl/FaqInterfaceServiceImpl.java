package com.tuniu.mob.ocsfaq.faq.unit.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.tuniu.mob.boot.tsp.TspClient;
import com.tuniu.mob.ocsfaq.faq.dao.FaqDynamicInterfaceMapper;
import com.tuniu.mob.ocsfaq.faq.entity.FaqDynamicInterface;
import com.tuniu.mob.ocsfaq.faq.entity.FaqDynamicParameter;
import com.tuniu.mob.ocsfaq.faq.unit.enums.DynamiceEnum;
import com.tuniu.mob.ocsfaq.faq.unit.enums.UnitConstant;
import com.tuniu.mob.ocsfaq.faq.unit.service.IFaqInterfaceService;
import com.tuniu.mob.ocsfaq.faq.unit.service.IFaqParamterService;
import com.tuniu.mob.ocsfaq.faq.vo.FaqOption;
import com.tuniu.mob.ocsfaq.faq.vo.FaqOptionDto;
import com.tuniu.mob.ocsfaq.faq.vo.GroupCountVo;
import com.tuniu.mob.ocsfaq.faq.vo.InterfaceFormDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.nio.charset.Charset;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Description
 * @Date 2022/11/23
 */
@Service
public class FaqInterfaceServiceImpl  implements IFaqInterfaceService {

    private static final Logger log  = LoggerFactory.getLogger(FaqInterfaceServiceImpl.class);

    @Resource
    FaqDynamicInterfaceMapper interfaceMapper;

    @Resource
    IFaqParamterService paramterService;

    @Autowired
    private TspClient tspClient;

    @Override
    public int insertInterfaceInfoSelective(InterfaceFormDto dto) {
        List<Integer>  outParamsFirstIds = paramterService.insertOutParameters(dto.getOutParam());
        if(CollectionUtils.isEmpty(outParamsFirstIds)){
            log.error("出参插入失败, 接口出参不能为空 errorInfo={}");
            return  -1;
        }
        List<Integer>  enterParamsFirstIds = paramterService.insertEnterParameters(dto.getEnterParam());
        // 接口入参可以为空
        if( !CollectionUtils.isEmpty(dto.getEnterParam()) && CollectionUtils.isEmpty(enterParamsFirstIds) ){
            log.error(" dto.getEnterParam() 入参不为空时 插入失败,errorInfo={}");
            return  -1;
        }
        FaqDynamicInterface  dynamicInterface = new FaqDynamicInterface();
        dynamicInterface.setAddress(dto.getAddress());
        dynamicInterface.setDescription(dto.getDesc());
        dynamicInterface.setName(dto.getName());
        dynamicInterface.setType(dto.getType());
        dynamicInterface.setInParameter(Joiner.on(",").join(enterParamsFirstIds));
        dynamicInterface.setOutParameter(Joiner.on(",").join(outParamsFirstIds));
        dynamicInterface.setAddTime(new Date());
        dynamicInterface.setUpdateTime(new Date());
        dynamicInterface.setMethodType(dto.getMethodType());
        dynamicInterface.setInParameterNeedEncode(dto.getInParameterNeedEncode());
        dynamicInterface.setOutParameterNeedDecode(dto.getOutParameterNeedDecode());
        return interfaceMapper.insertSelective(dynamicInterface);
    }

    @Override
    public List getOutParameterByInterfaceId(Integer interfaceId) {
        FaqDynamicInterface dynamicInterface = interfaceMapper.selectByPrimaryKey(interfaceId);

        if(StrUtil.isBlank(dynamicInterface.getOutParameter())){
            return  null;
        }

        List<Integer> outParamIds =  Splitter.on(",").splitToList(dynamicInterface.getOutParameter())
                .stream().map(Integer::parseInt).collect(Collectors.toList());

        return  null;
    }

    @Override
    public List<FaqDynamicInterface> selectByExample(FaqDynamicInterface record) {
        return interfaceMapper.selectByExample(record);
    }

    @Override
    public int countByExample(FaqDynamicInterface record) {
        return  interfaceMapper.countByExample(record);
    }

    @Override
    public List<FaqOption> initInterfacesOption() {
        List<FaqOption> options = new ArrayList<FaqOption>();
        List<FaqDynamicInterface> interfaces = interfaceMapper.selectByExample(new FaqDynamicInterface());
        if(!CollectionUtils.isEmpty(interfaces)){

            options = this.convertToFaqOptionList(interfaces);
        }
        return options;
    }

    @Override
    public List<FaqOption> getParameterChildrenById(FaqOptionDto dto) {
        List<FaqOption> options = new ArrayList<FaqOption>();


        if(UnitConstant.NUM_ONE.equals( dto.getLevel())){
            FaqDynamicInterface dynamicInterface =   interfaceMapper.selectByPrimaryKey(dto.getId());
            if(dynamicInterface!=null && StrUtil.isNotEmpty(dynamicInterface.getOutParameter())){
                List<Integer> outParamIds = Splitter.on(",").splitToList(dynamicInterface.getOutParameter())
                        .stream().map(Integer::parseInt).collect(Collectors.toList());
                List<FaqDynamicParameter> parameters = paramterService.selectDynamicParameterByIds(outParamIds);
                List<GroupCountVo> groupCountVos = paramterService.selectGroupCountByParentIds(outParamIds);
                options = this.convertToFaqOptionList(parameters, groupCountVos);
            }
        }
        // 查下面节点
        else {
            List<FaqDynamicParameter> parameters = paramterService.selectDynamicParameterByParentIds(Arrays.asList(dto.getId()));
            List<GroupCountVo> groupCountVos =new ArrayList<>();
            if(!CollectionUtils.isEmpty(parameters)){
                List<Integer> paramIds = parameters.stream().map(FaqDynamicParameter::getId).collect(Collectors.toList());
                groupCountVos = paramterService.selectGroupCountByParentIds(paramIds);
            }
            options = this.convertToFaqOptionList(parameters, groupCountVos);
        }

        return options;
    }

    @Override
    public FaqDynamicInterface selectById(Integer id) {
        return interfaceMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<FaqDynamicInterface> selectByIds(List<Integer> ids) {
        return interfaceMapper.selectByIds(ids);
    }

    @Override
    public List<FaqDynamicParameter> selectContextParameters(List<Integer> interfaceIds) {
        List<FaqDynamicParameter>   result = Lists.newArrayList();
        if(CollectionUtils.isEmpty(interfaceIds)){return  result;}

        List<FaqDynamicInterface> interfaces =  interfaceMapper.selectByIds(interfaceIds);
        List<String> inParamIdsStr=  interfaces.stream().map(FaqDynamicInterface::getInParameter).collect(Collectors.toList());
        List<Integer> inParamIds = Lists.newArrayList();
        inParamIdsStr.forEach(e->{
            inParamIds.addAll( Splitter.on(",").splitToList(e).stream().map(Integer::parseInt).collect(Collectors.toList()));
        });
        if(!CollectionUtils.isEmpty(inParamIds)) {
            result.addAll(paramterService.selectDynamicParameterByIds(inParamIds));
            result.addAll(paramterService.selectDynamicParameterChildren(inParamIds));
        }
        return result;
    }


    @Override
    public String excuteInterfaceRequest(Map<String, Object> param, FaqDynamicInterface dynamicInterface) throws Exception {
        boolean needBase64Encode = UnitConstant.NUM_ONE.equals( dynamicInterface.getInParameterNeedEncode());
        boolean needBase64Decode = UnitConstant.NUM_ONE.equals( dynamicInterface.getOutParameterNeedDecode());
        String result =   "";
            result =  tspClient.get(dynamicInterface.getAddress(), String.class, param, needBase64Encode, 5000, 5000);
            if(needBase64Decode) {
                result = StrUtil.str(Base64.getDecoder().decode(result), Charset.forName("UTF-8"));
            }

        return result;
    }

    @Override
    public int delInterfaceByIds(List<Integer> ids) {
       return interfaceMapper.deleteByIds(ids);
    }

    /**
     * @Description 组装第一层接口
     * @param interfaces
     * @return
     */
    public List<FaqOption>  convertToFaqOptionList(List<FaqDynamicInterface> interfaces) {
        List<FaqOption> options = new ArrayList<FaqOption>();
        if(CollectionUtils.isEmpty(interfaces)){return options;}
        for (FaqDynamicInterface e : interfaces){
               FaqOption option = new FaqOption();
                option.setLabel(e.getName());
                option.setValue(e.getId());
                option.setLeaf(false);
                option.setLevel(UnitConstant.NUM_ONE);
                //第一层字段
                option.setChildren(new ArrayList<>());
                options.add(option);
        }
        return  options;
    }

    public List<FaqOption> convertToFaqOptionList(List<FaqDynamicParameter> parameters ,List<GroupCountVo> groupCountVos) {
        List<FaqOption> options = new ArrayList<>();
        Map<Integer,Integer>  groupCountMap = new HashMap<>();



        if(CollectionUtils.isEmpty(parameters)){
            return options;
        }
        if(!CollectionUtils.isEmpty(groupCountVos)){
            groupCountMap= groupCountVos.stream().collect(Collectors.toMap(GroupCountVo::getId,GroupCountVo::getCount));
        }
        final Map<Integer,Integer>  finalGroupCountMap = new HashMap<>(groupCountMap);
        parameters.stream().forEach(e->{
            FaqOption option   = new FaqOption();
            if(StrUtil.isNotBlank(e.getDescription())){
                option.setLabel(e.getDescription());
            }else {
                option.setLabel(e.getFieldName());
            }
            option.setValue(e.getId());
            Integer childrenCount = finalGroupCountMap.get(e.getId());
            // 没有children 就是叶子节点
            option.setLeaf( !(childrenCount!=null&&childrenCount>UnitConstant.NUM_ZERO));
            options.add(option);
        });
        return  options;
    }


    @Override
    public int updateById(FaqDynamicInterface record) {
        return interfaceMapper.updateByPrimaryKeySelective(record);
    }
}
