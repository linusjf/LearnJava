package mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoDatabase;

public final class ConnectToDB {

  private ConnectToDB() {
    throw new IllegalStateException("Private constructor.");
  }

  public static void main(String... args) {

    // Creating a Mongo client
    MongoClient mongo = new MongoClient("localhost", 27_017);

    // Creating Credentials
    MongoCredential credential = MongoCredential.createCredential(
        "sampleUser", "myDB", "password".toCharArray());
    System.out.println("Connected to the database successfully");

    // Accessing the database
    MongoDatabase database = mongo.getDatabase("myDB");
    System.out.println("Credentials ::" + credential);
    System.out.println("Database ::" + database);
  }
}
