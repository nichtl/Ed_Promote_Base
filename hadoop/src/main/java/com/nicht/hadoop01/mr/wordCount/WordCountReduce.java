package com.nicht.hadoop01.mr.wordCount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @Description
 * @Date 2023/4/24
 */
public class WordCountReduce  extends Reducer<Text, IntWritable,Text,IntWritable> {
   private   IntWritable out = new IntWritable();
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
            int sum  = 0 ;
            for (IntWritable value : values) {
                 sum += value.get();
            }
            out.set(sum);
            context.write(key,out);
    }


}


