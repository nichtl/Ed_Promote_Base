package com.tuniu.mob.ocsfaq.faq.unit.service.impl;
import javax.annotation.Resource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tuniu.mob.boot.tsp.TspClient;
import com.tuniu.mob.ocsfaq.faq.component.DynamicPostProcessComponent;
import com.tuniu.mob.ocsfaq.faq.component.DynamicPreProcessComponent;
import com.tuniu.mob.ocsfaq.faq.entity.FaqDynamicInterface;
import com.tuniu.mob.ocsfaq.faq.entity.FaqDynamicParameter;
import com.tuniu.mob.ocsfaq.faq.exception.FAQException;
import com.tuniu.mob.ocsfaq.faq.unit.dto.OutParameter;
import com.tuniu.mob.ocsfaq.faq.unit.enums.*;
import com.tuniu.mob.ocsfaq.faq.unit.service.*;
import com.tuniu.mob.ocsfaq.faq.vo.DefaultMemberAccess;
import com.tuniu.mob.ocsfaq.faq.vo.ParamParserNode;
import com.tuniu.mob.ocsfaq.utils.StringUtils;
import ognl.DefaultClassResolver;
import ognl.DefaultTypeConverter;
import ognl.Ognl;
import ognl.OgnlContext;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.w3c.dom.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description
 * @Date 2022/11/28
 */
@Service
public  class DynamicFaqServiceImpl  implements IDynamicFaqService  {
    @Resource
    IFaqParamterService paramterService;

    @Resource
    IFaqInterfaceService interfaceService;


    @Autowired
    TspClient tspClient;

    @Autowired
    DynamicPostProcessComponent dynamicePostProcessComponent;

    @Autowired
    DynamicPreProcessComponent  dynamicPreProcessComponent;

    public   final  static  Map<Object,Object>  recordMap = new HashMap<>();


    /**
     * @desc 由于 带转义字符或者格式的html 不是一个合法的xml 使用原始xml 解析会报错 改为jsoup
     * @param content  解析富文本string 替换带有动态参数的内容 再 转为回文本
     * eg : <p><span dynamic_interface_id="1"  dynamic_outparam_ids="4,8,9" dynamic_outparam_desc="酒店电话" dynamic_placeholder="#{测试接口.data.d.d1}" > #{测试接口.data.d.d1}</span></p>
     * @param param 顶层接口传入参数
     * @return
     * @throws Exception
     */
    @Override
    public  String convertDynamicContent(String content, Map<String,Object> param)  throws  Exception{
        recordMap.clear();
        // String content=" <p><span contextparaminterfaceid=\"1\"  contextoutparamids=\"4,8,9\"> #{测试接口.data.d.d1}</span> <i> tesgsj </i></p>";
        // 创建Document对象
        //content 带转义字符就报错了
        org.jsoup.nodes.Document doc = Jsoup.parseBodyFragment(content);
        Elements elements =  doc.getElementsByAttribute("dynamic_interface_id");

        if(CollectionUtils.isEmpty(elements)){
            throw  new FAQException("not found dynamic visible node content = "+content+" param="+JSON.toJSONString(param));

        }
        for (int i = 0; i <elements.size() ; i++) {

            org.jsoup.nodes.Element element = elements.get(i);
            Attributes attributes =  element.attributes();
            if(!attributes.isEmpty()) {
                String dynamic_interface_id = attributes.get("dynamic_interface_id");
                String dynamic_outparam_ids = attributes.get("dynamic_outparam_ids");
                String dynamic_outparam_desc= attributes.get("dynamic_outparam_desc");
                String dynamic_placeholder  = attributes.get("dynamic_placeholder");
                //缺少参数的隐藏
                if(StrUtil.isBlank(dynamic_interface_id) || StrUtil.isBlank(dynamic_outparam_ids) || StrUtil.isBlank(dynamic_outparam_desc) ){
                    throw  new FAQException("attr node value is empty  dynamicContent= "+content +"--- node = "+ JSON.toJSONString(element));
                    // element.setAttribute("style","display: none;"); 动态答案不适合隐藏变量占位符，因为一旦确少某个答案 整个faq答案可能看起来不连贯
                }

                /** 这块很可能会出问题 修改动态答案时不断删除新增动态变量 由于tinymce富文本 编辑器动态html 会保留之前的 html 结构,相当于在清空原动态节点代码里的占位内容,在该元素下追加新元素
                 * 就会出现 异常的结构
                 *  正常 结构
                 *  <span dynamic_interface_id="1"  dynamic_outparam_ids="4,8,9" dynamic_outparam_desc="酒店电话" dynamic_placeholder="#{测试接口.data.d.d1}" > #{测试接口.data.d.d1}</span>
                 *  解析出
                 *  element(span )
                 *        -> textNode(text_value = #{测试接口.data.d.d1} )
                 *
                 * 异常结构
                 * <span dynamic_interface_id="1"  dynamic_outparam_ids="4,8,9" dynamic_outparam_desc="酒店电话" dynamic_placeholder="#{测试接口.data.d.d1}" >
                 *
                 *   <span dynamic_interface_id="1"  dynamic_outparam_ids="4,8,9" dynamic_outparam_desc="酒店电话" dynamic_placeholder="#{测试接口.data.d.d1}" > #{测试接口.data.d.d1}</span>
                 * </span>
                 * 解析出
                 * element(span )
                 *           -> element(span )
                 *                   -> textNode(text_value = #{测试接口.data.d.d1} )
                 *  在同一个编辑器中 不断重新删除新增动态节点 会使嵌套层级 不断变深
                 *
                 *  最好把动态答案 全删除掉再重新插入 再看下动态答案的html层级
                 *
                 *  --- 前端编辑器 已将变量占位符设置为不可编辑
                 * */
                TextNode dynamicContentNode  =  (TextNode)element.firstChild();
                // 正常的动态变量节点 里面应该存在一个textnode 值为  #{测试接口.data.d.d1}
                if( dynamicContentNode == null ){
                    throw  new FAQException(" dynamicContentNode textNode  is empty   content= "+content+"param="+JSON.toJSONString(param));
                }
                // ids 逗号分割
                List<String>  finalOutParamIds = StringUtils.strToString2List(dynamic_outparam_ids);
                String finalOutParamId = finalOutParamIds.get(finalOutParamIds.size()-1);;
                FaqDynamicParameter parameter =  paramterService.selectDynamicParameterById(Integer.parseInt(finalOutParamId));

                applyDynamicBeforeHandle(element,parameter,param);
                /**
                 * 根据参数拿到接口出参
                 */
                OutParameter outParameterValue =  this.getOutParameter(Integer.valueOf(dynamic_interface_id),dynamic_outparam_ids,param);

                applyDynamicPostHandle(element,outParameterValue,parameter,param);
                //取值有问题 直接异常
                if( StrUtil.isBlank(outParameterValue.getValue()) ){
                    throw  new FAQException("getOutParameter return null   content= "+content+"param="+JSON.toJSONString(param));
                }
                else {
                    // 替换掉变量占位符
                    String dynamicPlaceHolder = dynamicContentNode.text();

                    String descWithParamValue = dynamic_outparam_desc  +": " + outParameterValue.getValue();

                    String relacePlaceHolderText = dynamicPlaceHolder.replace(dynamic_placeholder,descWithParamValue);

                    dynamicContentNode.text(relacePlaceHolderText);
                    attributes.put("dynamic_value",outParameterValue.getValue());

                    // 移除掉 接口id  出参id
                    attributes.remove("dynamic_interface_id");
                    attributes.remove("dynamic_outparam_ids");
                }
            }
            else{
                //找到了待处理的的动态节点 但是节点属性上携带的数据 丢失
                throw  new FAQException("there has found dynamic visible node but node attributes not found  attr_data= "+content +"--- node = "+ JSON.toJSONString(element));
            }
        }

        /**
         * doc.body().toString()
         * 带有body标签
         * <body>
         *     <p><span dynamic_interface_id="1"  dynamic_outparam_ids="4,8,9" dynamic_outparam_desc="酒店电话"> 酒店电话: +86-12345678 </span></p>
         * </body>
         * 替换掉 body tag
         */

        String docBodyStr = doc.body().toString();

        docBodyStr = docBodyStr.replaceAll("<body>\n|</body>","");
        return docBodyStr;
    }

    public OutParameter getOutParameter(Integer interfaceId , String outParamIds, Map<String,Object> param)  throws  Exception{
        /**
         * 上下文接口 若当前接口参数来源包含另外接口的出参
         * 需要控制请求顺序 一般都不会超过2个
         **/
        Stack<List<Integer>> interfaceStack = new Stack<>();
        FaqDynamicInterface dynamicInterface = null;
        dynamicInterface = interfaceService.selectById(interfaceId);
        List<FaqDynamicParameter> outParameter = paramterService.selectDynamicParameterByIds(this.convertToIntegerList(this.convertToList(outParamIds,",")));
        Map<Integer,Object> contextInterfaceParam = Maps.newHashMap();
        if (dynamicInterface == null){
            return  null;
        }
        /**
         *先将当前请求压在栈底
         */
        interfaceStack.add(Arrays.asList(dynamicInterface.getId()));
        /**
         * 接口入参可以为空
         * 若不为空构造上下文接口请求顺序
         * 不为空需要看参数是否包含上文接口出参  或者 请求栈顶的接口的参数是否存在
         */
        if(StrUtil.isNotBlank( dynamicInterface.getInParameter() )) {
            List<Integer> inParamIds = Splitter.on(",").splitToList(dynamicInterface.getInParameter())
                    .stream().map(Integer::parseInt).collect(Collectors.toList());
            List<FaqDynamicParameter> inParameters = paramterService.selectDynamicParameterByIds(inParamIds);

            List<FaqDynamicParameter> parmaChildren = paramterService.selectDynamicParameterChildren(inParamIds);
            inParameters.addAll(parmaChildren);

            this.getContextInterfaceStack(Maps.newHashMap(),inParameters,interfaceStack);
        }

        recordMap.put("OnglExpress",this.createOgnlExpression(outParameter,","));
        /**
         * 请求栈
         *
         */
        while ( !interfaceStack.empty() ){
            List<FaqDynamicInterface> interfaces = interfaceService.selectByIds(interfaceStack.pop());
            recordMap.put("interfaces =" +RandomUtil.randomString(5),interfaces);
            for (FaqDynamicInterface var1 : interfaces) {

                List<Integer> inParamIds = Splitter.on(",").splitToList(var1.getInParameter())
                        .stream().map(Integer::parseInt).collect(Collectors.toList());
                List<FaqDynamicParameter> inParameters  =  paramterService.selectDynamicParameterByIds(inParamIds);
                Map<String,Object> requestParam  =  this.assembleParameters(param,inParameters,contextInterfaceParam);
                JSONObject  response =  this.executeRequest(requestParam,var1);
                if(response!=null) {
                    contextInterfaceParam.put(var1.getId(), response);
                }
            }

        }

        Object  interfaceResponse =   contextInterfaceParam.getOrDefault(interfaceId,null);
        recordMap.put("interfaceResponse",interfaceResponse);
        String  strValue  = this.parserOutParamWhitResponse((JSONObject) interfaceResponse,this.createOgnlExpression(outParameter,","));


        if( StrUtil.isBlank(strValue) ) {
            throw  new FAQException(" null val = " +JSON.toJSONString(interfaceResponse) +"----"+JSON.toJSONString(this.createOgnlExpression(outParameter,",")));
        }

        return   new OutParameter(strValue)  ;

    }


    /**
     *  @desc 由于 带转义字符或者格式的html 不是一个合法的xml 使用原始xml 解析会报错 改为jsoup
     * @param content
     * @param param
     * @return
     * @throws Exception
     */
    @Deprecated
    public  String xmlParserConvertDynamicContent(String content, Map<String,Object> param)  throws  Exception{

        // String content=" <p><span contextparaminterfaceid=\"1\"  contextoutparamids=\"4,8,9\"> #{测试接口.data.d.d1}</span> <i> tesgsj </i></p>";
        // 创建Document对象
        //content 带转义字符就报错了
        content = content.replaceAll("\\&nbsp\\;"," ");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setValidating(false);
        DocumentBuilder db = dbf.newDocumentBuilder();
        InputStream resourceAsStream = new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));
        Document doc= db.parse(resourceAsStream);

        // 创建XPath对象 解析并替换
        XPathFactory factory = XPathFactory.newInstance();
        XPath xpath = factory.newXPath();
        NodeList  nodeList =  (NodeList) xpath.compile("//span[@dynamic_interface_id]").evaluate(doc,XPathConstants.NODESET);

        if(nodeList ==null || nodeList.getLength()==0){
            throw  new FAQException(" empty  NodeList  content= "+content+" param="+JSON.toJSONString(param));

        }
        for (int i = 0,len= nodeList.getLength(); i <len ; i++) {
            Element element = (Element) nodeList.item(i);
            Attr interFaceIdNode  =  (Attr) element.getAttributes().getNamedItem("dynamic_interface_id");
            Attr outParamNode = (Attr) element.getAttributes().getNamedItem("dynamic_outparam_ids");
            Attr outparamDescNode =(Attr) element.getAttributes().getNamedItem("dynamic_outparam_desc");
            //缺少参数的隐藏
            if(interFaceIdNode == null  || outParamNode ==null || outparamDescNode ==null ||StrUtil.isBlank(interFaceIdNode.getValue()) || StrUtil.isBlank(outParamNode.getValue()) || StrUtil.isBlank(outparamDescNode.getValue()) ){
                throw  new FAQException("attr node value is empty  dynamicContent= "+content +"--- node = "+ interFaceIdNode.getValue());
                // element.setAttribute("style","display: none;"); 动态答案不适合隐藏，因为一旦确少某个答案 整个faq答案可能看起来不连贯
            }

            /**
             * 根据参数拿到接口出参
             */
            OutParameter outParameterValue = this.getOutParameter(Integer.valueOf(interFaceIdNode.getValue()),outParamNode.getValue(),param);
            //取值有问题 直接异常
            if(StrUtil.isBlank(outParameterValue.getValue())){
                throw  new FAQException("getOutParameter return null content= "+content+"param="+JSON.toJSONString(param));
            }else {
                String textContent = element.getTextContent();

                String descWithoutParamValue = outparamDescNode.getValue() +": " + outParameterValue.getValue();

                element.setTextContent( descWithoutParamValue);
            }
        }

        // dom转回文本
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING, StandardCharsets.UTF_8.toString());
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.METHOD, "html");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        transformer.transform(new DOMSource(doc), new StreamResult(outputStream));
        String docStr = outputStream.toString("UTF-8");
        recordMap.clear();
        return docStr;
    }


    /**
     * @Description 由于接口的入参可以使用另外接口的出参做入参，需要重复递归来构造上下文接口的请求栈
     * @param contextParameters
     * @param interfaceIdStack
     */
    public void getContextInterfaceStack( Map<Integer,Object> visitedMap,List<FaqDynamicParameter> contextParameters,Stack<List<Integer>> interfaceIdStack ){
        boolean existContextParam  = contextParameters.stream().anyMatch(e-> DynamiceEnum.CONTEXT_OUT_PARAM.getCode().equals(e.getDataSourceType()) && !visitedMap.containsKey(e.getContextInterfaceId()));
        if(CollectionUtil.isEmpty(contextParameters) || !existContextParam ){
            return  ;
        }
        //上文接口
        List<Integer> preContextInterfaceId = contextParameters.stream()
                                                               .filter(e->DynamiceEnum.CONTEXT_OUT_PARAM.getCode().equals(e.getDataSourceType()) && !visitedMap.containsKey(e.getContextInterfaceId()) )
                                                               .map(FaqDynamicParameter::getContextInterfaceId).collect(Collectors.toList());
       if(!CollectionUtils.isEmpty(preContextInterfaceId)) {
           interfaceIdStack.add(preContextInterfaceId);
           preContextInterfaceId.forEach(e -> visitedMap.put(e, true));
       }
        // 上文接口入参
        List<FaqDynamicParameter> preContextInterfaceInParameter = interfaceService.selectContextParameters(preContextInterfaceId);
        this.getContextInterfaceStack(visitedMap,preContextInterfaceInParameter,interfaceIdStack);
    }

    /**
     *
     * @param param
     * @return
     */
    public  JSONObject  executeRequest(Map<String,Object> param, FaqDynamicInterface dynamicInterface) throws Exception {
        JSONObject  response = null;
        boolean needBase64Encode = UnitConstant.NUM_ONE.equals( dynamicInterface.getInParameterNeedEncode());
        boolean needBase64Decode = UnitConstant.NUM_ONE.equals( dynamicInterface.getOutParameterNeedDecode());
        if(  StrUtil.isNotEmpty(dynamicInterface.getInParameter())&& CollectionUtil.isEmpty(param)){return response;}

        switch(DynamiceEnum.getDynamiceEnumByCodeAndType(dynamicInterface.getType(),"REQUEST_TYPE")){
            case  HTTP :
                HttpResponse  httpResponse =null;
                if(DynamiceEnum.GET.getCode().equals(dynamicInterface.getMethodType())){
                    dealGetUrlObjectParam(param);
                    if(needBase64Encode){
                        httpResponse = HttpRequest.get(dynamicInterface.getAddress()).body(Base64.encode(JSON.toJSONString(param))).timeout(2000).execute();

                    }else {
                        httpResponse = HttpRequest.get(dynamicInterface.getAddress()).form(param).timeout(2000).execute();
                    }
                }
                if(DynamiceEnum.POST.getCode().equals(dynamicInterface.getMethodType())){
                    String postParam =JSON.toJSONString( param);
                    if(needBase64Encode){
                       postParam = Base64.encode(postParam.getBytes());
                    }
                    recordMap.put("postParam",postParam);
                    httpResponse=  HttpRequest.post(dynamicInterface.getAddress()).timeout(2000).body(postParam).execute();
                }
                if( httpResponse !=null && httpResponse.getStatus()==200 ){
                    if(needBase64Decode){
                        response    = JSONObject.parseObject(Base64.decodeStr(httpResponse.body(),StandardCharsets.UTF_8));
                    }else{
                        response    = JSONObject.parseObject(httpResponse.body());

                    }
                }else{
                    throw  new FAQException("null response "+JSON.toJSONString(httpResponse)+"-----"+JSON.toJSONString(dynamicInterface)+"---"+JSON.toJSONString(param));
                }

                recordMap.put("executeRequest",httpResponse);
                break;
            case  TSP:
                String result =   "";


                if(DynamiceEnum.GET.getCode().equals(dynamicInterface.getMethodType())) {
                    result = tspClient.get(dynamicInterface.getAddress(), String.class, param, needBase64Encode, 3000, 3000);

                }
                else if(DynamiceEnum.POST.getCode().equals(dynamicInterface.getMethodType())) {
                    result = tspClient.post(dynamicInterface.getAddress(), String.class, param, needBase64Encode, 3000, 3000);

                }else{
                    result = tspClient.get(dynamicInterface.getAddress(), String.class, param, needBase64Encode, 3000, 3000);
                }

                if(needBase64Decode){
                   result =  Base64.decodeStr(result,StandardCharsets.UTF_8);
                }

                 if(StrUtil.isNotBlank(result)){
                     response = JSONObject.parseObject(result);
                 }
                 recordMap.put("executeRequest",response);
                break;
            default:
                break;
        }


        return response;
    }

    /**
     * 组装参数 参数可能为上文接口出参 确保接口请求顺序
     * @param  params 顶层 controller调用时透传   eg "producId"：123
     * @param  inParameters 当前需要组装参数的接口的入参
     * @param  contextParam 上文接口缓存  接口id ：JsonObJect
     * * */
    public Map<String,Object>  assembleParameters(Map<String,Object> params, List<FaqDynamicParameter> inParameters,Map<Integer,Object> contextParam ) throws  Exception{
        Map<String,Object> result =  new HashMap<String,Object>();
        if(CollectionUtil.isEmpty(inParameters)){return  result;}
        recordMap.put("contextParam",contextParam);
        recordMap.put("param",params);

        for (FaqDynamicParameter parameter : inParameters) {
            Integer childrenCount = paramterService.countDynamicParameterByParentIds(parameter.getId());
            // 如果这个字段下的children 不为空就表示当前这个入参是对象
            if(childrenCount!=null && childrenCount>0){
                List<FaqDynamicParameter> childrenParameter = paramterService.selectDynamicParameterByParentIds(Arrays.asList(parameter.getId()));
                Map<String,Object> val = this.assembleParameters(params,childrenParameter,contextParam);
                if(CollectionUtil.isNotEmpty(val)){
                    result.put(parameter.getFieldName(),val);
                }
                continue;
            }
            recordMap.put("current_param",parameter);
            //  若为固定值
            if(DynamiceEnum.CONST_VALUE.getCode().equals( parameter.getDataSourceType() )){
                // 针对可选择的固定值选项特定取值
                handelSpecialConstValue(parameter);

                Object constValue = ConvertEnum.convertTypeByCodeWithDefaultNull(parameter.getFieldType(),parameter.getConstValue());
                if(constValue == null && parameter.getRequired()){
                    throw  new FAQException(" assembleParameters constValue类型转换失败 parameter={}"+JSON.toJSONString(recordMap));
                }
                result.put(parameter.getFieldName(),constValue );
                continue;
            }
            // 接口传入 即在参数透传的param里面
            if(DynamiceEnum.INTERFACE_PARAM.getCode().equals(parameter.getDataSourceType()) ){
                Object  fieldValue =  params.get(parameter.getFieldName());
                //已包含入参 字段名称不同可根据别名取 别名可以有多个逗号分割
                if(fieldValue==null && StrUtil.isNotBlank(parameter.getFieldAlias())){
                   List<String> aliasList =  Splitter.on(",").splitToList(parameter.getFieldAlias());

                   for(String alias:aliasList){
                      if(params.containsKey(alias)){
                          fieldValue=params.get(alias);
                      }
                   }
                }
                if(fieldValue == null && parameter.getRequired()){
                    throw  new FAQException("assembleParameters fieldValue接口入参不能为空 "+JSON.toJSONString(recordMap) );
                }
                Object convertTypeValue = ConvertEnum.convertTypeByCodeWithDefaultNull(parameter.getFieldType(),String.valueOf(fieldValue));
                if(convertTypeValue == null ){
                    throw  new FAQException(" assembleParameters fieldValue类型转换失败 parameter={}"+JSON.toJSONString(recordMap) );
                }
                result.put(parameter.getFieldName(),convertTypeValue);
                continue;
            }
            // 上文接口出参  需要上文接口先请求 将response 放入contextParam 带进来
            if(DynamiceEnum.CONTEXT_OUT_PARAM.getCode().equals(parameter.getDataSourceType())){
                Object contextOutParam = contextParam.get(parameter.getContextInterfaceId());
                if(contextOutParam == null && parameter.getRequired()){
                    throw  new FAQException("assembleParameters contextOutParam接口上文出参不能为空 "+JSON.toJSONString(contextParam)+"---"+JSON.toJSONString(parameter) );
                }
                List<String> outParamsIds  = Splitter.on(",").splitToList( parameter.getContextInterfaceOutParam());
                List<FaqDynamicParameter> outParameters =  paramterService.selectDynamicParameterByIds(this.convertToIntegerList(outParamsIds));
                List<String> expressionList = this.createOgnlExpression(outParameters,",");
                String outParamValues = this.parserOutParamWhitResponse((JSONObject) contextOutParam,expressionList);
                Object convertTypeValue = ConvertEnum.convertTypeByCodeWithDefaultNull(parameter.getFieldType(),outParamValues);
                if(convertTypeValue == null ){
                    throw  new FAQException(" assembleParameters outParamValues接口上文出参类型转换失败 "+JSON.toJSONString(recordMap));
                }
                result.put(parameter.getFieldName(),convertTypeValue);

            }
        }
        recordMap.put("assemmbleParam",result);
        return  result;
    }
    /**
     * parserOutParamWhitResponse
     */
    public String parserOutParamWhitResponseV2(JSONObject jsonObject,ParamParserNode parserNode) throws  Exception {

        Object ans = null;

       // ParamParserNode parserNode = createExpressionChain(parameters);


        if(parserNode == null ){
            return  null;
        }


        while (parserNode!=null){


//            JSONObject  curValueObject =  jsonObject.getJSONObject(parserNode.getExpression());
//
//            if(curValueObject.)
//
//            if(expressValue  == null){
//                parserNode = null;
//
//                break;
//            }


        }










     return  null;

    }



    public  List<String> parserArrayObjectValue(JSONArray  object , ParamParserNode   parserNode) throws Exception{


       StringBuilder  parserResults =   new StringBuilder();


        if(object == null || object.size() <=0  ||  parserNode == null ){
            return  null;
        }

        OgnlContext ognlContext = (OgnlContext) Ognl.createDefaultContext(object,new DefaultMemberAccess(true),new DefaultClassResolver(),new DefaultTypeConverter());
        ognlContext.setRoot(object);
        ognlContext.put("params",object);

        StringBuilder  resultData = new StringBuilder();
        StringBuilder  appendExpression   = new StringBuilder();

        for (int i = 0; i < object.size(); i++) {
              JSONObject arrObj = object.getJSONObject(i);



        }









    return  null;
    }



    public ParamParserNode createExpressionChain(List<FaqDynamicParameter> parameters ) {


           ParamParserNode parserChainHead = null;

           if(CollectionUtil.isEmpty(parameters)){ return  parserChainHead;}

             ParamParserNode pre = null;


           for(FaqDynamicParameter parameter : parameters ){
               ParamParserNode node = new ParamParserNode();

                 node.setExpression( parameter.getFieldName());

                 node.setFieldType(parameter.getFieldType());

                 node.setOperateType(parameter.getFieldOperateType());

                 if(pre!= null){
                     node.setPreNode(pre);
                     pre.setNextNode(node);
                 }else{
                     parserChainHead = node;
                 }

                 pre = node ;
           }
           if(pre!=null){
               pre.setEnd(true);
           }

           return  parserChainHead;


    }

    /**
     *
     * @param jsonObject response JSON object
     * @param ognlExpressionList 完整表达式拆分列表
     * @return
     */
    public  String parserOutParamWhitResponse( JSONObject jsonObject  , List<String> ognlExpressionList) throws  Exception{
        Object ans = null;
        if(jsonObject==null || CollectionUtil.isEmpty(ognlExpressionList) || ognlExpressionList.stream().anyMatch(StrUtil::isBlank)) {
            return  null;
        }

        OgnlContext ognlContext = (OgnlContext) Ognl.createDefaultContext(jsonObject,new DefaultMemberAccess(true),new DefaultClassResolver(),new DefaultTypeConverter());
        ognlContext.setRoot(jsonObject);
        ognlContext.put("params",jsonObject);

        /**
         * 1 #params.get('data')
         * 2 .get('cc')
         * 3 .get('c1')  将 表达式拆分 取值判断null 再拼接
         * final realExpress = #params.get('data').get('cc').get('c1')
         */
        StringBuilder realExpress = new StringBuilder();
        for (String expression: ognlExpressionList) {
            realExpress.append(expression);
            Object expressValue =   Ognl.getValue(Ognl.parseExpression(realExpress.toString()), ognlContext, ognlContext.getRoot());
            if(expressValue  == null ||  (expressValue instanceof JSONArray  && ((JSONArray) expressValue).isEmpty()) ) {
                ans =null;
                break;
            }else{ans =expressValue;}
        }
        return ans != null ?String.valueOf( ans):null;
    }

    /**
     * @Description 根据传入的出参字段名称 拼接ognl取值表达式
     * @param outParams
     * @param splitStr
     * @return
     */
    public  List<String>  createOgnlExpression(List<FaqDynamicParameter> outParams,String splitStr){
        List<String> ognlExpressionList = new ArrayList<String>();
        if(CollectionUtil.isEmpty(outParams)) {return  ognlExpressionList;}

        List<FaqDynamicParameter> outParamStrList = new ArrayList<>(outParams);
        if(CollectionUtil.isEmpty(outParamStrList)) {return  ognlExpressionList;}
        StringBuilder sb = new StringBuilder();
        sb.append("#params");
        sb.append(".get(")
                .append("\"")
                .append(outParamStrList.get(0).getFieldName() )
                .append("\"")
                .append(")");
        ognlExpressionList.add(sb.toString());
        outParamStrList.remove(0);
        if(CollectionUtil.isNotEmpty(outParamStrList)) {
            outParamStrList.forEach(e -> {
                String expression = ".get(" + "\"" + e.getFieldName() + "\"" + ")";
                ognlExpressionList.add(expression);

                // 若为数组 取第一个
                if(UnitConstant.NUM_FOUR.equals(e.getFieldType())){
                    ognlExpressionList.add(".get(0)");
                }

            });
        }
        return  ognlExpressionList;
    }


    public  List<Integer> convertToIntegerList(List<String> stringList){
        if(CollectionUtil.isEmpty(stringList)){return Lists.newArrayList();}
        return   stringList.stream().map(Integer::parseInt).collect(Collectors.toList());
    }

    public  List<String> convertToList(String string,String splitChar){
        if(StrUtil.isBlank(string)){return Lists.newArrayList();}
        return   Splitter.on(splitChar).splitToList(string);
    }

    /**
     * @Description 如果固定值 从 固定值选项里选择的 比如当天日期 则此时的 constValue 是枚举值 'CURRENT_DATE'
     * 需要特定处理替换为当前日期
     * @param parameter
     */
    void handelSpecialConstValue(FaqDynamicParameter parameter){
        SpecialConstEnum specialConstEnum =   SpecialConstEnum.getSpecialConstEnum(parameter.getConstValue());
        if(specialConstEnum!=null){
            parameter.setConstValue(specialConstEnum.handle());
        }
    }
    /**
     *   http get
     * 对于assembleParameters封装的 参数 若 value 为 map类型 需要转为json
     */
    void  dealGetUrlObjectParam(Map<String,Object> params){
        for(Map.Entry<String,Object>  entry:params.entrySet()){
            String key = entry.getKey();
            Object value = entry.getValue();
            if(value instanceof Map){
                params.put(key,JSON.toJSONString(value));
            }
        }
    }


    private void applyDynamicPostHandle(org.jsoup.nodes.Element element, OutParameter outParameterValue, FaqDynamicParameter parameter,Map<String, Object> param) throws Exception{
        Map<String, IDynamicPostProcessor> dynamicPostProcessorMap =  dynamicePostProcessComponent.getDynamicPostProcessorMap();
           if(CollectionUtil.isNotEmpty(dynamicPostProcessorMap)) {
               for (Map.Entry<String, IDynamicPostProcessor> postProcessorEntry:dynamicPostProcessorMap.entrySet()) {
                    postProcessorEntry.getValue().dynamicPostHandle(element, outParameterValue,parameter, param);

               }
           }

    }


    private  void applyDynamicBeforeHandle(org.jsoup.nodes.Element element ,FaqDynamicParameter parameter,Map<String,Object> param) throws Exception{

        Map<String, IDynamicBeforeProcessor> beforeProcessorMap = dynamicPreProcessComponent.getBeforeProcessorMap();
        if(CollectionUtil.isNotEmpty(beforeProcessorMap)){
            for (Map.Entry<String, IDynamicBeforeProcessor> preProcessorEntry:beforeProcessorMap.entrySet()) {
                preProcessorEntry.getValue().dynamicPreHandle(element, parameter,param);

            }
        }


    }















}
