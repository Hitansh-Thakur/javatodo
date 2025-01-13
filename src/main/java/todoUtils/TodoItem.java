package todoUtils;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
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
    public String text;
    public boolean check;
    LocalDateTime dateTime;
    String StringDateTime;
    static MongoClient mongoClient = MongoDBConnect.getMongoClient();
    static MongoDatabase database = mongoClient.getDatabase("javatodo");
    static MongoCollection<Document> collection = database.getCollection("todos");
    

    /**
     * setItem is a method that sets the id and text of a todo item
     *
     * @category setter
     * @param id:   int
     * @param text: String
     * @return TodoItem
     */
    public TodoItem setItem(String text) {
        return setItem(text, false);
    }
    

    public TodoItem setItem( String text, boolean check) {
        this.text = text;
        this.check = check;
        dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd / mm HH:mm");
        StringDateTime = dateTime.format(formatter);
        try {
            collection.insertOne(new Document()
                    .append("_id", new ObjectId())
                    .append("text", text)
                    .append("dateTime", StringDateTime)
                    .append("check", check));
        } catch (MongoException e) {
            System.out.println("Error in inserting: " + e);
        }        
        return this;
    }

}
