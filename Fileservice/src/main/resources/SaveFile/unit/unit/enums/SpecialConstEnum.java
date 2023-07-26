package com.tuniu.mob.ocsfaq.faq.unit.enums;

import cn.hutool.core.date.DateUtil;

import java.util.Date;

public enum SpecialConstEnum {
    CURRENT_DATE("CURRENT_DATE","返回具体当前日期"){
        @Override
        public String handle() {
            return  DateUtil.today();
        }
    },
    CURRENT_DATE_ADD_1("CURRENT_DATE_ADD_1","返回具体当前日期加1"){
        @Override
        public String handle() {
            return  DateUtil.format( DateUtil.offsetDay(new Date(),1), "YYYY-MM-dd");
        }
    },
    CURRENT_DATE_ADD_2("CURRENT_DATE_ADD_2","返回具体当前日期加2"){
        @Override
        public String handle() {
            return  DateUtil.format( DateUtil.offsetDay(new Date(),2), "YYYY-MM-dd");
        }
    },
    CURRENT_DATE_ADD_3("CURRENT_DATE_ADD_3","返回具体当前日期加3"){
        @Override
        public String handle() {
            return  DateUtil.format( DateUtil.offsetDay(new Date(),3), "YYYY-MM-dd");
        }
    }

    ;

    private  String  value;

    private  String  desc;

    public   abstract String handle() ;

    SpecialConstEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static SpecialConstEnum getSpecialConstEnum(String value) {
        SpecialConstEnum res = null;

        for (SpecialConstEnum e : SpecialConstEnum.values()){
            if(e.getValue().equals(value)){
                res = e;
                break;
            }
        }
        return res;
    }
}
