package com.nicht.hadoop01.dto;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @Description
 * @Date 2023/5/4
 */
public class FlowBean  implements Writable {


    private  Long upFlow;

    private  Long downFlow;

    private  Long sumFlow;

    public FlowBean() {
    }

    public Long getUpFlow() {
        return upFlow;
    }

    public void setUpFlow(Long upFlow) {
        this.upFlow = upFlow;
    }

    public Long getDownFlow() {
        return downFlow;
    }

    public void setDownFlow(Long downFlow) {
        this.downFlow = downFlow;
    }

    public Long getSumFlow() {
        return sumFlow;
    }

    public void setSumFlow(Long sumFlow) {
        this.sumFlow = sumFlow;
    }


    @Override
    public void write(DataOutput output) throws IOException {
       output.writeLong(upFlow);
       output.writeLong(downFlow);
       output.writeLong(sumFlow);
    }

    @Override
    public void readFields(DataInput input) throws IOException {
         this.upFlow  = input.readLong();
         this.downFlow = input.readLong();
         this.sumFlow = input.readLong();
    }

    @Override
    public String toString() {
        return "FlowBean{" +
                "upFlow=" + upFlow +
                ", downFlow=" + downFlow +
                ", sumFlow=" + sumFlow +
                '}';
    }
}
