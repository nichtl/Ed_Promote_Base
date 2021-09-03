package com.nicht.promote.DataStruct_Algorithrem.src.Reflection.model;

/**
 * @Author admin
 * @description
 * @ 2020/12/1
 */

import javax.management.MXBean;

/**
 * 用户实体类
 *
 * @author xue huisheng
 *
 */
public class UserBean {
    /*get set 省略*/
    private Integer id;// ID

    private String loginPwd;// 登录密码
    private String loginName;// 登录名
    private String name;    //用户姓名
    private String roleName;// 角色名

    private String whcd;// 文华程度

    private String email;// email

    private String officePhone;// 办公电话

    private String telephone;// 手机号

    private String address;// 家庭住址

    private String workUnit;// 工作单位

    private String duty;// 职务

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getWhcd() {
        return whcd;
    }

    public void setWhcd(String whcd) {
        this.whcd = whcd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWorkUnit() {
        return workUnit;
    }

    public void setWorkUnit(String workUnit) {
        this.workUnit = workUnit;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }
}