package com.test.demo.netty;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.net.InetAddress;

public class HelloServerHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {

        System.out.println(channelHandlerContext.channel().remoteAddress() + "Say : " + s);

        // 返回客户端消息 - 我已经接收到了你的消息
        channelHandlerContext.writeAndFlush("Received your message !\\n");

    }

    /*
20      *
21      * 覆盖 channelActive 方法 在channel被启用的时候触发 (在建立连接的时候)
22      *
23      * channelActive 和 channelInActive 在后面的内容中讲述，这里先不做详细的描述
24      * */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("RamoteAddress : " + ctx.channel().remoteAddress() + " active !");

        ctx.writeAndFlush( "Welcome to " + InetAddress.getLocalHost().getHostName() + " service!\n");

        super.channelActive(ctx);
    }
}
