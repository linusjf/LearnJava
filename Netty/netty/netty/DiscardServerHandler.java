package netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/** Handles a server-side channel. */
public class DiscardServerHandler extends ChannelHandlerAdapter {  // (1)

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) {  // (2)
    // Discard the received data silently.
    ((ByteBuf)msg).release();  // (3)
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx,
                              Throwable cause) {  // (4)
    // Close the connection when an exception is raised.
    cause.printStackTrace();
    ctx.close();
  }
}
