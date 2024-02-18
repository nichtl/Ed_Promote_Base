package com.example.udpDemo.socket.udp;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * Description: 计量单位枚举
 *
 * @author sjt Administrator
 * @since 2023/12/3 16:17
 */
@Getter
@AllArgsConstructor
public enum MeasurementUnitEnum {
    UNKNOWN(0x00, "未知"),
    LEL_PERCENTAGE(0x01, "%LEL"),
    VOL_PERCENTAGE(0x02, "%VOL"),
    PPM(0x03, "PPM"),
    MICRO_MOL_PER_MOL(0x04, "μmol/mol"),
    MG_PER_M3(0x05, "mg/m3"),
    RELATIVE_HUMIDITY(0x06, "%RH"),
    METERS(0x07, "m"),
    METERS_PER_SECOND(0x08, "m/s"),
    KILOMETERS_PER_HOUR(0x09, "km/h"),
    LUX(0x0A, "LX"),
    PASCAL(0x0B, "Pa"),
    DECIBEL(0x0C, "DB"),
    CUBIC_METERS(0x0D, "m3"),
    SQUARE_METERS(0x0E, "m2"),
    KILOGRAMS(0x0F, "Kg"),
    GRAMS(0x10, "g"),
    MILLIMETERS(0x11, "mm"),
    CENTIMETERS(0x12, "cm"),
    MICROGRAMS_PER_M3(0x13, "ug/m3"),
    PARTICLES_PER_ML(0x14, "pcs/ml"),
    MILLIAMPERE_HOUR(0x15, "mAH"),
    CELSIUS(0x16, "°C"),
    REVOLUTIONS_PER_MINUTE(0x17, "r/min"),
    KILOMETERS(0x18, "Km"),
    FAHRENHEIT(0x19, "F"),
    PPB(0x1A, "ppb"),
    LEL_METER(0x1B, "LEL • m"),
    MILLIVOLTS(0x1C, "mV"),
    AMPERES(0x1D, "A"),
    MILLIAMPERES(0x1F, "mA"),
    DEGREES(0x20, "°"),
    SECONDS(0x21, "秒"),
    MINUTES(0x22, "分"),
    HOURS(0x23, "时"),
    DAYS(0x24, "日");

    private final int code;
    private final String name;

    public static MeasurementUnitEnum getByCode(int code) {
        return Arrays.stream(MeasurementUnitEnum.values()).filter(v -> code == v.getCode()).findFirst().orElse(UNKNOWN);
    }
}
