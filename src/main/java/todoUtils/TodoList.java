package todoUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.bson.types.ObjectId;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import todo.MongoDBConnect;

public class TodoList {
    // public static ArrayList<TodoList> Lists = new ArrayList<TodoList>();
    public static HashMap<Integer, TodoList> Lists = new HashMap<Integer, TodoList>();
    static int index = 1;
    ArrayList<ObjectId> todos = new ArrayList<ObjectId>();
    public String ListName;
    MongoClient client = MongoDBConnect.getMongoClient();
    MongoDatabase db = client.getDatabase("javatodo");
    MongoCollection<Document> collection = db.getCollection("todoList");
    ObjectId ListId = new ObjectId();

    public TodoList(String text) {
        System.out.println(todos);
        ListName = text;

        // Document test = new Document();
        // can use json to make document
        // Document test = Document.parse("{\"name\":\"Hitansh\"}");
        // collection.insertOne(test);
        // Creating a List in DB
        // dont create if exist i.e if find is not null.
        Document exists = collection.find(new Document().append("ListName", ListName)).first();
        // System.out.println("Exists:   " + exists);
        if (exists == null) {
            Document doc = new Document()
                    .append("_id", ListId)
                    .append("ListName", ListName)
                    .append("list", List.of());

            collection.insertOne(doc);
        }else{
            System.out.println("List with same name Already Exist! ");
        }

        // update the document
        // Document query = new Document().append("ListName", "Tasks");
        // Document update = new Document().append("$set", new Document().append("list",
        // todos));
        //
        // UpdateResult r = collection.updateOne(query, update);
        // System.out.println(r);
        Lists.put(index, this);
        index++;
    }

    // public ObjectId getList(int index){

    // }

    public void printList() {
        System.out.println(todos);
    }

    public void addItem(String text) {

        // Insert TODOS:
        TodoItem item = new TodoItem();
        ObjectId todoId = item.setItem(text);
        collection.updateOne(new Document().append("_id", ListId),
                new Document().append("$push", new Document().append("list", todoId)));

        // ObjectId ob = new ObjectId("67853947c0342a354f6817a9");
        // Document doc = collection.find("$eq",new Document().append("_id",
        // item.setItem(text)));
        // Document d = collection.find(new Document().append("_id", ob)).first();

        // todos.add(d);
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

    public static HashMap<Integer, TodoList> getALlLists() {
        System.out.println(Lists);
        for (Map.Entry<Integer, TodoList> ent : Lists.entrySet()) {
            System.out.println(ent.getKey() + " : " + ent.getValue().ListName);
        }
        return Lists;
    }

    public static TodoList getList(int idx) {
        System.out.println(Lists.get(idx).ListName + " list is Selected.");
        return Lists.get(idx);

        // TODO: inplement a function in main to fetch a todoList based on index
        // provided by user
    }
}
