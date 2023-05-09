package com.nicht.hadoop01;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;


import java.net.URI;

import org.apache.hadoop.fs.Path;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * @Description
 * @Date 2023/4/18
 */
public class hadoopTest01 {


    private static Logger logger = LogManager.getLogger(hadoopTest01.class);

    public static void main(String[] args) throws Exception {
        logger.error("hadoop test");

        FileSystem fs = HadoopUtils.getFileSystem();
        if(fs!=null) {
            boolean r = fs.mkdirs(new Path("javaCreateDir"));
            fs.close();
        }

        System.out.println("fs = " +fs);
    }


}
