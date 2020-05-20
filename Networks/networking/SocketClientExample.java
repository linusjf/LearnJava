package networking;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class SocketClientExample {
  @SuppressWarnings({"PMD.DataflowAnomalyAnalysis", "PMD.LawOfDemeter"})
  public void startClient() throws IOException, InterruptedException {
    InetSocketAddress hostAddress = new InetSocketAddress("localhost", 8090);
    try (SocketChannel client = SocketChannel.open(hostAddress);) {
      System.out.println("Client..started");

      String threadName = Thread.currentThread().getName();

      // Send messages to server
      String[] messages = new String[] {threadName + ": test1",
                                        threadName + ": test2",
                                        threadName + ": test3"};

      for (String msg: messages) {
        byte[] message = msg.getBytes(StandardCharsets.UTF_8);
        ByteBuffer buffer = ByteBuffer.wrap(message);
        client.write(buffer);
        System.out.println(msg);
        buffer.clear();
        Thread.sleep(5000);
      }
    }
  }
}
