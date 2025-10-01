import java.util.Scanner;

public class Bevo {
    /** List of commands available to the user */
    private static final String BYE_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";

    /** Unknown message used when unable to determine times */
    private static final String UNSPECIFIED_MESSAGE = "unspecified";

    /** Horizontal line that is used when creating boxes during printing */
    private static final String HORIZONTAL_LINE = "\t_____________________________________________________";

    public static void main(String[] args) {
        printWelcomeMessage();

        Scanner scanner = new Scanner(System.in);

        Task[] tasks = Storage.load();
        int taskCount = 0;
        for (Task task : tasks) {
            if (task != null) {
                taskCount++;
            }
        }

        while (true) {
            String input = scanner.nextLine();

            if (input.toLowerCase().equals(BYE_COMMAND)) {
                executeByeCommand();
                break;
            } else if (input.toLowerCase().equals(LIST_COMMAND)) {
                executeListCommand(tasks, taskCount);
            } else if (input.toLowerCase().startsWith(MARK_COMMAND)) {
                executeMarkCommand(tasks, input);
                Storage.save(tasks, taskCount);
            } else if (input.toLowerCase().startsWith(UNMARK_COMMAND)) {
                executeUnmarkCommand(tasks, input);
                Storage.save(tasks, taskCount);
            } else if (input.toLowerCase().startsWith(TODO_COMMAND)) {
                taskCount = executeToDoCommand(tasks, taskCount, input);
                Storage.save(tasks, taskCount);
            } else if (input.toLowerCase().startsWith(DEADLINE_COMMAND)) {
                taskCount = executeDeadlineCommand(tasks, taskCount, input);
                Storage.save(tasks, taskCount);
            } else if (input.toLowerCase().startsWith(EVENT_COMMAND)) {
                taskCount = executeEventCommand(tasks, taskCount, input);
                Storage.save(tasks, taskCount);
            } else {
                printError("Bevo does not understand the command.");
            }
        }

        scanner.close();
    }

    /**
     * Executes the event command by creating an
     * Event and setting its description and timeline.
     * Then, it adds the Event to the task list.
     * 
     * @param tasks the array of all tasks
     * @param taskCount the current count of the task list
     * @param input the user's input
     * @return the updated task list's length
     */
    private static int executeEventCommand(Task[] tasks, int taskCount, String input) {
        String[] parts = input.length() > EVENT_COMMAND.length() + 1
                ? input.substring(EVENT_COMMAND.length() + 1).split("/from|/to")
                : new String[]{""};

        String description = parts[0].trim();
        if (description.isEmpty()) {
            printError("Bevo says an event must have a description.");
            return taskCount;
        }

        String from = parts.length > 1 ? parts[1].trim() : UNSPECIFIED_MESSAGE;
        String to = parts.length > 2 ? parts[2].trim() : UNSPECIFIED_MESSAGE;
        tasks[taskCount] = new Event(description, from, to);

        printAddCommand(tasks[taskCount], ++taskCount);
        return taskCount;
    }

    /**
     * Executes the deadline command by creating a
     * Deadline and setting its description and end date.
     * Then, it adds the Deadline to the task list.
     * 
     * @param tasks the array of all tasks
     * @param taskCount the current count of the task list
     * @param input the user's input
     * @return the updated task list's length
     */
    private static int executeDeadlineCommand(Task[] tasks, int taskCount, String input) {
        String[] parts = input.length() > DEADLINE_COMMAND.length() + 1
                ? input.substring(DEADLINE_COMMAND.length() + 1).split("/by", 2)
                : new String[]{""};

        String description = parts[0].trim();
        if (description.isEmpty()) {
            printError("Bevo says a deadline must have a description!");
            return taskCount;
        }

        String by = parts.length > 1 ? parts[1].trim() : UNSPECIFIED_MESSAGE;
        tasks[taskCount] = new Deadline(description, by);

        printAddCommand(tasks[taskCount], ++taskCount);
        return taskCount;
    }

    /**
     * Executes the todo command by creating a
     * ToDo and setting its description.
     * Then, it adds the Deadline to the task list.
     * 
     * @param tasks the array of all tasks
     * @param taskCount the current count of the task list
     * @param input the user's input
     * @return the updated task list's length
     */
    private static int executeToDoCommand(Task[] tasks, int taskCount, String input) {
        String description = input.length() > TODO_COMMAND.length() + 1
                ? input.substring(TODO_COMMAND.length() + 1).trim()
                : "";
        
        if (description.isEmpty()) {
            printError("Bevo says a todo must have a description.");
            return taskCount;
        }
        
        tasks[taskCount] = new Todo(description);

        printAddCommand(tasks[taskCount], ++taskCount);
        return taskCount;
    }

    /**
     * Prints out the adding confirmation to the user.
     * 
     * @param task a newly added Task
     * @param taskCount the current count of the task list
     */
    private static void printAddCommand(Task task, int taskCount) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("\t Got it. I've added this task:");
        System.out.println("\t  " + task);
        System.out.println("\t Now you have " + taskCount + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE + "\n");
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
     * @param taskCount the current count of the task list
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
     * Prints out an error message for invalid inputs.
     * 
     * @param message the error message to be displayed
     */
    private static void printError(String message) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("\t  Oops! " + message);
        System.out.println(HORIZONTAL_LINE + "\n");
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
