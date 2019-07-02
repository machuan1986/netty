package com.yusys.netty.thrid;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @Description
 * @auther machuan
 * @create 2019-07-02 11:04
 */
public class MyChatServer {
    public static void main(String[] args) throws Exception{
        EventLoopGroup boos = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();

        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(boos,worker).channel(NioServerSocketChannel.class)
                    .childHandler(new MychatServerInitlizer());

            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
            channelFuture.channel().closeFuture().sync();
        }finally {

            boos.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}
