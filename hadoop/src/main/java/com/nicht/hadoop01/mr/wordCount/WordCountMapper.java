package com.nicht.hadoop01.mr.wordCount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @Description
 * @Date 2023/4/24
 */
public class WordCountMapper extends Mapper<LongWritable,Text,Text, IntWritable> {
   private  Text outKey = new Text();
    IntWritable outValue = new IntWritable(1);

    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context) throws IOException, InterruptedException {
        // line = jaja ssa ss ss zxx s;
        String line =   value.toString();
        String [] words = line.split(" ");

        for (String word : words) {
              outKey.set(word);
              context.write(outKey,outValue);
        }
    }
}
