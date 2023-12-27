package com.nicht.hadoop01.mr.wordCount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * @Description
 * @Date 2023/4/24
 */

public class WordCountDrive  {


    public  static void main(String[] args) throws  Exception {



        Configuration conf = new Configuration();

        Job job = Job.getInstance(conf);
           job.setJarByClass(WordCountDrive.class);
           // job.setJar("");
           job.setMapperClass(WordCountMapper.class);
           job.setReducerClass(WordCountReduce.class);
           //设置 mapper 输出的 key value 类型 对应
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
          //设置 最终输出key value
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
    
        FileInputFormat.setInputPaths(job, new Path("/Downloads/mypa.txt"));
        FileOutputFormat.setOutputPath(job, new Path("Downloads/wordresults1"));
        job.waitForCompletion(true);




    }

}
