package todoUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import todo.MongoDBConnect;

public class TodoList {
    // public static ArrayList<TodoList> Lists = new ArrayList<TodoList>();
    public static HashMap<Integer, TodoList> Lists = new HashMap<Integer, TodoList>();
    static int index = 1;
    ArrayList<ObjectId> todos = new ArrayList<ObjectId>();
    public String ListName;
    ObjectId ListId;

    MongoClient client = MongoDBConnect.getMongoClient();
    MongoDatabase db = client.getDatabase("javatodo");
    MongoCollection<Document> collection = db.getCollection("todoList");
    MongoCollection<Document> todosCollection = db.getCollection("todos");

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
        if (exists == null) {
            ListId = new ObjectId();
            Document doc = new Document()
            .append("_id", ListId)
            .append("ListName", ListName)
            .append("list", List.of());
            
            collection.insertOne(doc);
        }else{
            // Assign the id of exsisting list to the currnt obj.
            ListId = (ObjectId) exists.get("_id");
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

    public void printList() {
        System.out.println("-------------------->");
        // TODO: fetch todos from database
        // TODO: the new obj shouild not be created if list already exist or it will assign new ListId to that
        Document doc = new Document().append("_id", this.ListId);
        Document CurrList = collection.find(doc).first();
        System.out.println("list id: " + ListId);
        System.out.println("DB list: " + CurrList.get("list"));
        // above code gets the list arr from inside the current selected list for DB.
        // ObjectId todos[] = new ObjectId[3]
        todos = (ArrayList<ObjectId>)CurrList.get("list");
        //TODO: fetch individual todo from todos coll in DB.
        for (int i = 0; i < todos.size(); i++) {
            Document d = new Document().append("_id",todos.get(i));
            System.out.println("### "+todosCollection.find(d).first().get("text"));
        }
        // System.out.println("TODOs fetched form DB: " + todos.get(0));
    }

    public void addItem(String text) {

        // Insert TODOS:
        TodoItem item = new TodoItem();
        ObjectId todoId = item.setItem(text);
        todos.add(todoId);

        // dont insert if todoId is null as todo already exixts
        if (todoId!=null){
            collection.updateOne(new Document().append("_id", ListId),
                new Document().append("$push", new Document().append("list", todoId)));
        }

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
