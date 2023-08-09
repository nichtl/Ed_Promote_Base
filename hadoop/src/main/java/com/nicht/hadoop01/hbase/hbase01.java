package com.nicht.hadoop01.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.NamespaceDescriptor;
import org.apache.hadoop.hbase.NamespaceExistException;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
/**
 * @Description ddl operate
 * @Date 2023/8/8
 */
public class hbase01 {
   
    private  static final   Logger  logger   = LoggerFactory.getLogger(hbase01.class);

    public  static  boolean  createNameSpace(String nameSpace) {
       boolean rs = false;
        try {
            Configuration configuration = HBaseConfiguration.create();
            configuration.set("hbase.zookeeper.quorum", "82.156.165.91,101.43.154.160,120.26.11.94");
            Connection connection = ConnectionFactory.createConnection(configuration);
            NamespaceDescriptor namespace =  NamespaceDescriptor.create(nameSpace).build();
            connection.getAdmin().createNamespace(namespace);
            connection.getAdmin().close();
            connection.close();
        }catch (NamespaceExistException nse){
            logger.error("Error creating connection,namespace already exists,{}",nse.getMessage());
        }catch (IOException ioe){
            logger.error("Error creating connection, ioException={}" , ioe.getMessage());
        }catch (Exception e){
            logger.error("Error creating connection, Exception={}" , e.getMessage());
        }
        return  rs;
    }



    public static void main(String[] args) {
      createNameSpace("nicht");
    }


}
