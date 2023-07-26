package com.tuniu.mob.ocsfaq.faq.unit.enums;

public enum ActionTypeEnum {
    FAILURE("failure", "理解失败", 0),
    GUIDE("guide", "引导到对话意图", 1),
    SATISFY("satisfy", "满足", 2),
    FAQGUIDE("faqguide", "引导到问答意图", 3),
    CLARIFY("clarify", "澄清", 4),
    UNDERSTOOD("understood", "理解达成，注：内部使用", 5),
    CHAT("chat", "聊天话术", 6),
    EVENT("event", "触发事件，在答复型对话回应中选择了执行函数，将返回event类型的action", 7),
    PRODUCT_RECOMMAND("product_recommand ","命中产品推荐分支 走产品推荐",8 )

    ;
    private String actionName;

    private String desc;

    private Integer actionNum;

    ActionTypeEnum(String actionName, String desc, Integer actionNum) {
        this.actionName = actionName;
        this.desc = desc;
        this.actionNum = actionNum;
    }

    public Integer getActionNum() {
        return actionNum;
    }

    public void setActionNum(Integer actionNum) {
        this.actionNum = actionNum;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
