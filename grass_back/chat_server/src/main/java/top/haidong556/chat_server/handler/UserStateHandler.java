package top.haidong556.chat_server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import top.haidong556.chat_server.common.GlobalContext;
import top.haidong556.chat_server.entity.Message;
import top.haidong556.chat_server.entity.MessagesPackage;

import java.util.List;

public class UserStateHandler extends SimpleChannelInboundHandler<MessagesPackage> {
    private GlobalContext context=GlobalContext.getInstance();
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessagesPackage messagesPackage) throws Exception {
        List<Message> messages = messagesPackage.getMessages();
        for(Message m:messages){
            long messageSenderId = m.getMessageSenderId();
            context.getUserChannelMap().put(messageSenderId,channelHandlerContext.channel());
            context.getChannelUserMap().put(channelHandlerContext.channel(),messageSenderId);
        }
        channelHandlerContext.fireChannelRead(messagesPackage);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Long userId = context.getChannelUserMap().get(ctx.channel());
        context.getUserChannelMap().remove(userId);
        context.getUserChannelMap().remove(ctx.channel());
        super.channelInactive(ctx);
    }
}
