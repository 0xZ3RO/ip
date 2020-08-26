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
        Scanner sc = new Scanner(System.in);
        while (!echo.equals("bye")){
            echo = sc.nextLine();
            System.out.println(hLine + " " + echo + "\n" + hLine);
        }
        sc.close();
        System.out.println(hLine + " Bye! Hope to see you again soon!\n" + hLine);
    }
}
