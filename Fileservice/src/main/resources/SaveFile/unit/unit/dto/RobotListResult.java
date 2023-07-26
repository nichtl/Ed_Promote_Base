package com.tuniu.mob.ocsfaq.faq.unit.dto;

import java.util.List;

public class RobotListResult extends Result {


    private List<Service> services;


    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }
}
