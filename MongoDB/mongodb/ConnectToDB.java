package mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.MongoSecurityException;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

@SuppressWarnings("PMD.LawOfDemeter")
public final class ConnectToDB {

  private static final int MONGO_DB_PORT = 27_017;
  private static final String MONGO_DB_HOST = "localhost";
  private static final String DB_NAME = "myDB";

  private ConnectToDB() {
    throw new IllegalStateException("Private constructor.");
  }

  public static void main(String... args) {
    try {
      MongoCredential auth = MongoCredential.createCredential(
          "linus", DB_NAME, "secret123".toCharArray());

      ServerAddress serverAddress =
          new ServerAddress(MONGO_DB_HOST, MONGO_DB_PORT);
      List<ServerAddress> servers = new ArrayList<>();
      servers.add(serverAddress);

      final MongoClientOptions.Builder options = MongoClientOptions.builder();
      // Creating a Mongo client
      MongoClient mongo = new MongoClient(servers, auth, options.build());

      System.out.println("Connected to the database successfully");

      // Accessing the database
      MongoDatabase db = mongo.getDatabase(DB_NAME);
      MongoCollection<Document> col = db.getCollection("mycol");
      for (Document doc: col.find())
        System.out.println(doc);

      // Creating a collection
      db.createCollection("sampleCollection");
      System.out.println("Collection created");

      Document document =
          new Document("title", "MongoDB")
              .append("id", 1)
              .append("description", "database")
              .append("likes", 100)
              .append("url", "http://www.tutorialspoint.com/mongodb/")
              .append("by", "tutorials point");
      col.insertOne(document);
      System.out.println("Document inserted successfully");
      for (Document doc: col.find())
        System.out.println(doc);

      col.updateOne(Filters.eq("id", 1), Updates.set("likes", 150));
      System.out.println("Document update successfully...");

      // Deleting the documents
      col.deleteOne(Filters.eq("id", 1));
      System.out.println("Document deleted successfully...");

      // Retieving a collection
      MongoCollection<Document> collection =
          db.getCollection("sampleCollection");
      // Dropping a Collection
      collection.drop();

      for (String name: db.listCollectionNames())
        System.out.println(name);
    } catch (MongoSecurityException mse) {
      System.err.println(mse.getMessage());
    }
  }
}
