package com.yusys.netty.frist;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @Description
 * @auther machuan
 * @create 2019-07-01 16:17
 */
public class TestHttpServer  {
    public static void main(String[] args) throws Exception {

        NioEventLoopGroup boos = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(boos, worker).channel(NioServerSocketChannel.class)
            .childHandler(new TestServerInitializer());
            ChannelFuture sync = serverBootstrap.bind(8899).sync();
            sync.channel().closeFuture().sync();
        } finally {
            boos.shutdownGracefully();
            worker.shutdownGracefully();
        }

    }
}
