package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.wande;

/**
 * @author nicht
 * @date 20220322
 */
public class TASK  implements  Comparable<TASK>{
    @Override
    public int compareTo(TASK o) {
       return Integer.compare(o.priority, priority);
    }

    private  String name;
    private  int priority;
    private  int resourceNum;

    public TASK(String name, int priority, int resourceNum) {
        this.name = name;
        this.priority = priority;
        this.resourceNum = resourceNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public  int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getResourceNum() {
        return resourceNum;
    }

    public void setResourceNum(int resourceNum) {
        this.resourceNum = resourceNum;
    }

    @Override
    public String toString() {
        return "TASK{" +
                "name='" + name + '\'' +
                ", priority=" + priority +
                ", resourceNum=" + resourceNum +
                '}';
    }
}
