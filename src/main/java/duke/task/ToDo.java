/**
 * Inherit from Task
 */

package duke.task;

public class ToDo extends Task {

    // Constructor for the ToDo class
    public ToDo(String description) {
        super(description);
    }

    // Override printing of Task class
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
