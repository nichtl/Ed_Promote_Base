
package com.tuniu.mob.ocsfaq.faq.entity;

/**
 * mob_faq_category_class
 * Date: 2016-05-24
 *
 * @author yulongfei
 */
public class CategoryClassEntity {
    private Integer id;
    //创建时间
    private String createTime;
    //是否删除
    private boolean deleteFlag;
    //class名称
    private String className;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public boolean isDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
