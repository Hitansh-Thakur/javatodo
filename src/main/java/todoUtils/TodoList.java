package todoUtils;
import java.util.ArrayList;

public class TodoList {
    public static ArrayList<TodoList> Lists= new ArrayList<TodoList>();
    ArrayList<TodoItem> todos = new ArrayList<TodoItem>();
    private int cnt;
    public String ListName;

    public TodoList(String text){
        ListName = text;
        Lists.add(this);
    }
    public void addItem(String text){
        TodoItem item = new TodoItem();
        cnt+=1;
        todos.add(item.setItem(cnt, text));
    }
    public ArrayList<TodoItem> ListTodos(){
        int no = 0;
        System.out.println("\nList Name: " + ListName);

        while (no<cnt) {
            TodoItem item = todos.get(no);
            System.out.println(item.id + ": " + item.text + "\t"+item.StringDateTime);
            no++;
        }
        return todos;
    }

    public static ArrayList<TodoList> getLists(){
        for (TodoList list : Lists) {
            System.out.println(list.ListName);
        }
        return Lists;
    }
}
