package netty.time;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import java.util.List;

public class TimeDecoder extends ReplayingDecoder<Void> {
  @Override
  protected void decode(ChannelHandlerContext ctx,
                        ByteBuf in,
                        List<Object> out) {
    out.add(in.readBytes(4));
  }
}
