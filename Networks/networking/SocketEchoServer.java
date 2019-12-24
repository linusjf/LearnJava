package networking;

/* SocketEchoServer.java
 *
 * Copyright (c) 2000 Sean Walton and Macmillan Publishers.  Use may be in
 * whole or in part in accordance to the General Public License (GPL).
 *
 * THIS SOFTWARE IS PROVIDED BY THE REGENTS AND CONTRIBUTORS ``AS IS'' AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED.  IN NO EVENT SHALL THE REGENTS OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
 * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 **
 * **************************************************************************
 *
 ** * SocketEchoServer.java ** *
 ** * ** *
 **
 * **************************************************************************
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class SocketEchoServer {
  private static final String UTF_8 = 
    StandardCharsets.UTF_8.name();

  private ServerSocket server;

  public SocketEchoServer(int portnum) {
    try {
      server = new ServerSocket(portnum);
    } catch (IOException err) {
      System.out.println(err);
    }
  }

  @SupportWarnings("PMD.LawOfDemeter")
  public void serve() {
      while (true) {
    try (
        Socket client = server.accept();
        BufferedReader r =
            new BufferedReader(
                new InputStreamReader(client.getInputStream(), UTF_8));
        PrintWriter w =
            new PrintWriter(
                new OutputStreamWriter(client.getOutputStream(), UTF_8),
                true);) {
        w.println("Welcome to the Java EchoServer.  Type 'bye' to close.");
        String line = "";
        while (!line.trim().equals("bye")) {
          line = r.readLine();
          if (line != null) 
            w.println("Got: " + line);
        } 
    } catch (IOException err) {
      System.err.println(err);
    }
      }
  }

  public static void main(String[] args) {
    SocketEchoServer s = new SocketEchoServer(9999);
    s.serve();
  }
}
