package com.tuniu.mob.ocsfaq.faq.entity;


import java.util.Date;

public class FaqDynamicParameter {
    private Integer id;

    /**
     * 如果该id不为0 代表父id字段为对象
     *
     * @mbg.generated
     */
    private Integer parentId;

    /**
     * 字段名称
     *
     * @mbg.generated
     */
    private String fieldName;

    /**
     * 不为空表示参数值是固定的
     *
     * @mbg.generated
     */
    private String constValue;

    /**
     * 1 String 2 Long 3 Double
     * @mbg.generated
     */
    private Integer fieldType;

    /**
     * 默认 0  直接使用各自类型默认的取值方式  如 string 类型 直接取值  long double 也都是转为string
     * 如果为list 默认 取第一个元素 进行后续取值操作
     * 1 说明这个
     */
    private Integer fieldOperateType;


    /**
     * 1 接口传入 2 固定值 3 配置的上文接口出参
     *
     * @mbg.generated
     */
    private Integer dataSourceType;

    /**
     * 如果data_source_type =3取上文接口出参
     *
     * @mbg.generated
     */
    private Integer contextInterfaceId;

    /**
     * 上文接口出参
     *
     * @mbg.generated
     */
    private String contextInterfaceOutParam;

    /**
     * 参数的中文名称
     *
     * @mbg.generated
     */
    private String description;

    /**
     * 节点级别
     *
     * @mbg.generated
     */
    private Integer level;

    /**
     * 控制入参必填
     *
     * @mbg.generated
     */
    private Boolean required;

    private Date addTime;

    private Date updateTime;

    /**
     * 字段树 级联 path
     */
    private String path;

    /**
     * 参数的别名 由于不同系统接口参数名称不统一 针对已有入参 支持根据别名取值
     */
    private String  fieldAlias;

    /**
     *
     * eg 1=是,2=否
     * 参数增加字典做映射
     * @return
     */

    private String  paramDict;


    public Integer getFieldOperateType() {
        return fieldOperateType;
    }

    public void setFieldOperateType(Integer fieldOperateType) {
        this.fieldOperateType = fieldOperateType;
    }

    public String getParamDict() {
        return paramDict;
    }

    public void setParamDict(String paramDict) {
        this.paramDict = paramDict;
    }

    public String getFieldAlias() {
        return fieldAlias;
    }

    public void setFieldAlias(String fieldAlias) {
        this.fieldAlias = fieldAlias;
    }


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName == null ? null : fieldName.trim();
    }

    public String getConstValue() {
        return constValue;
    }

    public void setConstValue(String constValue) {
        this.constValue = constValue == null ? null : constValue.trim();
    }

    public Integer getFieldType() {
        return fieldType;
    }

    public void setFieldType(Integer fieldType) {
        this.fieldType = fieldType;
    }

    public Integer getDataSourceType() {
        return dataSourceType;
    }

    public void setDataSourceType(Integer dataSourceType) {
        this.dataSourceType = dataSourceType;
    }

    public Integer getContextInterfaceId() {
        return contextInterfaceId;
    }

    public void setContextInterfaceId(Integer contextInterfaceId) {
        this.contextInterfaceId = contextInterfaceId;
    }

    public String getContextInterfaceOutParam() {
        return contextInterfaceOutParam;
    }

    public void setContextInterfaceOutParam(String contextInterfaceOutParam) {
        this.contextInterfaceOutParam = contextInterfaceOutParam == null ? null : contextInterfaceOutParam.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", parentId=").append(parentId);
        sb.append(", fieldName=").append(fieldName);
        sb.append(", constValue=").append(constValue);
        sb.append(", fieldType=").append(fieldType);
        sb.append(", dataSourceType=").append(dataSourceType);
        sb.append(", contextInterfaceId=").append(contextInterfaceId);
        sb.append(", contextInterfaceOutParam=").append(contextInterfaceOutParam);
        sb.append(", description=").append(description);
        sb.append(", level=").append(level);
        sb.append(", required=").append(required);
        sb.append(", addTime=").append(addTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}
