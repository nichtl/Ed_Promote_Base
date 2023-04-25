package com.nicht.hadoop01;

import com.nicht.hadoop01.constant.HadoopConstant;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URI;

/**
 * @Description
 * @Date 2023/4/19
 */
public class HadoopUtils {

    private static Logger logger = LogManager.getLogger(HadoopUtils.class);



    public  static  FileSystem getFileSystem() {
        FileSystem fs =null;
        try{
            URI url = URI.create(HadoopConstant.nameNodeUrI);
            Configuration conf = new Configuration();
            conf.set("dfs.replication","3");
            fs = FileSystem.get(url,conf,HadoopConstant.user);
        }catch(Exception e){
            logger.error("Failed create fileSystem for = {}",e.getMessage());
            fs = null;
        }
        return  fs;
    }

    public  static  FileSystem getFileSystem(String uri) {
        FileSystem fs =null;
        try{
            URI url = URI.create(uri);
            Configuration conf = new Configuration();
            conf.set("dfs.replication","3");
            fs = FileSystem.get(url,conf,HadoopConstant.user);
        }catch(Exception e){
            logger.error("Failed create fileSystem for = {}",e.getMessage());
            fs = null;
        }
        return  fs;
    }
}
