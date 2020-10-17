/**
 * Inherit from Task
 */

package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected String dueDate;
    protected LocalDate date;

    // Constructor for deadline class
    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
        date = LocalDate.parse(dueDate.split(" ")[0]);
    }

    // Get the due date for deadline
    public String getDueDate() {
        return dueDate;
    }

    // Override printing of Task class
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy "))
                + dueDate.split(" ")[1] + ")";
    }
}
