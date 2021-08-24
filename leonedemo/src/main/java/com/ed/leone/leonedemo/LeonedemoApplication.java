package com.ed.leone.leonedemo;

import com.ed.leone.leonedemo.Server.NettyServer;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetSocketAddress;
import java.util.logging.Logger;

@SpringBootApplication
public class LeonedemoApplication implements CommandLineRunner {
    @Autowired
    private NettyServer nettyServer ;
    public static void main(String[] args) {
        SpringApplication.run(LeonedemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        InetSocketAddress address = new InetSocketAddress("192.168.5.238",10998);
        System.out.println("netty 服务端启动 Address:" +"192.168.5.238" );
        nettyServer.start(address);
    }


}
