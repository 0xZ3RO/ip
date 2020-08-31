import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String hLine = "____________________________________________________________\n";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(hLine + logo + " Hello! I'm Duke\n What can I do for you?\n" + hLine);
        String echo = "";
        Task[] tasks = new Task[100];
        int listSize = 0;
        Scanner sc = new Scanner(System.in);
        while (!echo.equals("bye")) {
            echo = sc.nextLine().trim();
            if (echo.equals("list")) {
                System.out.println(hLine + " Here are the tasks in your tasks:");
                for (int i = 0; i < listSize; i++) {
                    System.out.println(" " + (i + 1) + "." + tasks[i]);
                }
                System.out.println(hLine);
            } else if (echo.length() > 5 && echo.substring(0, 4).equals("done")) {
                int selected = Integer.valueOf(echo.substring(5)) - 1;
                tasks[selected].markAsDone();
                System.out.println(hLine + " Nice! I've marked this task as done: ");
                System.out.println("   " + tasks[selected] + "\n" + hLine);
            } else if (!echo.equals("bye")) {
                String tmp = echo.split("\\s+")[0];
                System.out.println(hLine + " Got it. I've added this task:");
                if (tmp.equals("todo")) {
                    tasks[listSize] = new ToDos(echo.substring(5));
                    System.out.println("   " + tasks[listSize]);
                } else if (tmp.equals("deadline")) {
                    tmp = echo.substring(9);
                    String[] tmp2 = tmp.split(" /by ");
                    tasks[listSize] = new Deadlines(tmp2[0], tmp2[1]);
                    System.out.println("   " + tasks[listSize]);
                } else if (tmp.equals("event")) {
                    tmp = echo.substring(6);
                    String[] tmp2 = tmp.split(" /at ");
                    tasks[listSize] = new Events(tmp2[0], tmp2[1]);
                    System.out.println("   " + tasks[listSize]);
                } else {
                    tasks[listSize] = new Task(echo);
                    System.out.println("   " + echo);
                }
                listSize++;
                System.out.println(" Now you have " + listSize + " tasks in the list.\n" + hLine);
            }
        }
        sc.close();
        System.out.println(hLine + " Bye! Hope to see you again soon!\n" + hLine);
    }
}
