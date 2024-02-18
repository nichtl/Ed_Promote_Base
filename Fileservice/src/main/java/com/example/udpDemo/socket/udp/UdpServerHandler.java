package com.example.udpDemo.socket.udp;

import cn.hutool.core.util.HexUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wangdachuan
 * @version 1.0
 * @date 2023/3/14 23:12
 **/
@Slf4j
@Component
public class UdpServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    protected void messageReceived(ChannelHandlerContext channelHandlerContext, DatagramPacket datagramPacket)
            throws Exception {
        InetSocketAddress sender = datagramPacket.sender();
        String ip = sender.getAddress().getHostAddress();
        ByteBuf buf = datagramPacket.copy().content();
        try {
            byte[] data = new byte[buf.readableBytes()];
            buf.readBytes(data);
            String hexString = ByteBufUtil.hexDump(data);
            log.info("收到IP：{},发送的数据：{}", ip, hexString.toUpperCase());
            // 将长字符串每两个字符分割成一个字符串数组
            List<NettyPacket> nettyPackets = decode(channelHandlerContext, data);


            // TODO: 2023/12/19
            channelHandlerContext.writeAndFlush(new DatagramPacket(
                    // 回复内容，这里可以根据需要修改
                    buf,
                    datagramPacket.sender()
            ));
            // 下面进行业务代码处理
        } catch (Exception e) {
            log.error("报文解析异常", e);
        }
        // UDP返回数据写法
        channelHandlerContext.channel().writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer(HexUtil.decodeHex("1")), datagramPacket.sender()));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, DatagramPacket datagramPacket) throws Exception {
         this.messageReceived(channelHandlerContext,datagramPacket);
    }


//    protected ByteBuf assembledMessage(String msg) {
//
//        StringBuffer buffer = new StringBuffer("19");
//
//
//    }


    /**
     * 解码
     *
     * @param ctx
     * @auther sjt Administrator
     * @since 2023/12/17 19:50
     */
    protected List<NettyPacket> decode(ChannelHandlerContext ctx, byte[] dataByte) {
        // 读取头部数据 判断头部字节的内容，这里假设头部内容为固定值
        if (!"5E".equalsIgnoreCase(String.format("%02X", dataByte[0]))) {
            // 头部不匹配，可以处理相应的逻辑，例如关闭连接等
            ctx.close();
            return null;
        }
        //结束符
        if (!"5B".equalsIgnoreCase(String.format("%02X", dataByte[dataByte.length - 1]))) {
            log.info("报文结束符不为5B,丢弃当前报文");
            return null;
        }

        byte[] frameLength = Arrays.copyOfRange(dataByte, 1, 3);//帧长度 不包含起始符和结束符
        byte[] frameData = Arrays.copyOfRange(dataByte, 3, dataByte.length - 1);//按照帧长度取出后续所有字节 不含结束字节
        byte keyByte = dataByte[3];//密钥
        byte controlTypeByte = dataByte[4];//控制符
        byte deviceSpeciesByte = dataByte[5];//设备种类
        byte deviceTypeByte = dataByte[6];//设备类型
        byte[] deviceModelByte = {dataByte[7], dataByte[8]};//设备型号 2byte
        byte[] deviceId = Arrays.copyOfRange(dataByte, 9, 25);//设备 ID 16byte
        byte sort = dataByte[25];//帧序号 1byte
        byte[] timeBytes = Arrays.copyOfRange(dataByte, 26, 33);// 时间戳 yyyyMMddHHmmss
        byte[] dataPackage = Arrays.copyOfRange(dataByte, 33, dataByte.length - 2);//数据包

        int length = Integer.parseInt(ByteBufUtil.hexDump(frameLength), 16);
        if (frameData.length != length) {
            log.info("报文长度校验错误，丢弃当前报文");
            return null;
        }
//        byte lrc = frameData[length - 1];
//        if (lrc != LRC8ChecksumUtil.calculateLRC(Arrays.copyOfRange(frameData, 0, length - 1))) {
//            log.info("LRC校验未通过，丢弃当前报文");
//            return;
//        }

        List<NettyPacket> nettyPackets = new ArrayList();//需要发送的数据

        int keyNo = Integer.parseInt(String.valueOf(keyByte), 16);
        if (1 == keyNo) {
            dataPackage = AESUtil.decrypt("eVeX2g43w!$NrmKi", dataPackage);
            // TODO: 2023/12/3 解密成功后下发2、3密钥
            NettyPacket nettyPacket = new NettyPacket();
            nettyPacket.setNettyPacketType(NettyPacketTypeEnum.KEY);
            nettyPackets.add(nettyPacket);
        } else {
            if (2 == keyNo) {
                dataPackage = AESUtil.decrypt("ytnpwstqoa395wze", dataPackage);
            } else if (3 == keyNo) {
                dataPackage = AESUtil.decrypt("ixe7xgz0h628jsfq", dataPackage);
            } else {
                log.info("暂不支持的密钥号：{}", keyNo);
                return null;
            }
            int dataNum = Integer.parseInt(ByteBufUtil.hexDump(new byte[]{dataPackage[0]}), 16);//数据块的数量
            byte[] datas = Arrays.copyOfRange(dataPackage, 1, dataPackage.length);

            for (int i = 0; i < dataNum; i++) {
                if (0x50 == datas[i]) {
                    int num = Integer.parseInt(ByteBufUtil.hexDump(new byte[]{datas[2], datas[3]}), 16);// 传感器/事件数量
                    for (int j = 0; j < num; j++) {
                        byte[] data = Arrays.copyOfRange(datas, 4 + 9 * j, 4 + 9 * (j + 1));//

                        NettyPacket nettyPacket = new NettyPacket();
                        nettyPacket.setNettyPacketType(NettyPacketTypeEnum.REAL_TIME_DATA);
                        if (0x01 == datas[1]) {//传感器数据
                            nettyPacket.setDataType(0);
                            nettyPacket.setSn(new String(Arrays.copyOfRange(data, 0, 4)));
                            nettyPacket.setType(GasTypeEnum.getByCode(data[4]));
                            nettyPacket.setValue(new String(Arrays.copyOfRange(data, 5, 7)));
                            nettyPacket.setUnit(MeasurementUnitEnum.getByCode(data[7]));
                            nettyPacket.setNumPort(Integer.parseInt(data[8] + "", 16));
                        } else if (0x02 == datas[1]) {//事件数据
                            nettyPacket.setDataType(1);
                            nettyPacket.setType(EventEnum.getByCode(data[0]));
                            nettyPacket.setSn(new String(Arrays.copyOfRange(data, 1, 5)));
                            nettyPacket.setValue(new String(Arrays.copyOfRange(data, 5, 7)));
                            nettyPacket.setUnit(MeasurementUnitEnum.getByCode(data[7]));
                            nettyPacket.setNumPort(Integer.parseInt(data[8] + "", 16));
                        }
                        nettyPackets.add(nettyPacket);
                    }
                }
            }
        }
//        applicationEventPublisher.publishEvent(new NettyPacketEvent(ctx.channel().id(), nettyPackets));
        return nettyPackets;
    }
}
