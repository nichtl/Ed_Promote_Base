package com.nicht.promote.fileservice.config;


import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Author Nicht
 * @Description
 * @Time 2021/6/8
 * @Link  https://www.cnblogs.com/stonechen/p/14298748.html
 */
@Data
@Component
public class MinioConfig {
  @Autowired
  private MinioProperties minioProperties;
  @Bean(name = "MinioClient")
    public MinioClient getMinioClient() throws InvalidEndpointException, InvalidPortException {
    MinioClient  minioClient  = MinioClient.builder().
                                endpoint(minioProperties.getEndpoint(), minioProperties.getPort(), minioProperties.getSecure()).
                                credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey()).
                                build();
     return minioClient;
  }


}
