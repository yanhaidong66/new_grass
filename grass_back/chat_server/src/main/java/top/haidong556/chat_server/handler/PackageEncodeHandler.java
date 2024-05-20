package top.haidong556.chat_server.handler;

import io.netty.channel.*;
import top.haidong556.chat_server.common.codec.PackageCodec;
import top.haidong556.chat_server.entity.MessagesPackage;

public class PackageEncodeHandler extends SimpleChannelInboundHandler<MessagesPackage> {


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessagesPackage messagesPackage) throws Exception {
        byte[] encoded = PackageCodec.encode(messagesPackage);
        System.out.println("encode package:"+encoded);
        channelHandlerContext.fireChannelRead(encoded);
    }
}
