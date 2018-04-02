package com.kenfo.netty.demo;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.AttributeKey;

/**
 * @author kenfo
 * @version V1.0
 * @Package com.kenfo.netty
 * @Description: netty客户端
 * @date 2018/4/2 下午1:26
 */
public class NettyClient {

    public static void main(String[] args) throws InterruptedException {

        Bootstrap client = new Bootstrap();
        //没有了accept事件
        EventLoopGroup group = new NioEventLoopGroup();
        client.group(group);
        client.channel(NioSocketChannel.class);
        client.handler(new ChannelInitializer<NioSocketChannel>() {
            protected void initChannel(NioSocketChannel ch) throws Exception {
                ch.pipeline().addLast(new StringEncoder());//字符串解码器
                //\r\n解码器
                ch.pipeline().addLast(new DelimiterBasedFrameDecoder(Integer.MAX_VALUE, Delimiters.lineDelimiter()[0]));
                ch.pipeline().addLast(new SimpleClientHandler());
            }
        });


        ChannelFuture future = client.connect("127.0.0.1", 8080).sync();
        for(int i=0; i<100; i++){
            String msg =  "ssss" + i + "\r\n";//发送字符串使用new StringEncoder()编码器
            future.channel().writeAndFlush(msg);
        }
        future.channel().closeFuture().sync();
        Object result = future.channel().attr(AttributeKey.valueOf("ServerData")).get();
        System.out.println(result.toString());


    }
}
