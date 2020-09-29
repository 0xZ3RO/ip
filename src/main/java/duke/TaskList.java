/**
 * Arraylist to store all the different tasks
 */

package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int selected) {
        return tasks.get(selected);
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void remove(int selected) {
        tasks.remove(selected);
    }
}
