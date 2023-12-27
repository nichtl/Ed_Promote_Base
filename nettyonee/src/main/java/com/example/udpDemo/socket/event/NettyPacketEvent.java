package com.example.udpDemo.socket.event;

import com.example.udpDemo.socket.udp.NettyPacket;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.List;

/**
 * Description: 自定义Netty数据包处理事件
 *
 * @author sjt Administrator
 * @since 2023/11/28 22:20
 */
@Getter
public class NettyPacketEvent extends ApplicationEvent {

    private final List<NettyPacket> nettyPackets;

    public NettyPacketEvent(Object source, List<NettyPacket> nettyPackets) {
        super(source);
        this.nettyPackets = nettyPackets;
    }
}
