package com.example.udpDemo.socket.udp;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * Description: 事件类型枚举
 *
 * @author sjt Administrator
 * @since 2023/12/3 16:23
 */
@Getter
@AllArgsConstructor
public enum EventEnum {
    UNKNOWN(0x00, "未知"),
    NORMAL(0x01, "正常"),
    PREHEAT(0x02, "预热状态"),
    CALIBRATION_ERROR(0x03, "标定错误"),
    SENSOR_ERROR(0x04, "传感器错误"),
    WARNING(0x05, "预警"),
    LOW_LIMIT_ALARM(0x06, "低限报警"),
    HIGH_LIMIT_ALARM(0x07, "高限报警"),
    COMMUNICATION_FAULT(0x08, "通讯故障（控制器读探头故障）"),
    OVER_RANGE(0x09, "超量程"),
    AWAIT_CALIBRATION(0x0A, "待校准（标定提示）"),
    HEARTBEAT_SIGNAL(0x0B, "心跳信号"),
    SOS_SIGNAL(0x0C, "求救信号"),
    ABNORMAL_SIGNAL(0x0D, "异常信号（报警和故障）"),
    RESERVED1(0x0E, "预留"),
    RESERVED2(0x0F, "预留"),
    SELF_CHECK(0x10, "自检"),
    FAILURE(0x11, "失效"),
    POWER_OFF(0x12, "掉电"),
    HIGH_TEMPERATURE_ALARM(0x13, "高温报警"),
    SMOKE_ALARM(0x14, "烟雾报警"),
    REMOVED(0x15, "已拆除"),
    MUTING(0x16, "消音中"),
    POWER_ABNORMAL(0x17, "电源异常"),
    CONTROLLER_FAULT(0x18, "控制器故障");

    private final int code;
    private final String name;

    public static EventEnum getByCode(int code) {
        return Arrays.stream(EventEnum.values()).filter(v -> code == v.getCode()).findFirst().orElse(UNKNOWN);
    }
}
