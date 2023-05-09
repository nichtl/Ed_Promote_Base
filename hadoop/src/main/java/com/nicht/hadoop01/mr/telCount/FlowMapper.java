package com.nicht.hadoop01.mr.telCount;

import com.nicht.hadoop01.dto.FlowBean;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @Description
 * @Date 2023/5/4
 */
public class FlowMapper  extends Mapper<LongWritable, Text,Text, FlowBean> {
    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, FlowBean>.Context context) throws IOException, InterruptedException {

              String line = value.toString();
              if(StringUtils.isBlank(line)){
                  return;
              }
              Text outKey = new Text();

              String [] msg =   line.split("\\s+");
              outKey.set(msg[1]);
              FlowBean outValue = new FlowBean();

              outValue.setUpFlow(Long.parseLong(msg[msg.length-3]));
              outValue.setDownFlow(Long.parseLong(msg[msg.length-2]));
              outValue.setSumFlow( Long.parseLong(msg[msg.length-3]) + Long.parseLong(msg[msg.length-2]) );
         context.write(outKey,outValue);
    }
}
