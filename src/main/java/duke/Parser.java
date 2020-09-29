package duke;

public class Parser {
    static void mainTask() {
        String echo;
        String command = "";
        while (!command.equalsIgnoreCase("bye")) {
            echo = Duke.sc.nextLine().trim();
            command = echo.split("\\s+")[0];
            if (command.equalsIgnoreCase("list")) {
                Command.handleList();
            } else if (command.equalsIgnoreCase("done")) {
                Command.handleDone(echo);
            } else if (command.equalsIgnoreCase("todo")) {
                Command.handleTodo(echo);
            } else if (command.equalsIgnoreCase("deadline")) {
                Command.handleDL(echo);
            } else if (command.equalsIgnoreCase("event")) {
                Command.handleEvent(echo);
            } else if (command.equalsIgnoreCase("delete")) {
                Command.handleDelete(echo);
            } else if (command.equalsIgnoreCase("find")) {
                Command.handleFind(echo);
            } else if (!command.equalsIgnoreCase("bye")) {
                Command.handleUnknown();
            }
        }
        Ui.stdout.println(Ui.hLine + " Bye! Hope to see you again soon!\n" + Ui.hLine);
    }
}
