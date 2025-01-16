package todoUtils;

import com.mongodb.MongoException;
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
    public ObjectId id;
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
    public ObjectId setItem(String text) {
        return setItem(text, false);
    }
    

    public ObjectId setItem( String text, boolean check) {
        this.text = text;
        this.check = check;
        this.id = new ObjectId();
        dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd / mm HH:mm");
        StringDateTime = dateTime.format(formatter);
        try {
            InsertOneResult r = collection.insertOne(new Document()
                    .append("_id", id)
                    .append("text", text)
                    .append("dateTime", StringDateTime)
                    .append("check", check));
                    // get document reference that is inserted


            System.out.println("----------------------------->\n"+r);
        } catch (MongoException e) {
            System.out.println("Error in inserting: " + e);
        }        
        return this.id;
    }

}
