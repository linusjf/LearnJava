package pmdtests;

import java.io.InputStream;
import java.io.IOException;

public class TryWithResources {
    public void run() {
        
        try {
          InputStream  in = openInputStream();
            int i = in.read();
in.close();
        } catch (IOException e) {
            e.printStackTrace();
        } 
}
}
