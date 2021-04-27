package mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoDatabase;

public final class ConnectToDB {

  private ConnectToDB() {
    throw new IllegalStateException("Private constructor.");
  }

  public static void main(String args[]) {

    // Creating a Mongo client
    MongoClient mongo = new MongoClient("localhost", 27017);

    // Creating Credentials
    MongoCredential credential = MongoCredential.createCredential(
        "sampleUser", "myDb", "password".toCharArray());
    System.out.println("Connected to the database successfully");

    // Accessing the database
    MongoDatabase database = mongo.getDatabase("myDb");
    System.out.println("Credentials ::" + credential);
  }
}
