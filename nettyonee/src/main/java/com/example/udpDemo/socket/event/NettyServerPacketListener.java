package com.example.udpDemo.socket.event;

import com.example.udpDemo.socket.udp.NettyPacket;
import com.example.udpDemo.socket.udp.UdpServer;
import io.netty.channel.ChannelId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Description: 自定义数据包处理监听器
 *
 * @author sjt Administrator
 * @since 2023/11/28 22:19
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class NettyServerPacketListener implements ApplicationListener<NettyPacketEvent> {

    private final UdpServer udpServer;

    @Async
    @Override
    public void onApplicationEvent(NettyPacketEvent event) {
        ChannelId channelId = (ChannelId) event.getSource();
        List<NettyPacket> nettyPackets = event.getNettyPackets();
        // TODO: 2023/12/3 输出到平台
        // TODO: 2023/12/3 写入响应

    }
}
