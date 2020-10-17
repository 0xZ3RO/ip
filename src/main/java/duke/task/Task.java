/**
 * Task class to inherit from
 */

package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    // Constructor for Task
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    // Get the icon based on the status
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    // Mark the task as done
    public void markAsDone() {
        this.isDone = true;
    }

    // Get the description of task
    public String getDescription() {
        return description;
    }

    // Get the completion status of task
    public boolean getDone() {
        return isDone;
    }

    // Override default printing
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
