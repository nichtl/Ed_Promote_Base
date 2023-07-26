package com.tuniu.mob.ocsfaq.faq.unit.enums;

public enum UnitEnum {

    CRM_GET_PHONE_BY_SOURCETYPE("http://crm.tuniu.org/muses-web-site/phone/getPhoneNumBySourceType.htm","根据sourceType获取手机号"),
    CRM_UOPDATE_PHONE_STATUS("http://crm.tuniu.org/muses-web-site/phone/updatePhoneStatus.htm","更新手机添加状态"),
    ROBOT_LIST("service/list", "查询机器人列列表"),
    ROBOT_ADD("service/add", " 新建机器人"),
    ROBOT_UPDATE("service/update", "修改机器人属性"),
    ROBOT_DELETE("service/delete", "删除机器人"),
    ROBOT_DETAIL("service/info", "查询机器人详情"),
    ROBOT_SKILL_LIST("service/listSkill", "查询机器人技能列列表"),
    ROBOT_SKILL_ADD("service/addSkill", "机器人添加技能"),
    ROBOT_SKILL_SORT("service/sortSkill", "修改技能优先级"),
    ROBOT_SKILL_REMOVE("service/deleteSkill", "移除技能"),
    DEVELOPER_SKILL_LIST("skill/list", "查询开发者的技能列列表"),
    DEVELOPER_SKILL_ADD("skill/add", "新建技能"),
    DEVELOPER_SKILL_UPDATE("skill/update", "修改技能的名称和属性"),
    DEVELOPER_SKILL_DELETE("skill/delete", "删除技能"),
    DEVELOPER_SKILL_DETAIL("skill/info", "查询开发者的指定技能详情"),
    FAQ_SKILL_LIST("faqskill/faqPair/list", "查询问答对列列表"),
    FAQ_SKILL_ADD("faqskill/faqPair/add", "新建问答对"),
    FAQ_SKILL_BATCH_ADD("faqskill/faqPair/batchAdd", "批量新建问答对"),
    FAQ_SKILL_INFO("faqskill/faqPair/info", "查询问答对详情"),
    FAQ_SKILL_UPDATE("faqskill/faqPair/update", "修改问答对详情"),
    FAQ_SKILL_BATCH_DELETE("faqskill/faqPair/batchDelete", "删除问答对(批量)"),
    FAQ_SKILL_SEARCH("faqskill/faqPair/search", "查找问答对"),
    FAQ_SKILL_MODEL_LIST("faqskill/model/list", "查询faq训练模型列表"),
    FAQ_SKILL_MODEL_TRAIN("faqskill/model/train", "训练模型"),
    FAQ_SKILL_MODEL_DELETE("faqskill/model/delete", "删除有效模型"),
    FAQ_SKILL_TAG_LIST("tag/list", "查询二级菜单绑定的技能下的标签"),
    FAQ_SKILL_TAG_ADD("tag/add", "向技能下新增标签"),
    FAQ_SKILL_TAG_BATCH_DELETE("tag/batchDelete", "批量删除"),
    PRODUCT_SEARCH("http://public-api.nj.dcb.tuniu.org/ror/category/ids/query","推荐产品详情搜索接口"),
    PRODUCT_RECOMMAND("http://crm.tuniu.org/muses-web-site/unit/getGuessMemberLikeProList.htm","产品推荐"),
    ;

    UnitEnum(String urlSuffix, String desc) {
        this.urlSuffix = urlSuffix;
        this.desc = desc;
    }

    private String urlSuffix;

    private String desc;

    public String getUrlSuffix() {
        return urlSuffix;
    }


    public String getDesc() {
        return desc;
    }


}
