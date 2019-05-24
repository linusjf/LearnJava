package stefano.lupo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public enum TestProxy {

	;

	private static String FILE = "urls.txt";

  private static String proxyHost = "localhost";

  private static int proxyPort = 8085;

	public static void main(String[] args) {

/**		System.setProperty("http.proxyHost","127.0.0.1");
		System.setProperty("http.proxyPort","8085");
		System.setProperty("https.proxyHost","127.0.0.1");
		System.setProperty("https.proxyPort","8085");
*/
		String fileName = FILE;
		if (args.length > 0)
			fileName = args[0];

		String[] urls = readURLsFromFile(fileName);
		testURLs(urls);
	}

	private static String[] readURLsFromFile(String fileName)
	{
		String[] data = new String[]{};
    try {
    BufferedReader reader = new BufferedReader(new FileReader(fileName));
		List<String> lines = new ArrayList<String>();

		String line = reader.readLine();
    while(line != null) {
			lines.add(line);
      line = reader.readLine();
		}
		reader.close();
		data = lines.toArray(new String[]{});
    } catch (IOException ioe) {
      System.err.println(ioe.getMessage());
    }
    return data;
	}

	private static void testURLs(String[] urls) {
		for (String strUrl: urls) {
			try {
        Proxy proxy = null;
				URL url = new URL(strUrl);
        System.out.println("Connecting to ..." + strUrl);
        if (strUrl.startsWith("http"))
          proxy = new Proxy(Proxy.Type.HTTP,
              new InetSocketAddress(proxyHost, proxyPort));
				URLConnection connection = url.openConnection(proxy);
        BufferedReader in = new BufferedReader(
            new InputStreamReader(
            connection.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) 
            System.out.println(inputLine);
        in.close();
			} catch (IOException e) {
				System.err.println("Error creating HTTP(S) connection: " + e.getMessage());
			}
		}
	}
}
