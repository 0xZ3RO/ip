package duke;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Storage {
    private static java.nio.file.Path filePath;

    static void loadDB() throws DukeException {
        String home = System.getProperty("user.home");
        filePath = Paths.get(home, "Desktop", "DB.txt");
        boolean directoryExists = Files.exists(filePath);
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
                    Duke.tasks.add(new ToDo(subSec[2]));
                    break;
                case "E":
                    Duke.tasks.add(new Event(subSec[2], subSec[3]));
                    break;
                case "D":
                    Duke.tasks.add(new Deadline(subSec[2], subSec[3]));
                    break;
                }
                if (subSec[1].equals("1")) {
                    Duke.tasks.get(Duke.tasks.size() - 1).markAsDone();
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

    static void updateDB() throws DukeException {
        try {
            FileWriter fw = new FileWriter(String.valueOf(filePath));
            for (int i = 0; i < Duke.tasks.size(); i++) {
                Task task = Duke.tasks.get(i);
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
}
