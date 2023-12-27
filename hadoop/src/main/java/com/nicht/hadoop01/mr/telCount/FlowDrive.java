package com.nicht.hadoop01.mr.telCount;

import com.nicht.hadoop01.dto.FlowBean;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @Description
 * @Date 2023/5/4
 */
public class FlowDrive {

    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration();

        Job job =  Job.getInstance(conf);

        job.setJarByClass(FlowDrive.class);
        job.setMapperClass(FlowMapper.class);
        job.setReducerClass(FlowReduce.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FlowBean.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);


        FileInputFormat.setInputPaths(job,new Path("Downloads/flow.txt"));
        FileOutputFormat.setOutputPath(job,new Path("Downloads/flowcount1"));
        // 提交
        job.waitForCompletion(true);



    }












//
//1 12824914901 192.168.0.1 www.baidu.com  2401 2701 200
//2 12722816901 192.168.1.1 www.baidu.com  2402 2301 200
//3 16528904807 192.168.2.1 www.baidu.com  2401 2701 200
//4 12824914901 192.168.0.1 www.baidu.com  2401 2701 200


}
