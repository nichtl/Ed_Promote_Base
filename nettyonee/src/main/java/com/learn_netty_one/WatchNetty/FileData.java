package com.learn_netty_one.WatchNetty;

/**
 * @Author ni
 * @description
 * @ 2021/3/30
 */
public class FileData  extends   WatchCommand{

    private  String  FileTicket; //文件标示,
    private  String  pack_num; //总分包个数,每次最多传输4000个字节的内容，计算方法为num = 文件总长度/ 4000 ，如果长度不能被4000整除，则累加1，即num ++;
    private  String  pack_ind;//当前包序号
    private  byte[]  data;//数据








}
