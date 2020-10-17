/**
 * Arraylist to store all the different tasks
 */

package duke;

import duke.task.Task;

import java.util.ArrayList;

// Manages the memory storage
public class TaskList {
    private static ArrayList<Task> tasks;

    // Constructor to initialize the array
    public TaskList() {
        tasks = new ArrayList<>();
    }

    // Gets the size of the array list
    public int size() {
        return tasks.size();
    }

    // Gets the task from the array list given the index
    public Task get(int selected) {
        return tasks.get(selected);
    }

    // Adds a task to the array list
    public void add(Task task) {
        tasks.add(task);
    }

    // Remove a task from the array list given an index
    public void remove(int selected) {
        tasks.remove(selected);
    }
}
