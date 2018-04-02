package com.kenfo.netty.demo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.AttributeKey;

import java.nio.charset.Charset;

/**
 * @author kenfo
 * @version V1.0
 * @Package com.kenfo.netty
 * @Description: 客户端请求处理
 * @date 2018/4/2 下午1:32
 */
public class SimpleClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof ByteBuf){
            System.out.println(((ByteBuf)msg).toString(Charset.defaultCharset()));
        }
        ctx.channel().attr(AttributeKey.valueOf("ServerData")).set(((ByteBuf)msg).toString(Charset.defaultCharset()));
        ctx.channel().close();
    }
}
