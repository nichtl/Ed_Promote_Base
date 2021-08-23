package com.nicht.promote.fileservice.SimapleChat.Mycodec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * @Author Nicht
 * @Description
 * @Time 2021/5/21
 * @Link
 */
public class decoder extends ReplayingDecoder<String> {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        switch (state()){

        }
    }
}
