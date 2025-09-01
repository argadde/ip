import java.util.Scanner;

public class Bevo {
    public static void main(String[] args) {
        String logo = "|||||   -----   \\     /   -----\n"
                + "|    |  |        \\   /   |     |\n"
                + "|||||   ----      \\ /    |     |\n"
                + "|    |  |          |     |     |\n"
                + "|||||   -----      |      ----- \n";
        System.out.println("Hello from\n" + logo);

        System.out.println("\t_____________________________________________________");
        System.out.println("\t  Hello! I'm Bevo.");
        System.out.println("\t  What can I do for you?");
        System.out.println("\t_____________________________________________________\n");

        Scanner scanner = new Scanner(System.in);

        String[] tasks = new String[100];
        int numTasks = 0;

        while (true) {
            String input = scanner.nextLine();

            if (input.toLowerCase().equals("bye")) {
                System.out.println("\t_____________________________________________________");
                System.out.println("\t  Bye. Hope to see you again soon!");
                System.out.println("\t_____________________________________________________");
                break;
            } else if (input.toLowerCase().equals("list")) {
                System.out.println("\t_____________________________________________________");
                for (int i = 0; i < numTasks; i++) {
                    System.out.println("\t  " + (i + 1) + ". " + tasks[i]);
                }
                System.out.println("\t_____________________________________________________\n");
            } else {
                tasks[numTasks] = input;
                numTasks++;
                System.out.println("\t_____________________________________________________");
                System.out.println("\t  added: " + input);
                System.out.println("\t_____________________________________________________\n");
            }
        }

        scanner.close();
    }
}
