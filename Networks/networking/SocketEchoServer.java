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
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketEchoServer {
  private ServerSocket server;

  public SocketEchoServer(int portnum) {
    try {
      server = new ServerSocket(portnum);
    } catch (IOException err) {
      System.out.println(err);
    }
  }

  public void serve() {
    try {
      while (true) {
        Socket client = server.accept();
        BufferedReader r =
            new BufferedReader(new InputStreamReader(client.getInputStream()));
        PrintWriter w = new PrintWriter(client.getOutputStream(), true);
        w.println("Welcome to the Java EchoServer.  Type 'bye' to close.");
        String line;
        do {
          line = r.readLine();
          if (line != null)
            w.println("Got: " + line);
        } while (!line.trim().equals("bye"));
        client.close();
      }
    } catch (IOException err) {
      System.err.println(err);
    }
  }

  public static void main(String[] args) {
    SocketEchoServer s = new SocketEchoServer(9999);
    s.serve();
  }
}
