package duke;

import duke.task.*;
import duke.exception.DukeException;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {
    private static final String hLine = "──────────────────────────── ⋆⋅☆⋅⋆ ────────────────────────────\n";
    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static Scanner sc;
    private static java.nio.file.Path filePath;
    private static final ArrayList<Task> tasks = new ArrayList<>();

    private static void greet() {
        System.out.println(hLine + logo + " Hello! I'm Duke\n What can I do for you?\n" + hLine);
    }

    private static void loadDB() throws DukeException {
        String home = System.getProperty("user.home");
        filePath = java.nio.file.Paths.get(home, "Desktop", "DB.txt");
        boolean directoryExists = java.nio.file.Files.exists(filePath);
        if (directoryExists) {
            File f = new File(String.valueOf(filePath));
            Scanner s;
            try {
                s = new Scanner(f);
            } catch (FileNotFoundException e) {
                throw new DukeException(" ☹ OOPS!!! Unable to find database.");
            }
            while (s.hasNext()) {
                String taskToProcess = s.nextLine();
                String[] subSec = taskToProcess.split(" \\| ");
                switch (subSec[0]) {
                case "T":
                    tasks.add(new ToDo(subSec[2]));
                    break;
                case "E":
                    tasks.add(new Event(subSec[2], subSec[3]));
                    break;
                case "D":
                    tasks.add(new Deadline(subSec[2], subSec[3]));
                    break;
                }
                if (subSec[1].equals("1")) {
                    tasks.get(tasks.size() - 1).markAsDone();
                }
            }
        } else {
            File yourFile = new File(String.valueOf(filePath));
            try {
                boolean success = yourFile.createNewFile();
                if (!success) {
                    throw new DukeException(" ☹ OOPS!!! Unable to create database.");
                }
            } catch (IOException e) {
                throw new DukeException(" ☹ OOPS!!! Unable to create database.");
            }
        }
    }

    private static void updateDB() throws DukeException {
        try {
            FileWriter fw = new FileWriter(String.valueOf(filePath));
            for (Task task : tasks) {
                String toWrite = "";
                if (task instanceof ToDo) {
                    toWrite += "T | ";
                    toWrite += (task.getDone() ? "1" : "0") + " | ";
                    toWrite += task.getDescription();
                } else if (task instanceof Event) {
                    toWrite += "E | ";
                    toWrite += (task.getDone() ? "1" : "0") + " | ";
                    toWrite += task.getDescription() + " | ";
                    toWrite += ((Event) task).getAt();
                } else if (task instanceof Deadline) {
                    toWrite += "D | ";
                    toWrite += (task.getDone() ? "1" : "0") + " | ";
                    toWrite += task.getDescription() + " | ";
                    toWrite += ((Deadline) task).getBy();
                }
                fw.write(toWrite + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException(" ☹ OOPS!!! Unable to create database.");
        }
    }

    private static void mainTask() {
        String echo;
        String command = "";
        while (!command.equalsIgnoreCase("bye")) {
            echo = sc.nextLine().trim();
            command = echo.split("\\s+")[0];
            if (command.equalsIgnoreCase("list")) {
                System.out.println(hLine + " Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(" " + (i + 1) + "." + tasks.get(i));
                }
                System.out.println(hLine);
            } else if (command.equalsIgnoreCase("done")) {
                try {
                    if (echo.length() < 6) {
                        throw new DukeException(" ☹ OOPS!!! Please specify the task to mark as done.");
                    }
                    int selected = Integer.parseInt(echo.substring(5)) - 1;
                    tasks.get(selected).markAsDone();
                    System.out.println(hLine + " Nice! I've marked this task as done: ");
                    System.out.println("   " + tasks.get(selected) + "\n" + hLine);
                    updateDB();
                } catch (DukeException e) {
                    System.out.println(hLine + e.getMessage() + "\n" + hLine);
                } catch (NullPointerException | IndexOutOfBoundsException e) {
                    System.out.println(hLine + " ☹ OOPS!!! Please specify a valid task to mark as done"
                            + "\n" + hLine);
                }
            } else if (command.equalsIgnoreCase("todo")) {
                try {
                    if (echo.length() < 6) {
                        throw new DukeException(" ☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    tasks.add(new ToDo(echo.substring(5)));
                    System.out.println(hLine + " Got it. I've added this task:");
                    System.out.println("   " + tasks.get(tasks.size() - 1));
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.\n" + hLine);
                    updateDB();
                } catch (DukeException e) {
                    System.out.println(hLine + e.getMessage() + "\n" + hLine);
                }
            } else if (command.equalsIgnoreCase("deadline")) {
                try {
                    if (echo.length() < 10) {
                        throw new DukeException(" ☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    String[] deadL = echo.substring(9).split(" /by ", 2);
                    tasks.add(new Deadline(deadL[0], deadL[1]));
                    System.out.println(hLine + " Got it. I've added this task:");
                    System.out.println("   " + tasks.get(tasks.size() - 1));
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.\n" + hLine);
                    updateDB();
                } catch (DukeException e) {
                    System.out.println(hLine + e.getMessage() + "\n" + hLine);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(hLine + " ☹ OOPS!!! Please use /by to specify date/time."
                            + "\n" + hLine);
                }
            } else if (command.equalsIgnoreCase("event")) {
                try {
                    if (echo.length() < 7) {
                        throw new DukeException(" ☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    String[] event = echo.substring(6).split(" /at ", 2);
                    tasks.add(new Event(event[0], event[1]));
                    System.out.println(hLine + " Got it. I've added this task:");
                    System.out.println("   " + tasks.get(tasks.size() - 1));
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.\n" + hLine);
                    updateDB();
                } catch (DukeException e) {
                    System.out.println(hLine + e.getMessage() + "\n" + hLine);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(hLine + " ☹ OOPS!!! Please use /at to specify start/end time."
                            + "\n" + hLine);
                }
            } else if (command.equalsIgnoreCase("delete")) {
                try {
                    if (echo.length() < 8) {
                        throw new DukeException(" ☹ OOPS!!! Please specify the task to delete.");
                    }
                    int selected = Integer.parseInt(echo.substring(7)) - 1;
                    Task removed = tasks.get(selected);
                    tasks.remove(selected);
                    System.out.println(hLine + " Noted! I've removed this task: ");
                    System.out.println("   " + removed);
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.\n" + hLine);
                    updateDB();
                } catch (DukeException e) {
                    System.out.println(hLine + e.getMessage() + "\n" + hLine);
                } catch (NullPointerException | IndexOutOfBoundsException e) {
                    System.out.println(hLine + " ☹ OOPS!!! Please specify a valid task to delete"
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
            loadDB();
            mainTask();
        } catch (DukeException e) {
            System.out.println(hLine + e.getMessage() + "\n" + hLine);
        }
        sc.close();
    }
}
