package com.tuniu.mob.ocsfaq.faq.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.codehaus.jackson.annotate.JsonBackReference;

import java.util.List;
import java.util.Map;

/**
 * @description 百度unit 机器人请求参数
 * @Link https://ai.baidu.com/ai-doc/UNIT/qkpzeloou
 */

public class UnitRequest {

    /**
     * 必需 当前api版本对应协议版本号为3.0，固定值
     */
    private String version;

    /**
     * 可选 机器人ID，service_id 与skill_ids不能同时缺失，至少一个有值
     */
    @JSONField(name = "service_id")
    private String serviceId;

    /**
     * 可选	技能ID列表。我们允许开发者指定调起哪些技能。这个列表是有序的——排在越前面的技能，优先级越高。技能优先级体现在response的排序上。具体排序规则参见【应答参数说明】
     * service_id和skill_ids可以组合使用，详见【请求参数详细说明】
     */
    @JSONField(name = "skill_ids")
    private List<String> skillIds;
    /**
     * 必需	开发者需要在客户端生成的唯一id，用来定位请求，
     * 响应中会返回该字段。对话中每轮请求都需要一个log_id
     */
    @JSONField(name = "log_id")
    private String logId;

    /**
     * 必需	session保存机器人的历史会话信息，由机器人创建，客户端从上轮应答中取出并直接传递，不需要了解其内容。如果为空，则表示清空session（开发者判断用户意图已经切换且下一轮会话不需要继承上一轮会话中的词槽信息时可以把session置空，从而进行新一轮的会话）。
     * 开发者可以通过传送session_id的方式实现多轮对话。具体操作方式见【请求参数详细说明】
     */
    @JSONField(name = "session_id")
    private String sessionId;

    /**
     * 基本上只需要两个参数 terminal_id 客户端标识 faq的问题query 其他都不需要
     * object	必需	本轮请求体。
     * {
     * "terminal_id":"1234"必需	与技能对话的终端用户id（如果客户端是用户未登录状态情况下对话的，也需要尽量通过其他标识（比如设备id）来唯一区分用户），方便今后在平台的日志分析模块定位分析问题、从用户维度统计分析相关对话情况。详情见【请求参数详细说明】
     * "query":"你好" 必需	本轮请求query（用户说的话），详情见【请求参数详细说明】
     * "query_info":{ 可选	本轮请求query的附加信息
     * "type":"TEXT" 可选	请求信息类型，取值范围："TEXT","EVENT"。详情见【请求参数详细说明】
     * "source":"ASR" 可选	请求信息来源，可选值："ASR","KEYBOARD"。ASR为语音输入，KEYBOARD为键盘文本输入。针对ASR输入，UNIT平台内置了纠错机制，会尝试解决语音输入中的一些常见错误
     * "asr_candidates":[ 可选	请求信息来源若为ASR，该字段为ASR候选信息。（如果调用百度语音的API会有该信息，UNIT会参考该候选信息做综合判断处理。）
     * {
     * "text":"候选文本" 可选	语音输入候选文本
     * "confidence":55.77 可选	语音输入候选置信度，取值范围[0,100]
     * }
     * ]
     * }
     * }
     */
    private UnitRequestParam request;


    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public List<String> getSkillIds() {
        return skillIds;
    }

    public void setSkillIds(List<String> skillIds) {
        this.skillIds = skillIds;
    }

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public UnitRequestParam getRequest() {
        return request;
    }

    public void setRequest(UnitRequestParam request) {
        this.request = request;
    }
}
