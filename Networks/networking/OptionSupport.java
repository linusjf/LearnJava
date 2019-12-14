package networking;

import java.io.IOException;
import java.net.SocketOption;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.DatagramChannel;
import java.nio.channels.NetworkChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public enum OptionSupport {
  ;

  public static void main(String[] args) {
    try {
      printOptions(SocketChannel.open());
      printOptions(ServerSocketChannel.open());
      printOptions(AsynchronousSocketChannel.open());
      printOptions(AsynchronousServerSocketChannel.open());
      printOptions(DatagramChannel.open());
    } catch (IOException ioe) {
      System.err.println(ioe);
    }
  }

  private static void printOptions(NetworkChannel channel) throws IOException {
    System.out.println(channel.getClass().getSimpleName() + " supports:");
    for (SocketOption<?> option : channel.supportedOptions()) {
      try {
        System.out.println(option.name() + ": " + channel.getOption(option));
      } catch (AssertionError ae) {
        System.err.println("Option not found for : " + option.name());
      }
    }
    System.out.println();
    channel.close();
  }
}
