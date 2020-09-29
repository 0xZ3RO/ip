package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected String at;
    protected LocalDate date;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        date = LocalDate.parse(at.split(" ")[0]);
    }

    public String getAt() {
        return at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy "))
                + at.split(" ")[1] + ")";
    }
}
