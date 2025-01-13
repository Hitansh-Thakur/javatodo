package todo;

import todoUtils.*;

public class todo {
    public static void main(String[] args) {

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

