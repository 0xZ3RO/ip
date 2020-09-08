import java.util.Scanner;

public class Duke {
    private static final String hLine = "____________________________________________________________\n";
    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final int MAX = 100;
    private static Scanner sc;

    public static void greet() {
        System.out.println(hLine + logo + " Hello! I'm Duke\n What can I do for you?\n" + hLine);
    }

    public static void mainTask() throws DukeException {
        String echo;
        String command = "";
        Task[] tasks = new Task[MAX];
        int listSize = 0;
        while (!command.equalsIgnoreCase("bye")) {
            echo = sc.nextLine().trim();
            command = echo.split("\\s+")[0];
            if (command.equalsIgnoreCase("list")) {
                System.out.println(hLine + " Here are the tasks in your tasks:");
                for (int i = 0; i < listSize; i++) {
                    System.out.println(" " + (i + 1) + "." + tasks[i]);
                }
                System.out.println(hLine);
            } else if (command.equalsIgnoreCase("done")) {
                try {
                    if (echo.length() < 6) {
                        throw new DukeException(" ☹ OOPS!!! Please specify the task to mark as done.");
                    }
                    int selected = Integer.parseInt(echo.substring(5)) - 1;
                    tasks[selected].markAsDone();
                    System.out.println(hLine + " Nice! I've marked this task as done: ");
                    System.out.println("   " + tasks[selected] + "\n" + hLine);
                } catch (DukeException e) {
                    System.out.println(hLine + e.getMessage() + "\n" + hLine);
                } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
                    System.out.println(hLine + " ☹ OOPS!!! Please specify a valid task to mark as done"
                            + "\n" + hLine);
                }
            } else if (command.equalsIgnoreCase("todo")) {
                try {
                    if (echo.length() < 6) {
                        throw new DukeException(" ☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    tasks[listSize] = new ToDo(echo.substring(5));
                    System.out.println(hLine + " Got it. I've added this task:");
                    System.out.println("   " + tasks[listSize]);
                    listSize++;
                    System.out.println(" Now you have " + listSize + " tasks in the list.\n" + hLine);
                } catch (DukeException e) {
                    System.out.println(hLine + e.getMessage() + "\n" + hLine);
                }
            } else if (command.equalsIgnoreCase("deadline")) {
                try {
                    if (echo.length() < 10) {
                        throw new DukeException(" ☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    String[] deadL = echo.substring(9).split(" /by ", 2);
                    tasks[listSize] = new Deadline(deadL[0], deadL[1]);
                    System.out.println(hLine + " Got it. I've added this task:");
                    System.out.println("   " + tasks[listSize]);
                    listSize++;
                    System.out.println(" Now you have " + listSize + " tasks in the list.\n" + hLine);
                } catch (DukeException e) {
                    System.out.println(hLine + e.getMessage() + "\n" + hLine);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(hLine + " ☹ OOPS!!! Please use /by to specify date/time."
                            + "\n" + hLine);
                }
            } else if (command.equalsIgnoreCase("event")) {
                try {
                    if (echo.length() < 7) {
                        throw new DukeException(" ☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    String[] event = echo.substring(6).split(" /at ", 2);
                    tasks[listSize] = new Event(event[0], event[1]);
                    System.out.println(hLine + " Got it. I've added this task:");
                    System.out.println("   " + tasks[listSize]);
                    listSize++;
                    System.out.println(" Now you have " + listSize + " tasks in the list.\n" + hLine);
                } catch (DukeException e) {
                    System.out.println(hLine + e.getMessage() + "\n" + hLine);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(hLine + " ☹ OOPS!!! Please use /at to specify start/end time."
                            + "\n" + hLine);
                }
            } else if (!command.equalsIgnoreCase("bye")) {
                try {
                    throw new DukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                } catch (DukeException e) {
                    System.out.println(hLine + e.getMessage() + "\n" + hLine);
                }
            }
        }
        System.out.println(hLine + " Bye! Hope to see you again soon!\n" + hLine);
    }

    public static void main(String[] args) {
        greet();
        sc = new Scanner(System.in);
        try {
            mainTask();
        } catch (DukeException e) {
            System.out.println(hLine + e.getMessage() + "\n" + hLine);
        }
        sc.close();
    }
}
