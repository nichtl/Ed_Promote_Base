package com.example.udpDemo.socket;

import cn.hutool.core.thread.ThreadUtil;
import com.example.udpDemo.socket.tcp.TcpServer;
import com.example.udpDemo.socket.udp.UdpServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author wangdachuan
 * @version 1.0
 * @date 2023/3/14 23:31
 **/
@Slf4j
@Component
public class StartRunner implements ApplicationRunner {

    @Autowired
    private UdpServer udpServer;
    @Autowired
    private TcpServer tcpServer;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("开始启动Netty服务");
        // 启动UDP服务
        ThreadUtil.execAsync(()->{
           udpServer.run();
        });
        // 启动TCP服务端
//        ThreadUtil.execAsync(()->{
//            tcpServer.run();
//        });
    }
}
