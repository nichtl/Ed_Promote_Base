package com.tuniu.mob.ocsfaq.faq.unit.dto;

import cn.hutool.core.collection.CollectionUtil;

import java.util.List;

public class AppendSameFaqADto {

    /**
     * 1 app库 2客服供应商库
     */
    private  Integer qapType;

    private List<SameFaqDto> sameFaqDtos;

    public List<SameFaqDto> getSameFaqDtos() {
        return sameFaqDtos;
    }

    public void setSameFaqDtos(List<SameFaqDto> sameFaqDtos) {
        this.sameFaqDtos = sameFaqDtos;
    }

    public Integer getQapType() {
        return qapType;
    }

    public void setQapType(Integer qapType) {
        this.qapType = qapType;
    }

    public  boolean  valid(){

        if( this.getQapType() == null || this.getQapType()<=0 || CollectionUtil.isEmpty(  this.getSameFaqDtos())){
            return  false;
        }
        boolean  r = this.getSameFaqDtos().stream().anyMatch(e->!e.valid());
        if(r){return  false;}
      return  true ;
    }

}
