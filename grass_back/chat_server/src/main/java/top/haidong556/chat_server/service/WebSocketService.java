package top.haidong556.chat_server.service;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import top.haidong556.chat_server.handler.*;
import top.haidong556.chat_server.handler.util.HalfPackageDecodeHandler;


public class WebSocketService {

    public void run(int port) {
        EventLoopGroup bossGroup=new NioEventLoopGroup(2);
        EventLoopGroup workerGroup=new NioEventLoopGroup(6);
        try{
            ServerBootstrap serverBootstrap=new ServerBootstrap();
            serverBootstrap.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline= socketChannel.pipeline();
                            pipeline.addLast("httpCodecHandler",new HttpServerCodec());//将HTTP请求和响应进行相应的编码（序列化为字节流）和解码（将字节流解析为HTTP请求和响应对象）。
                            pipeline.addLast("httpAggregatorHandler",new HttpObjectAggregator(65536)); // 聚合 HTTP 消息的帧
                            pipeline.addLast("websocketHandler",new WebSocketServerProtocolHandler("/websocket"));//主要负责处理 WebSocket 握手请求、升级为 WebSocket 连接以及处理 WebSocket 协议的一些协商过程。

                            pipeline.addLast("halfPackageDecodeHandler",new HalfPackageDecodeHandler(65535, 0, 2, 0, 2));//通过给包前加长度字段拆包粘包问题
                            pipeline.addLast("packageDecodeHandler",new PackageDecodeHandler());
                            pipeline.addLast("loginLoginoutHandler",new UserStateHandler());
                            pipeline.addLast("createConversationHandler",new CreateConversationHandler());
                            pipeline.addLast("persistentMessageHandler",new PersistentMessageHandler());
                            pipeline.addLast("sendHandler",new SendHandler());
//                            pipeline.addLast("packageEncodeHandler",new PackageEncodeHandler());
                            pipeline.addLast("responseHandler",new ResponseHandler());

                        }
                    });
            Channel channel=serverBootstrap.bind(port).sync().channel();
            channel.closeFuture().sync();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        finally{
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }

}
