package com.nicht.promote.DataStruct_Algorithrem.src.Reflection.Annotation;


/**
 * @Author Nicht
 * @description
 * @ 2020/12/1
 */
@MyAnnotation(name= "zhangsan",value = "18")
public class AnnotationTest {
    private  String  annotationname;
    private  String  annotationdes;
    private  String  annotationvalue;

    public String getAnnotationname() {
        return annotationname;
    }

    public void setAnnotationname(String annotationname) {
        this.annotationname = annotationname;
    }

    public String getAnnotationdes() {
        return annotationdes;
    }

    public void setAnnotationdes(String annotationdes) {
        this.annotationdes = annotationdes;
    }

    public String getAnnotationvalue() {
        return annotationvalue;
    }

    public void setAnnotationvalue(String annotationvalue) {
        this.annotationvalue = annotationvalue;
    }

    public AnnotationTest() {
    }

    @Override
    public String toString() {
        return "AnnotationTest{" +
                "annotationname='" + annotationname + '\'' +
                ",annotationdes='" + annotationdes + '\'' +
                ",annotationvalue='" + annotationvalue + '\'' +
                '}';
    }
}
