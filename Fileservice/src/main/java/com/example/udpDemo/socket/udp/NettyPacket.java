package com.example.udpDemo.socket.udp;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * Description: 自定义Netty数据包
 *
 * @author sjt Administrator
 * @since 2023/11/28 22:21
 */
@Data
@Slf4j
public class NettyPacket implements Serializable {


    private NettyPacketTypeEnum nettyPacketType;
    //数据类型 0-传感器  1-事件
    private Integer dataType;
    private String sn;
    private Object type; //GasTypeEnum or EventEnum
    private String value;
    private Integer numPort;
    private MeasurementUnitEnum unit;

    public static String buildHeartBeatResponse(String data) {
        StringBuilder builder = new StringBuilder("5E");
        return builder.toString();
    }

}
