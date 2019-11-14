package networking;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPEchoServer extends UDPServer {
  public static final int DEFAULT_PORT = 7;

  public UDPEchoServer() {
    super(DEFAULT_PORT);
  }

  public UDPEchoServer(int port) {
    super(port);
  }

  @Override
  public void respond(DatagramSocket socket, DatagramPacket packet) throws IOException {
    // clang-format off
    DatagramPacket outgoing =
        new DatagramPacket(
            packet.getData(), packet.getLength(), packet.getAddress(), packet.getPort());

    // clang-format on
    socket.send(outgoing);
  }

  public static void main(String[] args) {
    int port = args.length > 0 ? readPort(args[0], DEFAULT_PORT) : DEFAULT_PORT;
    UDPServer server = new UDPEchoServer(port);
    Thread t = new Thread(server);
    t.start();
  }
}
