package stefano.lupo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public enum TestProxy {

	;

	private static String FILE = "urls.txt";

	public static void main(String[] args) {

		System.setProperty("http.proxyHost","127.0.0.1");
		System.setProperty("http.proxyPort","8085");
		System.setProperty("https.proxyHost","127.0.0.1");
		System.setProperty("https.proxyPort","8085");

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
				URL url = new URL(strUrl);
				URLConnection connection = url.openConnection();
				connection.connect();
			} catch (IOException e) {
				System.err.println("Error creating HTTP(S) connection: " + e.getMessage());
			}
		}
	}
}
