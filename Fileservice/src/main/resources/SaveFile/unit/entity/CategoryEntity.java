package com.tuniu.mob.ocsfaq.faq.entity;


public class CategoryEntity {
    //类别Id
    private Integer id;
    //类别名称
    private String name;
    //父类别Id
    private Integer parentId;
    //排序时间
    private Integer sort;
    //类别图标
    private String icon;
    //根节点到父节点的路径
    private String path;
    //类别的级别
    private Integer level;
    //创建时间
    private String createTime;
    //修改时间
    private String modifyTime;
    //是否删除
    private boolean deleteFlag;
    //是否开启人工客服，0：没有  1开启
    private Boolean customerCloseFlag;
    //人工客服下所对应的分类名
    private String customerCategoryName;
    //虚拟组Id
    private Integer groupId;
    //虚拟组名称
    private String groupName;
    //品类Id
    private Integer productType;
    //品类名称
    private String productName;
    //cat大类id
    private Integer classId;
    //客服咨询跳转openUrl需要用到的temId
    private int templateId;
    //客服中心跳转openUrl需要用到的temId
    private int customerCenterTemplateId;
    //int 分类id
    private int buttonClass;
    //String 分类展示名称
    private String buttonClassName = "其他";
    // 一级菜单映射到百度uinit对话机器人id
    private String unitServiceId;

    // 二级分类对应的技能id
    private String unitSkillId;
    // 技能名称
    private String unitSkillName;
    // 控制新版FAQ 菜单显示 0显示 1不显示
    private Integer productShow;


    //分类id 在线客服 v10.80 产品咨询 对菜单按类别分组
    private Integer  productCategoryId;

    private String   productCategoryName;


    public String getProductCategoryName() {
        return productCategoryName;
    }

    public void setProductCategoryName(String productCategoryName) {
        this.productCategoryName = productCategoryName;
    }

    public Integer getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Integer productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public Integer getProductShow() {
        return productShow;
    }

    public void setProductShow(Integer productShow) {
        this.productShow = productShow;
    }

    public String getUnitSkillId() {
        return unitSkillId;
    }

    public void setUnitSkillId(String unitSkillId) {
        this.unitSkillId = unitSkillId;
    }

    public String getUnitSkillName() {
        return unitSkillName;
    }

    public void setUnitSkillName(String unitSkillName) {
        this.unitSkillName = unitSkillName;
    }

    public String getUnitServiceId() {
        return unitServiceId;
    }

    public void setUnitServiceId(String unitServiceId) {
        this.unitServiceId = unitServiceId;
    }

    public int getButtonClass() {
        return buttonClass;
    }

    public void setButtonClass(int buttonClass) {
        this.buttonClass = buttonClass;
    }

    public String getButtonClassName() {
        return buttonClassName;
    }

    public void setButtonClassName(String buttonClassName) {
        this.buttonClassName = buttonClassName;
    }

    public int getCustomerCenterTemplateId() {
        return customerCenterTemplateId;
    }

    public void setCustomerCenterTemplateId(int customerCenterTemplateId) {
        this.customerCenterTemplateId = customerCenterTemplateId;
    }

    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public boolean isDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Boolean getCustomerCloseFlag() {
        return customerCloseFlag;
    }

    public void setCustomerCloseFlag(Boolean customerCloseFlag) {
        this.customerCloseFlag = customerCloseFlag;
    }

    public String getCustomerCategoryName() {
        return customerCategoryName;
    }

    public void setCustomerCategoryName(String customerCategoryName) {
        this.customerCategoryName = customerCategoryName;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    @Override
    public String toString() {
        return "CategoryEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                ", sort=" + sort +
                ", icon='" + icon + '\'' +
                ", path='" + path + '\'' +
                ", level=" + level +
                ", createTime='" + createTime + '\'' +
                ", modifyTime='" + modifyTime + '\'' +
                ", deleteFlag=" + deleteFlag +
                ", customerCloseFlag=" + customerCloseFlag +
                ", customerCategoryName='" + customerCategoryName + '\'' +
                ", groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                ", productType=" + productType +
                ", productName='" + productName + '\'' +
                ", classId=" + classId +
                ", templateId=" + templateId +
                ", customerCenterTemplateId=" + customerCenterTemplateId +
                ", buttonClass=" + buttonClass +
                ", buttonClassName='" + buttonClassName + '\'' +
                ", unitServiceId='" + unitServiceId + '\'' +
                '}';
    }
}
