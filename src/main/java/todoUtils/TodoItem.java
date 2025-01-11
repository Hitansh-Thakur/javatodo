package todoUtils;
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
        return this;
    }
}
