import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String hLine = "____________________________________________________________\n";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(hLine + " Hello! I'm Duke\n What can I do for you?\n" + hLine);
        String echo = "";
        Task[] list = new Task[100];
        int listSize = 0;
        Scanner sc = new Scanner(System.in);
        while (!echo.equals("bye")){
            echo = sc.nextLine();
            if (echo.equals("list")){
                System.out.println(hLine + " Here are the tasks in your list:");
                for (int i = 0; i < listSize; i++){
                    System.out.println(" " + (i+1) + ".["+ list[i].getStatusIcon() + "] " + list[i].description);
                }
                System.out.println(hLine);
            } else if (echo.length() > 5 && echo.substring(0,4).equals("done")) {
                list[Integer.valueOf(echo.substring(5))-1].setDone();
                System.out.println(hLine + " Nice! I've marked this task as done: ");
                System.out.println("   [\u2713] " + list[Integer.valueOf(echo.substring(5))-1].description
                        + "\n" + hLine);
            } else if (!echo.equals("bye")){
                    list[listSize] = new Task(echo);
                    listSize++;
                    System.out.println(hLine + " added: " + echo + "\n" + hLine);
            }
        }
        sc.close();
        System.out.println(hLine + " Bye! Hope to see you again soon!\n" + hLine);
    }
}
