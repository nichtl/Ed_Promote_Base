package com.example.udpDemo.socket.udp;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Description: 数据包类型
 *
 * @author sjt Administrator
 * @since 2023/11/28 20:13
 */
@Getter
@AllArgsConstructor
public enum NettyPacketTypeEnum {

    HEARTBEAT("心跳", "HEARTBEAT"),
    KEY("密钥下发", "KEY"),
    REAL_TIME_DATA("实时数据", "REAL_TIME_DATA");

    private final String name;
    private final String value;
}
