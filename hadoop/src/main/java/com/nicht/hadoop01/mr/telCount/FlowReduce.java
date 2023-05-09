package com.nicht.hadoop01.mr.telCount;

import com.nicht.hadoop01.dto.FlowBean;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @Description
 * @Date 2023/5/4
 */
public class FlowReduce extends Reducer<Text, FlowBean,Text,FlowBean> {
    private   FlowBean outValue  = new FlowBean();

    @Override
    protected void reduce(Text key, Iterable<FlowBean> values, Reducer<Text, FlowBean, Text, FlowBean>.Context context) throws IOException, InterruptedException {
        long  totalUpFLow=0 ;
        long  totalDownFLow=0;
        long  totalSumFLow=0;
        //

        for (FlowBean bean : values) {
                totalUpFLow   += bean.getUpFlow();
                totalDownFLow += bean.getDownFlow();
                totalSumFLow  += bean.getSumFlow();
        }
        outValue.setUpFlow(totalUpFLow);
        outValue.setDownFlow(totalDownFLow);
        outValue.setSumFlow(totalSumFLow);


        context.write(key,outValue);
    }
}
