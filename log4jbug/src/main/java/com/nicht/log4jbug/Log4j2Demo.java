package com.nicht.log4jbug;

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
            File file = new File("D:\\MyTools\\Project\\Ed_Promote_Base\\log4jbug\\src\\main\\resources\\log4j.xml");
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
            final ConfigurationSource source = new ConfigurationSource(in);
            Configurator.initialize(null, source);
            Logger logger = LogManager.getLogger(Log4j2Demo.class);
            String username="${java:os}";
            logger.info("Hello, {}",username);
        }catch (Exception e){e.printStackTrace();}
    }

}
