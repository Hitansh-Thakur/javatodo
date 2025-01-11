package todo;

import org.bson.types.ObjectId;
import todoUtils.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.*;
import org.bson.Document;

public class todo {
    public static void main(String[] args) {

        MongoClient mongoClient = MongoDBConnect.getMongoClient();
        MongoDatabase database = mongoClient.getDatabase("javatodo");
        MongoCollection<Document> collection = database.getCollection("todos");

        try{
            InsertOneResult r = collection.insertOne(new Document()
                    .append("_id",new ObjectId())
                    .append("a","b")
            );
            System.out.println("Inserted: " + r);
        }catch (Exception e){
            System.out.println("Error in inserting: " + e);
        }

        TodoList todo = new TodoList("Shopping List");
        todo.addItem("Pens");
        todo.addItem("Drawing Book");
        todo.addItem("Geometry Box");
        todo.ListTodos();

        TodoList todo1 = new TodoList("Tasks");
        todo1.addItem("Study");
        todo1.addItem("Water the plants");
        todo1.addItem("Work on Project");
        todo1.ListTodos();

        System.out.println("\nTotal Lists created: ");
        TodoList.getLists();
    }
}

