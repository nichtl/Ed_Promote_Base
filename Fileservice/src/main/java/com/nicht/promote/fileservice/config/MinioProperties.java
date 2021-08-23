package com.nicht.promote.fileservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @Author Nicht
 * @Description 映射yaml minio配置
 * @Time 2021/6/8
 * @Link https://www.cnblogs.com/stonechen/p/14298748.html
 */
@ConfigurationProperties(prefix = "minio")
//@PropertySource("classpath:application-minio.properties")
@Data
@Component
@Repository
public class MinioProperties {

    private  String endpoint;

    private Integer port;

    private String accessKey;

    private String secretKey;

    private Boolean secure;

    private String bucketName;

}
