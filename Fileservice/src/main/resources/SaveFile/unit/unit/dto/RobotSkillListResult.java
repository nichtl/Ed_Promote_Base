package com.tuniu.mob.ocsfaq.faq.unit.dto;

import java.util.List;

public class RobotSkillListResult extends Result {

    private List<RobotSkill> skills;

    public List<RobotSkill> getSkills() {
        return skills;
    }

    public void setSkills(List<RobotSkill> skills) {
        this.skills = skills;
    }
}
