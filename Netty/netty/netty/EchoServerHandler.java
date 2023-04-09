package netty;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import java.util.logging.Level;
import java.util.logging.Logger;

/** Handles a server-side channel. */
@SuppressWarnings("checkstyle:trailingcomment")
public class EchoServerHandler extends ChannelHandlerAdapter {  // (1)

  private static Logger logger =
      Logger.getLogger(EchoServerHandler.class.getName());

  public EchoServerHandler() {
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
    ctx.write(msg);  // (1)
    ctx.flush();     // (2)
  }
}
