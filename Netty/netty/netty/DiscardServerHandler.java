package netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;
import java.util.logging.Level;
import java.util.logging.Logger;

/** Handles a server-side channel. */
@SuppressWarnings("checkstyle:trailingcomment")
public class DiscardServerHandler extends ChannelHandlerAdapter {  // (1)

  private static Logger logger =
      Logger.getLogger(DiscardServerHandler.class.getName());

  public DiscardServerHandler() {
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx,
                              Throwable cause) {  // (4)
    // Close the connection when an exception is raised.
    logger.log(Level.SEVERE, cause.getMessage(), cause);
    ctx.close();
  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) {
    ByteBuf in = (ByteBuf)msg;
    try {
      while (in.isReadable()) {  // (1)
        System.out.print((char)in.readByte());
        System.out.flush();
      }
    } finally {
      ReferenceCountUtil.release(msg);  // (2)
    }
  }
}
