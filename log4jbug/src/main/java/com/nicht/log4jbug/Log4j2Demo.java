package com.nicht.log4jbug;

import cn.hutool.core.io.resource.ClassPathResource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * @Description
 * @Author Administrator
 * @Time 2021/12/27
 * @Link
 */
public class Log4j2Demo {

    public static void main(String[] args) {
        try {
            ClassPathResource resource  = new ClassPathResource("log4j.xml");//原xml文件夹
            BufferedInputStream in = new BufferedInputStream(resource.getStream());
            final ConfigurationSource source = new ConfigurationSource(in);
            Configurator.initialize(null, source);
            Logger logger = LogManager.getLogger(Log4j2Demo.class);
            String username="${java:os}";
            logger.info("Hello, {}",username);
        }catch (Exception e){e.printStackTrace();}
    }

}
