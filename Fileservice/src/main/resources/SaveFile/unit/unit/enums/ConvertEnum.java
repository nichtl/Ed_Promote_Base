package com.tuniu.mob.ocsfaq.faq.unit.enums;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.NumberUtil;

import java.util.Date;


public enum ConvertEnum {
    CONVERT_TO_STRING(1,"转为String"){
        @Override
        public String convert(String param) {
            return  Convert.convert(String.class, param,null);
        }
    },
    CONVERT_TO_LONG(2,"转为Long"){
        @Override
        public Object convert(String param) {
            if(!NumberUtil.isLong(param)){return  null;}
            return  Convert.convert(Long.class, param,null);
        }
    },
    CONVERT_TO_DOUBLE(3,"转为Double"){
        @Override
        public Object convert(String param) {
            if(!NumberUtil.isDouble(param)){return  null;}
            return  Convert.convert(Double.class, param,null);
        }
    },
    CONVERT_TO_DATE(5,"转为Date"){
        @Override
        public Object convert(String param) {
            return  Convert.convert(Date.class, param,null);
        }
    },

    CONVERT_TO_BOOLEAN(6,"转为Boolean"){
        @Override
        public Object convert(String param) {
            return  Convert.convert(Boolean.class, param,null);
        }
    },

    ;


    private  Integer code;

    private  String  desc;

    public   abstract Object convert(String param) ;


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    ConvertEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    public  static  Object convertTypeByCodeWithDefaultNull(Integer code,String param){
        for (ConvertEnum e : ConvertEnum.values()) {
            if(e.code.equals(code)){
                try{
                    return e.convert(param);
                }catch (Exception  err){return  null;}
            }
        }
        return  null;
    }



}
