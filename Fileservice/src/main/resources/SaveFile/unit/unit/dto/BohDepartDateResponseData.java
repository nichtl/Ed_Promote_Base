package com.tuniu.mob.ocsfaq.faq.unit.dto;

import java.util.List;

public class BohDepartDateResponseData {

    private List<BohDepartDate> departDates;

    public List<BohDepartDate> getDepartDates() {
        return departDates;
    }

    public BohDepartDateResponseData(List<BohDepartDate> departDates) {
        this.departDates = departDates;
    }

    public BohDepartDateResponseData() {
    }

    public void setDepartDates(List<BohDepartDate> departDates) {
        this.departDates = departDates;
    }
}
