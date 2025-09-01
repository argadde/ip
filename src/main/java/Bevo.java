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

        Task[] tasks = new Task[100];
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
            } else if (input.toLowerCase().startsWith("mark")) {
                String taskNumber = input.split(" ")[1];
                int index = Integer.parseInt(taskNumber) - 1;
                tasks[index].markAsDone();
                System.out.println("\t_____________________________________________________");
                System.out.println("\t  Nice! I've marked this task as done:");
                System.out.println("\t\t  " + tasks[index]);
                System.out.println("\t_____________________________________________________\n");
            } else if (input.toLowerCase().startsWith("unmark")) {
                String taskNumber = input.split(" ")[1];
                int index = Integer.parseInt(taskNumber) - 1;
                tasks[index].markAsNotDone();
                System.out.println("\t_____________________________________________________");
                System.out.println("\t  OK, I've marked this task as not done yet:");
                System.out.println("\t\t  " + tasks[index]);
                System.out.println("\t_____________________________________________________\n");
            } else {
                tasks[numTasks] = new Task(input);
                numTasks++;
                System.out.println("\t_____________________________________________________");
                System.out.println("\t  added: " + input);
                System.out.println("\t_____________________________________________________\n");
            }
        }

        scanner.close();
    }
}
