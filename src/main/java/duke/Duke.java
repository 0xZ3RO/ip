/**
 * Main class
 * Program starts from here
 */


package duke;

import duke.exception.DukeException;

import java.util.Scanner;

public class Duke {
    protected static Scanner sc;
    protected static TaskList tasks = new TaskList();

    // Main program flow starts from here
    public static void main(String[] args) {
        // Prints the greeting
        Ui.greet();
        // Initialize the scanner
        sc = new Scanner(System.in);
        // Loads the database and runs the main program
        try {
            Storage.loadDB();
            Parser.mainTask();
        } catch (DukeException e) {
            Ui.stdout.println(Ui.hLine + e.getMessage() + "\n" + Ui.hLine);
        }
        // Closes the scanner
        sc.close();
    }
}
