package duke;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class Ui {
    static final PrintWriter stdout = new PrintWriter(
            new OutputStreamWriter(System.out, StandardCharsets.UTF_8), true);
    static final String hLine = "──────────────────────────── ⋆⋅☆⋅⋆ ────────────────────────────\n";
    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    static void greet() {
        stdout.println(hLine + logo + " Hello! I'm Duke\n What can I do for you?\n" + hLine);
    }
}
