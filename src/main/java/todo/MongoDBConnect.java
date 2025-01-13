package todo;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import io.github.cdimascio.dotenv.Dotenv;
import org.bson.Document;

public class MongoDBConnect {
    public static MongoClient getMongoClient() {
        // get the connection string from the environment variable
        Dotenv dotenv = Dotenv.load();

        String connectionString = dotenv.get("CONNECTION_URI");

        if (connectionString == null) {
            System.out.println("Connection String is null. Please check your environment variables.");
            return null;
        }

        System.out.println("Connection String: " + connectionString);

        // dont define the client inside the try block or it will not be accessible
        // outside the block
        MongoClient mongoClient = MongoClients.create(connectionString);
        try {
            // Send a ping to confirm a successful connection
            MongoDatabase database = mongoClient.getDatabase("javatodo");
            database.runCommand(new Document("ping", 1));
            System.out.println("Pinged your deployment. You successfully connected to MongoDB!");
            return mongoClient;
        } catch (MongoException e) {
            e.printStackTrace();
        }
        return null;
    }
}
