package netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings({"checkstyle:trailingcomment",
                   "checkstyle:magicnumber",
                   "checkstyle:descendanttoken"})
public class TimeClientHandler extends ChannelHandlerAdapter {
  private static Logger logger =
      Logger.getLogger(TimeClientHandler.class.getName());

  public TimeClientHandler() {
  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) {
    ByteBuf m = (ByteBuf)msg;  // (1)
    try {
      long currentTimeMillis = (m.readUnsignedInt() - 2208988800L) * 1000L;
      System.out.println(new Date(currentTimeMillis));
      ctx.close();
    } finally {
      m.release();
    }
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    logger.log(Level.SEVERE, cause.getMessage(), cause);
    ctx.close();
  }
}
