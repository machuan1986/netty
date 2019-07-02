package com.yusys.netty.thrid;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @Description
 * @auther machuan
 * @create 2019-07-02 11:39
 */
public class MyChatClinet {
    public static void main(String[] args) throws Exception{
        NioEventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group).channel(NioSocketChannel.class).handler(new MyChatClinetInitlizer());
            Channel channel = bootstrap.connect("localhost", 8899).sync().channel();
//            channelFuture.channel().closeFuture().sync();

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while (true){
                channel.writeAndFlush(br.readLine() +"\r\n");
            }
        }finally {
            group.shutdownGracefully();
        }

    }
}
