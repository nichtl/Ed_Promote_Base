package com.nicht.promote.fileservice;

import com.nicht.promote.fileservice.zkNetty.zkNettyManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.io.IOException;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
//@ComponentScan(basePackages={"com.nicht.promote.fileservice","com.nicht.promote.config"})
public class FileserviceApplication {

    public static void main(String[] args) throws IOException {
/*        Properties properties = new Properties();
        //这里指定加载的是application-uat.properties文件的配置
        InputStream inputStream = FileserviceApplication.class.getClassLoader().getResourceAsStream("application-dev.yml");
        properties.load(inputStream);
        SpringApplication app = new SpringApplication(FileserviceApplication.class);
        app.setDefaultProperties(properties);
        app.run(args);*/

        SpringApplication.run(FileserviceApplication.class, args);

        zkNettyManager manager = zkNettyManager.getInstance();
        manager.start(8082);

     /*   try {
            int port;
            port = 8082;
            new SimpleChatServer(port).run();
        }catch (Exception e){
            e.printStackTrace();
        }*/


//     加载YML格式自定义配置文件
//    @Bean
//    public static PropertySourcesPlaceholderConfigurer properties() {
//        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
//        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
////		yaml.setResources(new FileSystemResource(ResourceUtils.CLASSPATH_URL_PREFIX + "permission.yml"));//File引入
//        yaml.setResources(new ClassPathResource("permission.yml"));//class引入，避免了路径处理问题
//        configurer.setProperties(yaml.getObject());
//        return configurer;
//    }


    }
}
