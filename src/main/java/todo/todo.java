package todo;

import java.util.Scanner;

import todoUtils.*;

public class todo {
    public static void main(String[] args) {
        // TodoList todo;
        // Scanner sc = new Scanner(System.in);

        // try{

        //     while (true) {
    
        //         System.out.println("Welcome to the TodoList\n Created Lists:\n" + TodoList.getLists());
        //         System.out.println("Menu:1. Create a new List\n2. Select a List to Edit \n3. Delete a List \n4. Exit");
        //         int choice = sc.nextInt();
        //         switch (choice) {
        //             case 1:
        //                 System.out.println("Enter Name of the List: ");
        //                 todo = new TodoList(sc.next());
        //                 break;
        //             case 2:
                        
        //                 System.out.println("enter Number of list to Use:");
    
        //         }
        //     }
        // }catch(Exception e){
        //     System.out.println("Some Error Occured!" + e);

        // }finally{
        //     sc.close();
        // }


        TodoList todo = new TodoList("Shopping List");
        todo.addItem("Pens");
        todo.addItem("Drawing Book");
        todo.addItem("Geometry Box");
        todo.printList();
        // todo.ListTodos();

        TodoList todo1 = new TodoList("Tasks");
        todo1.addItem("Study");
        todo1.addItem("Water the plants");
        todo1.addItem("Work on Project");
        todo1.printList();

        // todo1.ListTodos();

        System.out.println("\nTotal Lists created: ");
        TodoList.getALlLists();
        TodoList.getList(2);
    }
}
