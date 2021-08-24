package com;

import com.learn_netty_one.SimapleChat.Server.SimpleChatServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@EnableCaching
public class NettyoneeApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(NettyoneeApplication.class, args);
        int port;
        if(args.length > 0){
            port = Integer.parseInt(args[0]);
        }else {
            port = 8082;
        }
        new SimpleChatServer(port).run();
    }

}
