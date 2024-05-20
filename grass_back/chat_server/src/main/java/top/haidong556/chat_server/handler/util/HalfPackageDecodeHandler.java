package top.haidong556.chat_server.handler.util;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

public class HalfPackageDecodeHandler extends LengthFieldBasedFrameDecoder {

    public HalfPackageDecodeHandler(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength, int lengthAdjustment, int initialBytesToStrip) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        // 判断是否为WebSocket二进制帧
        if (isWebSocketBinaryFrame(in)) {
            return super.decode(ctx, in); // 使用父类的解码逻辑
        } else {
            // 非WebSocket二进制帧，可以进行其他处理或忽略
            in.skipBytes(in.readableBytes()); // 这里简单地跳过所有字节
            return null;
        }
    }

    private boolean isWebSocketBinaryFrame(ByteBuf in) {
        // WebSocket二进制帧的opcode为2
        return in.readableBytes() > 0 && in.getByte(0) == 2;
    }
}
