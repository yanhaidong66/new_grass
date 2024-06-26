package top.haidong556.chat_server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import top.haidong556.chat_server.entity.MessagesPackage;

public class ResponseHandler extends SimpleChannelInboundHandler<MessagesPackage> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessagesPackage messagesPackage) throws Exception {
        ByteBuf response = Unpooled.copiedBuffer("ok".getBytes());
        channelHandlerContext.writeAndFlush(new TextWebSocketFrame(response));
    }
}
