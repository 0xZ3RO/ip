package duke;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.time.format.DateTimeParseException;

public class Command {
    protected static void handleList() {
        Ui.stdout.println(Ui.hLine + " Here are the tasks in your list:");
        for (int i = 0; i < Duke.tasks.size(); i++) {
            Ui.stdout.println(" " + (i + 1) + "." + Duke.tasks.get(i));
        }
        Ui.stdout.println(Ui.hLine);
    }

    protected static void handleDone(String echo) {
        try {
            if (echo.length() < 6) {
                throw new DukeException(" ☹ OOPS!!! Please specify the task to mark as done.");
            }
            int selected = Integer.parseInt(echo.substring(5)) - 1;
            Duke.tasks.get(selected).markAsDone();
            Ui.stdout.println(Ui.hLine + " Nice! I've marked this task as done: ");
            Ui.stdout.println("   " + Duke.tasks.get(selected) + "\n" + Ui.hLine);
            Storage.updateDB();
        } catch (DukeException e) {
            Ui.stdout.println(Ui.hLine + e.getMessage() + "\n" + Ui.hLine);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            Ui.stdout.println(Ui.hLine + " ☹ OOPS!!! Please specify a valid task to mark as done"
                    + "\n" + Ui.hLine);
        }
    }

    protected static void handleTodo(String echo) {
        try {
            if (echo.length() < 6) {
                throw new DukeException(" ☹ OOPS!!! The description of a todo cannot be empty.");
            }
            Duke.tasks.add(new ToDo(echo.substring(5)));
            Ui.stdout.println(Ui.hLine + " Got it. I've added this task:");
            Ui.stdout.println("   " + Duke.tasks.get(Duke.tasks.size() - 1));
            Ui.stdout.println(" Now you have " + Duke.tasks.size() + " tasks in the list.\n" + Ui.hLine);
            Storage.updateDB();
        } catch (DukeException e) {
            Ui.stdout.println(Ui.hLine + e.getMessage() + "\n" + Ui.hLine);
        }
    }

    protected static void handleDL(String echo) {
        try {
            if (echo.length() < 10) {
                throw new DukeException(" ☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            String[] deadL = echo.substring(9).split(" /by ", 2);
            Duke.tasks.add(new Deadline(deadL[0], deadL[1]));
            Ui.stdout.println(Ui.hLine + " Got it. I've added this task:");
            Ui.stdout.println("   " + Duke.tasks.get(Duke.tasks.size() - 1));
            Ui.stdout.println(" Now you have " + Duke.tasks.size() + " tasks in the list.\n" + Ui.hLine);
            Storage.updateDB();
        } catch (DukeException e) {
            Ui.stdout.println(Ui.hLine + e.getMessage() + "\n" + Ui.hLine);
        } catch (IndexOutOfBoundsException e) {
            Ui.stdout.println(Ui.hLine + " ☹ OOPS!!! Please use /by to specify date/time."
                    + "\n" + Ui.hLine);
        } catch (DateTimeParseException e) {
            Ui.stdout.println(Ui.hLine + " ☹ OOPS!!! Please use the correct date format."
                    + "\n" + Ui.hLine);
        }
    }

    protected static void handleEvent(String echo) {
        try {
            if (echo.length() < 7) {
                throw new DukeException(" ☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            String[] event = echo.substring(6).split(" /at ", 2);
            Duke.tasks.add(new Event(event[0], event[1]));
            Ui.stdout.println(Ui.hLine + " Got it. I've added this task:");
            Ui.stdout.println("   " + Duke.tasks.get(Duke.tasks.size() - 1));
            Ui.stdout.println(" Now you have " + Duke.tasks.size() + " tasks in the list.\n" + Ui.hLine);
            Storage.updateDB();
        } catch (DukeException e) {
            Ui.stdout.println(Ui.hLine + e.getMessage() + "\n" + Ui.hLine);
        } catch (IndexOutOfBoundsException e) {
            Ui.stdout.println(Ui.hLine + " ☹ OOPS!!! Please use /at to specify start/end time."
                    + "\n" + Ui.hLine);
        } catch (DateTimeParseException e) {
            Ui.stdout.println(Ui.hLine + " ☹ OOPS!!! Please use the correct date format."
                    + "\n" + Ui.hLine);
        }
    }

    protected static void handleUnknown() {
        try {
            throw new DukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        } catch (DukeException e) {
            Ui.stdout.println(Ui.hLine + e.getMessage() + "\n" + Ui.hLine);
        }
    }

    protected static void handleDelete(String echo) {
        try {
            if (echo.length() < 8) {
                throw new DukeException(" ☹ OOPS!!! Please specify the task to delete.");
            }
            int selected = Integer.parseInt(echo.substring(7)) - 1;
            Task removed = Duke.tasks.get(selected);
            Duke.tasks.remove(selected);
            Ui.stdout.println(Ui.hLine + " Noted! I've removed this task: ");
            Ui.stdout.println("   " + removed);
            Ui.stdout.println(" Now you have " + Duke.tasks.size() + " tasks in the list.\n" + Ui.hLine);
            Storage.updateDB();
        } catch (DukeException e) {
            Ui.stdout.println(Ui.hLine + e.getMessage() + "\n" + Ui.hLine);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            Ui.stdout.println(Ui.hLine + " ☹ OOPS!!! Please specify a valid task to delete"
                    + "\n" + Ui.hLine);
        }
    }

    public static void handleFind(String echo) {
        try {
            if (echo.length() < 6) {
                throw new DukeException(" ☹ OOPS!!! Please specify the task to find.");
            }
            String keyword = echo.substring(5);
            Ui.stdout.println(Ui.hLine + " Here are the matching tasks in your list:");
            for (int i =0; i< Duke.tasks.size(); i++){
                if(Duke.tasks.get(i).getDescription().contains(keyword)){
                    Ui.stdout.println(Duke.tasks.get(i));
                }
            }
            Ui.stdout.println(Ui.hLine);
        } catch (DukeException e) {
            Ui.stdout.println(Ui.hLine + e.getMessage() + "\n" + Ui.hLine);
        }
    }
}
