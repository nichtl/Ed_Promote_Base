package com.nicht.promote.fileservice.zkNetty.Server;


import com.nicht.promote.fileservice.SimapleChat.Beans.WatchBeans;
import com.nicht.promote.fileservice.SimapleChat.CommandBeans.WatchConstant;
import com.nicht.promote.fileservice.zkNetty.OnSocketListener;
import com.nicht.promote.fileservice.zkNetty.Utils.ByteUtils;
import com.nicht.promote.fileservice.zkNetty.Utils.WatchUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.apache.log4j.Logger;

/**
 * @Author Nicht
 * @description  服务端处理文本
 * @ 2021/1/5  SimpleChannelInboundHandler  ChannelInboundHandler 4.x
 */
@ChannelHandler.Sharable
public class ZKInboundHandle implements ChannelInboundHandler {   /*TextWebSocketFrame*/
    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    private static Logger logger = Logger.getLogger(ZKInboundHandle.class);

    private OnSocketListener listener;
    public ZKInboundHandle(OnSocketListener onSocketListener){
        this.listener = onSocketListener;
    }
    @Override
    public  void handlerAdded(ChannelHandlerContext ctx){   //新加入handle 触发 时给现有的channl 发送通知
      /*  Channel incoming = ctx.channel();
        String res  =  "2a4c4f4c4153090000#";
        for(Channel channel : channels){
            if(channel ==incoming) {
                 channel.writeAndFlush("Server - " + incoming.remoteAddress() + "加入\n");
                 channel.writeAndFlush(res);
            }
        }*/
        channels.add(ctx.channel());
    }
    @Override
    public  void  handlerRemoved(ChannelHandlerContext ctx){
        Channel  incoming = ctx.channel();
            System.out.println("Client - " +incoming.remoteAddress()+"离开\n");
            ctx.close();
        channels.remove(ctx.channel());

    }

    @Override
    public void channelRegistered(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    @Override
    public void channelUnregistered(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    @Override
    public  void   channelActive(ChannelHandlerContext ctx){
        Channel incoming = ctx.channel();
        System.out.println("Client  ::"+incoming.remoteAddress()+"在线");
    }

    @Override
    public  void channelInactive(ChannelHandlerContext ctx) {
        Channel incoming = ctx.channel();
        System.out.println("Client" + incoming.remoteAddress()+"掉线");
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        try {
//            Channel incoming = ctx.channel();  //当前channel 连接
//            if (listener != null){
//                listener.onReceive(ctx.channel(),msg);
//            }
//        } finally {
//            ReferenceCountUtil.release(msg);
//        }
        WatchBeans watchmsg = (WatchBeans) msg;
        Channel incoming = ctx.channel();  //当前channel 连接
        /*上传类*/
        if(WatchConstant.UploadMainPro.equals(watchmsg.getMainpro())) {
            if (!WatchConstant.UploadSecProCode.UploadFileData.equals(watchmsg.getSecpro())) {
                switch (watchmsg.getSecpro()){
                    case WatchConstant.UploadSecProCode.UploadFileRequest:
                        /*下发文件标识通知上传*/
                        ByteUtils.send2client(incoming, WatchConstant.CenterReplyCommand.FileRequestReply);
                        break;
                    case WatchConstant.UploadSecProCode.UploadLocateData:
                        /*定位上报消息回复*/
                        if ("-1".equals(watchmsg.getLocateMsg().getData_type())) {//回复签到成功
                            System.out.println(watchmsg.getLocateMsg().toString());
                            ByteUtils.send2client(incoming, WatchUtils.CreateSosCommandStr("王五","15824914901"));
                            ByteUtils.send2client(incoming, WatchConstant.CenterReplyCommand.DailySignInReply);
                        }
                        break;
                    case WatchConstant.UploadSecProCode.UploadFileDataResponse:
                        /*通知继续上传*/
                        System.out.println("回复接受文件完毕" + WatchConstant.CenterReplyCommand.UploadFileReply);
                        ByteUtils.send2client(incoming, WatchConstant.CenterReplyCommand.UploadFileReply);
                        break;
                    case   WatchConstant.UploadSecProCode.TextMsg:
                        /*终端上传短信*/
                        System.out.println(watchmsg.getSms().toString());
                        break;
                    case   WatchConstant.UploadSecProCode.UploadFileData:
                        System.out.println("成功上传保存文件");
                        ByteUtils.byte2image(ByteUtils.Byf2Bytes(ByteUtils.bytes2Bytebuf(watchmsg.getBytesdata())), "/tmp" + watchmsg.getFileInfo().getFile_name());
                    default: break;

                }
            }

        }
        /*下发类*/
        if (WatchConstant.IssueMainPro.equals(watchmsg.getMainpro())){
            switch (watchmsg.getSecpro()){
                /**短信处理*/
                case WatchConstant.IssueSecProCode.TextMsgRes:
                    if(watchmsg.getCommandSendSuccess()){
                        System.out.println("短信发送成功: "+watchmsg.getSendTime());
                    }else {
                        System.out.println("短信发送失败:");
                    }
                    break;
                /**同步设备参数*/
                case WatchConstant.IssueSecProCode.SyncDeviceSetting:
                    System.out.println("同步终端参数成功: "+watchmsg.getStr_Data());
                    break;
                /**其他统一格式回复处理*/
                default:
                    System.out.println("命令下发终端回复结果: "+watchmsg.getStr_Data()); break;
            }


        }
        ReferenceCountUtil.safeRelease(msg);///bytebuf引用释放*/
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext channelHandlerContext) throws Exception {
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {

    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    @Override
    public  void exceptionCaught (ChannelHandlerContext ctx ,Throwable cause){
        Channel incoming = ctx.channel();
        System.out.println("Client:"+incoming.remoteAddress()+"异常");
        // 当出现异常就关闭连接
        ctx.close();
        cause.printStackTrace();
    }


}
