package duke;

import duke.exception.DukeException;

import java.util.Scanner;

public class Duke {
    protected static Scanner sc;
    protected static TaskList tasks = new TaskList();

    public static void main(String[] args) {
        Ui.greet();
        sc = new Scanner(System.in);
        try {
            Storage.loadDB();
            Parser.mainTask();
        } catch (DukeException e) {
            Ui.stdout.println(Ui.hLine + e.getMessage() + "\n" + Ui.hLine);
        }
        sc.close();
    }
}
