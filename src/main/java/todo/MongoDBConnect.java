package todo;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;
import org.bson.types.ObjectId;


public class MongoDBConnect {
    public static MongoClient getMongoClient() {
        String connectionString = "mongodb+srv://thakurhitansh4325:MGuYKHiY5Gw31WHw@grocery-delivery.bh1pj.mongodb.net/?retryWrites=true&w=majority&appName=grocery-delivery";

        // Create a new client and connect to the server
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            try {
                // Send a ping to confirm a successful connection
                MongoDatabase database = mongoClient.getDatabase("javatodo");
                var collection = database.getCollection("todos");
                database.runCommand(new Document("ping", 1));
                System.out.println("Pinged your deployment. You successfully connected to MongoDB!");

                return mongoClient; // Return the client
            } catch (MongoException e) {
                e.printStackTrace();
            }
        }
        catch (Exception e) {
            System.out.println("Error in connecting to MongoDB: " + e);
            e.printStackTrace();
        }
        return null;
    }
}
