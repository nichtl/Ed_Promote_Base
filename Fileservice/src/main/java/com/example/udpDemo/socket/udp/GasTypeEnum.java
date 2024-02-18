package com.example.udpDemo.socket.udp;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * Description: 传感器枚举
 *
 * @author sjt Administrator
 * @since 2023/12/3 16:00
 */
@Getter
@AllArgsConstructor
public enum GasTypeEnum {
    UNKNOWN(0x00, "未知", "UNKNOWN"),
    CH4(0x01, "甲烷", "CH4"),
    NH3(0x02, "氨气", "NH3"),
    H2S(0x03, "硫化氢", "H2S"),
    CO(0x04, "一氧化碳", "CO"),
    O2(0x05, "氧气", "O2"),
    H2(0x06, "氢气", "H2"),
    C2H6(0x07, "乙烷", "C2H6"),
    C2H4(0x08, "乙烯", "C2H4"),
    C2H2(0x09, "乙焕", "C2H2"),
    C3H8(0x0A, "丙烷", "C3H8"),
    C3H6(0x0B, "丙烯", "C3H6"),
    C4H10(0x0C, "丁烷", "C4H10"),
    C4H8(0x0D, "丁烯", "C4H8"),
    C4H6(0x0E, "丁二烯", "C4H6"),
    LIGHT_OIL(0x0F, "轻油", "轻油"),
    HEAVY_OIL(0x10, "重油", "重油"),
    GASOLINE(0x11, "汽油", "汽油"),
    DIESEL(0x12, "柴油", "柴油"),
    KEROSENE(0x13, "煤油", "煤油"),
    CH3OH(0x14, "甲醇", "CH3OH"),
    C2H5OH(0x15, "乙醇", "C2H5OH"),
    ISOPROPANOL(0x16, "(CH3)2CH0H", "ISOPROPANOL"),
    HCHO(0x17, "甲醛", "HCHO"),
    C3H7CHO(0x18, "丁醛", "C3H7CHO"),
    C3H6O(0x19, "丙酮", "C3H6O"),
    ACETONE(0x1A, "丁酮", "ACETONE"),
    BENZENE(0x1B, "苯", "BENZENE"),
    TOLUENE(0x1C, "甲苯", "TOLUENE"),
    XYLENE(0x1D, "二甲苯", "XYLENE"),
    STYRENE(0x1E, "苯乙烯", "STYRENE"),
    PHENOL(0x1F, "苯酚", "PHENOL"),
    ETHER(0x20, "乙醚", "ETHER"),
    DIMETHYL_ETHER(0x21, "二甲醚", "DIMETHYL_ETHER"),
    PETROLEUM_ETHER(0x22, "石油醚", "PETROLEUM_ETHER"),
    DIMETHYLAMINE(0x23, "二甲胺", "DIMETHYLAMINE"),
    TRIMETHYLAMINE(0x24, "三甲胺", "TRIMETHYLAMINE"),
    FORMAMIDE(0x25, "甲酰胺", "FORMAMIDE"),
    TETRAHYDROFURAN(0x26, "四氢呋喃", "TETRAHYDROFURAN"),
    ETHYL_ACETATE(0x27, "醋酸乙酯", "ETHYL_ACETATE"),
    CHLOROTOLUENE(0x28, "氯代甲苯", "CHLOROTOLUENE"),
    EPOXY_ETHANE(0x29, "环氧乙烷", "EPOXY_ETHANE"),
    OZONE(0x2A, "臭氧", "OZONE"),
    SULFUR_DIOXIDE(0x2B, "二氧化硫", "SULFUR_DIOXIDE"),
    NITROGEN_DIOXIDE(0x2C, "二氧化氮", "NITROGEN_DIOXIDE"),
    NITRIC_OXIDE(0x2D, "一氧化氮", "NITRIC_OXIDE"),
    HYDROGEN_CHLORIDE(0x2E, "氯化氢", "HYDROGEN_CHLORIDE"),
    HYDROGEN_CYANIDE(0x2F, "氰化氢", "HYDROGEN_CYANIDE"),
    CARBON_DIOXIDE(0x30, "二氧化碳", "CARBON_DIOXIDE"),
    CHLORINE(0x31, "氯气", "CHLORINE"),
    COMBUSTIBLE_GAS(0x32, "可燃气体", "COMBUSTIBLE_GAS"),
    C3H3N(0x33, "丙稀腊", "C3H3N"),
    HF(0x34, "氟化氢", "HF"),
    PH3(0x35, "磷化氢", "PH3"),
    CLO2(0x36, "二氧化氯", "CLO2"),
    C4H8S(0x37, "四氢噻酚", "C4H8S"),
    CH3I(0x38, "碘甲烷", "CH3I"),
    CHCL3(0x39, "三氯甲烷", "CHCL3"),
    SIH4(0x3A, "硅烷", "SIH4"),
    C2H3CL(0x3B, "氯乙烯", "C2H3CL"),
    COCL2(0x3C, "光气", "COCL2"),
    ASH3(0x3D, "三氢化砷", "ASH3"),
    HBR(0x3E, "漠化氢", "HBR"),
    CS2(0x3F, "二硫化碳", "CS2"),
    C6H12(0x40, "环己烷", "C6H12"),
    TOXIC_GAS(0x41, "毒性气体", "TOXIC_GAS"),
    METHYLAMINE(0x42, "一甲胺", "METHYLAMINE"),
    ANILINE(0x43, "甲胺", "ANILINE"),
    DMF(0x44, "DMF", "DMF"),
    ORGANIC_AMINE(0x45, "有机胺", "ORGANIC_AMINE"),
    SF6(0x46, "六氟化硫", "SF6"),
    ISOBUTYLENE(0x47, "异丁烯", "ISOBUTYLENE"),
    ANILINE_BENZYL(0x48, "苯胺", "ANILINE_BENZYL"),
    H2O2(0x49, "双氧水", "H2O2"),
    DICHLORINE(0x4A, "双光气", "DICHLORINE"),
    TRIETHYLAMINE(0x4B, "三乙胺", "TRIETHYLAMINE"),
    ETHYLENE(0x4C, "乙烯", "ETHYLENE"),
    NITRIC_ACID(0x4D, "硝酸", "NITRIC_ACID"),
    EPOXY_CHLOROPROPANE(0x4E, "环氧氯丙烷", "EPOXY_CHLOROPROPANE"),
    DICHLORO_2PROPANOL(0x4F, "二氯丙醇", "DICHLORO_2PROPANOL"),
    CCL4(0x50, "四氯化碳", "CCL4"),
    PM25(0x51, "PM2.5", "PM25"),
    PM10(0x52, "PM10", "PM10"),
    VOC(0x53, "VOC", "VOC"),
    TVOC(0x54, "TVOC", "TVOC"),
    SMOKE(0x55, "烟雾", "SMOKE"),
    PID(0x56, "PID", "PID"),
    RESERVED1(0x57, "预留", "RESERVED1"),
    RESERVED2(0x58, "预留", "RESERVED2"),
    RESERVED3(0x59, "预留", "RESERVED3"),
    TEMPERATURE(0x5A, "温度", "TEMPERATURE"),
    HUMIDITY(0x5B, "湿度", "HUMIDITY"),
    PRESSURE(0x5C, "压力", "PRESSURE"),
    FLOW(0x5D, "流量", "FLOW"),
    WIND_SPEED(0x5F, "风速", "WIND_SPEED"),
    WIND_DIRECTION(0x60, "风向", "WIND_DIRECTION"),
    LIQUID_LEVEL(0x61, "液位", "LIQUID_LEVEL"),
    ILLUMINANCE(0x62, "光照度", "ILLUMINANCE"),
    SOUND(0x63, "声音", "SOUND"),
    WEIGHT(0x64, "重量", "WEIGHT"),
    VOLUME(0x65, "体积", "VOLUME"),
    HEIGHT(0x66, "高度", "HEIGHT"),
    LENGTH(0x67, "长度", "LENGTH"),
    PRESSURE_2(0x68, "气压", "PRESSURE_2"),
    ALTITUDE(0x68, "海拔", "ALTITUDE"),
    VOLTAGE(0x70, "电压", "VOLTAGE"),
    ANGLE_X(0x71, "X 轴角度", "ANGLE_X"),
    ANGLE_Y(0x72, "Y 轴角度", "ANGLE_Y"),
    ANGLE_Z(0x73, "Z 轴角度", "ANGLE_Z"),
    ACCELERATION_X(0x74, "X 轴重力加速度", "ACCELERATION_X"),
    ACCELERATION_Y(0x75, "Y 轴重力加速度", "ACCELERATION_Y"),
    ACCELERATION_Z(0x76, "Z 轴重力加速度", "ACCELERATION_Z");

    private final int code;
    private final String chineseName;
    private final String gasType;


    public static GasTypeEnum getByCode(int code) {
        return Arrays.stream(GasTypeEnum.values()).filter(v -> code == v.getCode()).findFirst().orElse(UNKNOWN);
    }
}

