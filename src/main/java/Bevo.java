import java.util.Scanner;

public class Bevo {
    /** Maximum number of tasks that can be created */
    private static final int MAX_TASKS = 100;

    /** Horizontal line that is used when creating boxes during printing */
    private static final String HORIZONTAL_LINE = "\t_____________________________________________________";

    public static void main(String[] args) {
        printWelcomeMessage();

        Scanner scanner = new Scanner(System.in);

        Task[] tasks = new Task[MAX_TASKS];
        int taskCount = 0;

        while (true) {
            String input = scanner.nextLine();

            if (input.toLowerCase().equals("bye")) {
                executeByeCommand();
                break;
            } else if (input.toLowerCase().equals("list")) {
                executeListCommand(tasks, taskCount);
            } else if (input.toLowerCase().startsWith("mark")) {
                executeMarkCommand(tasks, input);
            } else if (input.toLowerCase().startsWith("unmark")) {
                executeUnmarkCommand(tasks, input);
            } else {
                taskCount = executeAddCommand(tasks, taskCount, input);
            }
        }

        scanner.close();
    }

    /**
     * Executes the add command by automatically adding tasks
     * as the user inputs them.
     * 
     * @param tasks the array of all tasks
     * @param taskCount the current count of all tasks
     * @param input the user's input
     * @return the new count of all tasks
     */
    private static int executeAddCommand(Task[] tasks, int taskCount, String input) {
        tasks[taskCount] = new Task(input);
        taskCount++;
        System.out.println(HORIZONTAL_LINE);
        System.out.println("\t  added: " + input);
        System.out.println(HORIZONTAL_LINE + "\n");
        return taskCount;
    }

    /**
     * Executes the unmark command by setting the user-requested
     * task to incomplete.
     * 
     * @param tasks the array of all tasks
     * @param input the user's input
     */
    private static void executeUnmarkCommand(Task[] tasks, String input) {
        String taskNumber = input.split(" ")[1];
        int index = Integer.parseInt(taskNumber) - 1;
        tasks[index].markAsNotDone();
        System.out.println(HORIZONTAL_LINE);
        System.out.println("\t  OK, I've marked this task as not done yet:");
        System.out.println("\t\t  " + tasks[index]);
        System.out.println(HORIZONTAL_LINE + "\n");
    }

    /**
     * Executes the mark command by setting the user-requested
     * task to complete.
     * 
     * @param tasks the array of all tasks
     * @param input the user's input
     */
    private static void executeMarkCommand(Task[] tasks, String input) {
        String taskNumber = input.split(" ")[1];
        int index = Integer.parseInt(taskNumber) - 1;
        tasks[index].markAsDone();
        System.out.println(HORIZONTAL_LINE);
        System.out.println("\t  Nice! I've marked this task as done:");
        System.out.println("\t\t  " + tasks[index]);
        System.out.println(HORIZONTAL_LINE + "\n");
    }

    /**
     * Executes the list command by listing all the tasks and
     * their statuses.
     * 
     * @param tasks the array of all tasks
     * @param taskCount the current count of all tasks
     */
    private static void executeListCommand(Task[] tasks, int taskCount) {
        System.out.println(HORIZONTAL_LINE);
        for (int i = 0; i < taskCount; i++) {
            System.out.println("\t  " + (i + 1) + ". " + tasks[i]);
        }
        System.out.println(HORIZONTAL_LINE + "\n");
    }

    /**
     * Executes the bye command by printing out a goodbye
     * message.
     */
    private static void executeByeCommand() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("\t  Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints out a welcome message with the chatbot's
     * name and prompts the user for input.
     */
    private static void printWelcomeMessage() {
        String logo = "|||||   -----   \\     /   -----\n"
                + "|    |  |        \\   /   |     |\n"
                + "|||||   ----      \\ /    |     |\n"
                + "|    |  |          |     |     |\n"
                + "|||||   -----      |      ----- \n";
        System.out.println("Hello from\n" + logo);

        System.out.println(HORIZONTAL_LINE);
        System.out.println("\t  Hello! I'm Bevo.");
        System.out.println("\t  What can I do for you?");
        System.out.println(HORIZONTAL_LINE + "\n");
    }
}
