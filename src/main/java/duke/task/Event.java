/**
 * Inherit from Task
 */

package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected String timing;
    protected LocalDate date;

    public Event(String description, String timing) {
        super(description);
        this.timing = timing;
        date = LocalDate.parse(timing.split(" ")[0]);
    }

    public String getTiming() {
        return timing;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy "))
                + timing.split(" ")[1] + ")";
    }
}
