package todoUtils;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;
import org.bson.types.ObjectId;
import todo.MongoDBConnect;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * TodoItem is a class that defines the structure of a todo item
 * <p>
 * id: int
 * <p>
 * text: String
 */
public class TodoItem {
    public int id;
    public String text;
    public boolean check;
    LocalDateTime dateTime;
    String StringDateTime;


    /**
     * setItem is a method that sets the id and text of a todo item
     *
     * @category setter
     * @param id:   int
     * @param text: String
     * @return TodoItem
     */

    public TodoItem setItem(int id, String text) {
        this.text = text;
        this.id = id;
        dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd / mm HH:mm");
        // DateTimeFormatter format = new DateTimeFormatter.ofPattern("dd / mm")
        StringDateTime = dateTime.format(formatter);
        try{
            MongoClient mongoClient = MongoDBConnect.getMongoClient();
            MongoDatabase database = mongoClient.getDatabase("javatodo");
            MongoCollection<Document> collection = database.getCollection("todos");
            InsertOneResult r = collection.insertOne(new Document()
                    .append("_id",new ObjectId())
                    .append("text",text)
                    .append("dateTime",StringDateTime)
            );
            System.out.println("Inserted: " + r);
        }catch (Exception e){
            System.out.println("Error in inserting: " + e);
        }
        return this;
    }
}
