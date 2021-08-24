package com.learn_netty_one.SimapleChat.CommandBeans;

/**
 * @Author ni
 * @description
 * @ 2021/3/31
 */
public class posData extends BaseCommBean{

    private  float lon;//经度
    private  float lat;//纬度
    private  float alt;//高度
    private  float speed;//速度
    private  String time;//定位时间
    private  float mileage;//里程（m），上报该定位时间里程表上的里程值，如无里程表填0.
    private  byte  gps_status;//卫星状态：0-网络定位，1-定位成功
    private  char  Wrist_id;//固定000000
    private  byte  data_type;//byte  数据类型：0为普通定位数据1为低电报警数据2为关机报警数据3为腕带剪断报警数据6为心率异常报警数据-1为签到数据
    private  byte  battery;//电量，范围在0---100之间
    private  byte  Cutoff_times;//剪断次数，固定为0
    private  byte  Cutoff_status;//剪断状态：0为闭合，1位剪断
    private  byte  Sparate_status;//固定为-1，即 0xFF
    private  byte  Heart_rate;//心率值，取值范围0-255(unsigned char类型)



}
