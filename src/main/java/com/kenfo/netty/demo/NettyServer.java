package com.kenfo.netty.demo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;

/**
 * @author kenfo
 * @version V1.0
 * @Package com.kenfo.netty
 * @Description: netty 服务端启动类
 * @date 2018/4/2 下午12:57
 */
public class NettyServer {

    public static void main(String[] args) throws InterruptedException {
        ServerBootstrap server = new ServerBootstrap();

        //accept,read write
        EventLoopGroup parentGroup = new NioEventLoopGroup();//默认有一个线程
        EventLoopGroup childGroup = new NioEventLoopGroup();//默认是cpu核数的两倍
        server.group(parentGroup, childGroup);
        server.option(ChannelOption.SO_BACKLOG, 128);//加入客户端太多，服务端处理不过来，运行128个客户端排队等候

        server.channel(NioServerSocketChannel.class);//绑定channel

        server.childHandler(new ChannelInitializer<SocketChannel>() {
            protected void initChannel(SocketChannel ch) throws Exception {
                //解码器（\r\n）
                ch.pipeline().addLast(new DelimiterBasedFrameDecoder(Integer.MAX_VALUE, Delimiters.lineDelimiter()[0]));
                ch.pipeline().addLast(new SimpleServerHandle());
            }
        });

        ChannelFuture future = server.bind(8080).sync();
        future.channel().closeFuture().sync();//同步的等待关闭
    }
}
