package netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings({"checkstyle:trailingcomment",
                   "checkstyle:magicnumber",
                   "checkstyle:descendanttoken"})
public class TimeServerHandler extends ChannelHandlerAdapter {

  private static Logger logger =
      Logger.getLogger(TimeServerHandler.class.getName());

  public TimeServerHandler() {
  }

  @Override
  public void channelActive(final ChannelHandlerContext ctx) {  // (1)
    final ByteBuf time = ctx.alloc().buffer(4);                 // (2)
    time.writeInt((int)(System.currentTimeMillis() / 1000L + 2208988800L));

    final ChannelFuture f = ctx.writeAndFlush(time);  // (3)
    f.addListener(new ChannelFutureListener() {
      @Override
      public void operationComplete(ChannelFuture future) {
        assert f == future;
        ctx.close();
      }
    });  // (4)
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    // Close the connection when an exception is raised.
    logger.log(Level.SEVERE, cause.getMessage(), cause);
    ctx.close();
  }
}
