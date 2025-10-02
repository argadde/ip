import java.util.Scanner;

public class Ui {
    /** Horizontal line that is used when creating boxes during printing */
    private static final String HORIZONTAL_LINE = "\t_____________________________________________________";
    
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Prints out a welcome message with the chatbot's
     * name and prompts the user for input.
     */
    public void printWelcomeMessage() {
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

    public String read() {
        return scanner.nextLine();
    }

    /**
     * Prints out an error message for invalid inputs.
     * 
     * @param message the error message to be displayed
     */
    public void printError(String message) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("\t  Oops! " + message);
        System.out.println(HORIZONTAL_LINE + "\n");
    }

    /**
     * Prints out the adding confirmation to the user.
     * 
     * @param task a newly added Task
     * @param taskCount the current count of the task list
     */
    public void printAddCommand(Task task, int taskCount) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("\t Got it. I've added this task:");
        System.out.println("\t  " + task);
        System.out.println("\t Now you have " + taskCount + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE + "\n");
    }

    public void printMarkCommand(Task task) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("\t  Nice! I've marked this task as done:");
        System.out.println("\t\t  " + task);
        System.out.println(HORIZONTAL_LINE + "\n");
    }

    public void printUnmarkCommand(Task task) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("\t  OK, I've marked this task as not done yet:");
        System.out.println("\t\t  " + task);
        System.out.println(HORIZONTAL_LINE + "\n");
    }

    /**
     * Executes the bye command by printing out a goodbye
     * message.
     */
    public void printByeCommand() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("\t  Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints out the delete message to the user for the 
     * removed task.
     * 
     * @param taskCount the current count of the task list
     * @param removedTask the removed task from the original task list
     */
    public void printDeleteCommand(int taskCount, Task removedTask) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("\t Bevo has removed this task:");
        System.out.println("\t\t  " + removedTask);
        System.out.println("\t Now you have " + taskCount + " remaining tasks in the list.");
        System.out.println(HORIZONTAL_LINE + "\n");
    }

    /**
     * Executes the list command by listing all the tasks and
     * their statuses.
     * 
     * @param tasks the array of all tasks
     * @param taskCount the current count of the task list
     */
    public void printListCommand(TaskList tasks) {
        System.out.println(HORIZONTAL_LINE);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t  " + (i + 1) + ". " + tasks.get(i));
        }
        System.out.println(HORIZONTAL_LINE + "\n");
    }

    public void printFindCommand(TaskList matches) {
        System.out.println(HORIZONTAL_LINE);
        if (matches.size() == 0) {
            System.out.println("\t  Bevo did not find any matching tasks.");
        } else {
            for (int i = 0; i < matches.size(); i++) {
                System.out.println("\t  " + (i + 1) + ". " + matches.get(i));
            }
        }
        System.out.println(HORIZONTAL_LINE + "\n");
    }
}
