package com.tuniu.mob.ocsfaq.faq.unit.dto.faq;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class Slot {
    @JSONField(name = "slot_name")
    private String slotName;

    @JSONField(name = "slot_values")
    private List<SlotValue> slotValues;


    public String getSlotName() {
        return slotName;
    }

    public void setSlotName(String slotName) {
        this.slotName = slotName;
    }

    public List<SlotValue> getSlotValues() {
        return slotValues;
    }

    public void setSlotValues(List<SlotValue> slotValues) {
        this.slotValues = slotValues;
    }
}
