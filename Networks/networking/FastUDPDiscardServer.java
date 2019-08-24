package networking;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class FastUDPDiscardServer extends UDPServer {
  public static final int DEFAULT_PORT = 9;

  public FastUDPDiscardServer() {
    super(DEFAULT_PORT);
  }

  public FastUDPDiscardServer(int port) {
    super(port);
  }

  public static void main(String[] args) {
    int port = args.length > 0 ? readPort(args[0], DEFAULT_PORT) : DEFAULT_PORT;
    UDPServer server = new FastUDPDiscardServer(port);
    Thread t = new Thread(server);
    t.start();
  }

  @Override
  public void respond(DatagramSocket socket, DatagramPacket request) {
  // empty, do-nothing method
  }
}
