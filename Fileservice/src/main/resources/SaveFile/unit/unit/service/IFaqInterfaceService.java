package com.tuniu.mob.ocsfaq.faq.unit.service;

import com.alibaba.fastjson.JSONObject;
import com.tuniu.mob.ocsfaq.faq.entity.FaqDynamicInterface;
import com.tuniu.mob.ocsfaq.faq.entity.FaqDynamicParameter;
import com.tuniu.mob.ocsfaq.faq.vo.FaqOption;
import com.tuniu.mob.ocsfaq.faq.vo.FaqOptionDto;
import com.tuniu.mob.ocsfaq.faq.vo.InterfaceFormDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IFaqInterfaceService {

    /**
     * 新增接口配置信息
     * @param dto
     * @return 0 失败 1成功
     */
     int insertInterfaceInfoSelective(InterfaceFormDto dto);



     int updateById(FaqDynamicInterface record);

     List getOutParameterByInterfaceId(Integer interfaceId);



     List<FaqDynamicInterface> selectByExample(FaqDynamicInterface record);

    int  countByExample(FaqDynamicInterface record);


    /**
     * @Description 查接口信息一级
     * @return
     */
     List<FaqOption>  initInterfacesOption();

    /**
     * @Description 获取子节点 option
     */
     List<FaqOption>   getParameterChildrenById(FaqOptionDto dto);

     FaqDynamicInterface  selectById(Integer id);


     List<FaqDynamicInterface>  selectByIds(List<Integer> ids);

    /**
     * @Description 查出接口下 所有入参
     * @return
     */
     List<FaqDynamicParameter> selectContextParameters(List<Integer> ids);

    /**
     * @todo pebble的rpc 比较麻烦暂时先不管 现在也没有用到
     * 可使用 pebble的 mock api
     *  http://admin.pebble.tuniu.org/pebble-web/mock/proxy/invoke post
     * {"serviceName":"com.tuniu.crm.svr.RobotCallCustWhiteListService","method":"deleteWhiteSaler","version":"1.1.65-SNAPSHOT","body":"{\n  \"ids\": [9999]\n}","headers":{},"queryParams":null}
     * @param requestParam
     * @param dynamicInterface
     * @return 请求 接口返回的
     */
    String excuteInterfaceRequest(Map<String,Object> requestParam, FaqDynamicInterface dynamicInterface) throws  Exception;


    int delInterfaceByIds(List<Integer> ids);

}
