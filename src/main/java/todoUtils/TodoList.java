package todoUtils;

import java.util.ArrayList;
import java.util.List;

import javax.print.Doc;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;

import todo.MongoDBConnect;

public class TodoList {
    public static ArrayList<TodoList> Lists = new ArrayList<TodoList>();
    ArrayList<Document> todos = new ArrayList<Document>();
    private int cnt;
    public String ListName;
    MongoClient client = MongoDBConnect.getMongoClient();
    MongoDatabase db = client.getDatabase("javatodo");
    MongoCollection<Document> collection = db.getCollection("todoList");
    ObjectId ListId = new ObjectId();

    public TodoList(String text) {
        System.out.println(todos);
        ListName = text;
        Document doc = new Document()
                .append("_id", ListId)
                .append("ListName", ListName)
                .append("list", List.of());
        // update the document

        Document query = new Document().append("ListName", "Tasks");
        // Add a new item to the list
        // list is a field in the document of type array
        // doc structure: {ListName: "Tasks", list: []}
        // add "abc" to the list
        // result : {ListName: "Tasks", list: ["abc"]}
        // append "abc to the list"
        // Document update = new Document().append("$set", new Document().append("list",
        // List.of().add("abc")));
        // how to add Mongo Objects to the
        Document update = new Document().append("$set", new Document().append("list", todos));

        collection.insertOne(doc);
        UpdateResult r = collection.updateOne(query, update);
        System.out.println(r);
        Lists.add(this);
    }

    public void printList() {
        System.out.println(todos);
    }

    public void addItem(String text) {

        // Insert TODOS:
        TodoItem item = new TodoItem();
        ObjectId todoId = item.setItem(text);
        System.out.println("List ID ------------------> "+ ListId);
        collection.updateOne(new Document().append("_id", ListId), new Document().append("$push", new Document().append("list", todoId)));




        cnt += 1;
        ObjectId ob = new ObjectId("67853947c0342a354f6817a9");
        // Document doc = collection.find("$eq",new Document().append("_id",
        // item.setItem(text)));
        Document d = collection.find(new Document().append("_id", ob)).first();

        todos.add(d);
    }
    // public ArrayList<TodoItem> ListTodos(){
    // int no = 0;
    // System.out.println("\nList Name: " + ListName);

    // while (no<cnt) {
    // TodoItem item = todos.get(no);
    // System.out.println((no+1) + ": " + item.text + "\t"+item.StringDateTime);
    // no++;
    // }
    // return todos;
    // }

    public static ArrayList<TodoList> getLists() {
        for (TodoList list : Lists) {
            System.out.println(list.ListName);
        }
        return Lists;
    }
}
