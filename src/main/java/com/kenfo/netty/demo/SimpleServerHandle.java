package com.kenfo.netty.demo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;

/**
 * @author kenfo
 * @version V1.0
 * @Package com.kenfo.netty
 * @Description: TODO
 * @date 2018/4/2 下午1:21
 */
public class SimpleServerHandle extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        if(msg instanceof ByteBuf){
            System.out.println(((ByteBuf)msg).toString(Charset.defaultCharset()));
        }

        String result = "hello client";
        ByteBuf buf = Unpooled.buffer();
        buf.writeBytes(result.getBytes());
        ctx.channel().writeAndFlush(buf);

        ByteBuf buf2 = Unpooled.buffer();
        buf2.writeBytes("\r\n".getBytes());
        ctx.channel().writeAndFlush(buf2);
    }
}
