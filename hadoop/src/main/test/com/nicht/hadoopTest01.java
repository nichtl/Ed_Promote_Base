package com.nicht;

import com.nicht.hadoop01.HadoopUtils;
import com.nicht.hadoop01.dto.FlowBean;
import com.nicht.hadoop01.mr.telCount.FlowDrive;
import com.nicht.hadoop01.mr.telCount.FlowMapper;
import com.nicht.hadoop01.mr.telCount.FlowReduce;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.junit.Test;

import java.io.IOException;

/**
 * @Description
 * @Date 2023/4/18
 */

public class hadoopTest01 {


    @Test
    public  void testUpload() throws Exception {
        FileSystem fs = HadoopUtils.getFileSystem();
        if(fs!=null ){
            String uploadSrc ="/Users/xujian8/Downloads/在线客服介绍.pptx";
            String uploadDst="/user/root/javaCreateDir";
            fs.copyFromLocalFile(false,true,new Path(uploadSrc),new Path(uploadDst));
        }
    }

    @Test
    public void testDownload() throws Exception {
        FileSystem fs = HadoopUtils.getFileSystem();
        String downloadSrc="/user/root/javaCreateDir/在线客服介绍.pptx";
        String downloadDst="/Users/xujian8/Downloads/hadoopChatPPT.pptx";
        fs.copyToLocalFile(false,new Path(downloadSrc),new Path(downloadDst),true);
    }

    @Test
    public void testRename() throws Exception {
        FileSystem fs = HadoopUtils.getFileSystem();
        String src="/user/root/javaCreateDir/在线客服介绍.pptx";
        String dst="/user/root/javaCreateDir/在线客服介绍2.pptx";
        boolean r =  fs.rename(new Path(src),new Path(dst));
        System.out.println(r);
    }

    @Test
    public void testMove() throws Exception {
        FileSystem fs = HadoopUtils.getFileSystem();
        String src="/user/root/javaCreateDir/在线客服介绍2.pptx";
        String dst="/user/root/在线客服介绍3.pptx";
        boolean r =  fs.rename(new Path(src),new Path(dst));
        System.out.println(r);
    }

    @Test
    public  void testListFiles() throws Exception {
        FileSystem fs = HadoopUtils.getFileSystem("hdfs://10.28.20.158:8020");
        String src="/user/root/";
        String dst="/user/root/在线客服介绍3.pptx";
        RemoteIterator<LocatedFileStatus> ls = fs.listFiles(new Path("/"),true);

        while (ls.hasNext()){
            LocatedFileStatus status = ls.next();

            System.out.println("path   = "+status.getPath());
            System.out.println("owner  = "+status.getOwner());
            System.out.println("group  = "+status.getGroup());
            System.out.println("length = "+status.getLen());
        };
    }



    @Test
    public  void testDelFiles() throws Exception {
        FileSystem fs = HadoopUtils.getFileSystem();
        System.out.println(fs.getClass().getName());
        String src="/user/root/";
        fs.delete(new Path(src),false);//递归删除

    }


    @Test
    public void testFlow() throws Exception {
        Configuration conf = new Configuration();

        Job job =  Job.getInstance(conf);

        job.setJarByClass(FlowDrive.class);
        job.setMapperClass(FlowMapper.class);
        job.setReducerClass(FlowReduce.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FlowBean.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);


        FileInputFormat.setInputPaths(job,new Path("/Users/xujian8/Downloads/flow.txt"));
        FileOutputFormat.setOutputPath(job,new Path("/Users/xujian8/Downloads/flowcount1"));
        // 提交
        job.waitForCompletion(true);
    }






}
