public class Parser {
    /** List of commands available to the user */
    private static final String BYE_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String DELETE_COMMAND = "delete";
    private static final String FIND_COMMAND = "find";

    /** Unknown message used when unable to determine times */
    private static final String UNSPECIFIED_MESSAGE = "unspecified";
    
    /**
     * Parses the user-specified command and executes it accordingly.
     * 
     * @param input the user's input
     * @param tasks the list of all tasks
     * @param ui the object that communicates with the user
     * @param storage the save file to be updated
     * @return
     */
    public static boolean parse(String input, TaskList tasks, Ui ui, Storage storage) {
        if (input.toLowerCase().equals(BYE_COMMAND)) {
            ui.printByeCommand();
            return true;
        } else if (input.toLowerCase().equals(LIST_COMMAND)) {
            ui.printListCommand(tasks);
        } else if (input.toLowerCase().startsWith(MARK_COMMAND)) {
            executeMarkCommand(tasks, input, ui, storage);
        } else if (input.toLowerCase().startsWith(UNMARK_COMMAND)) {
            executeUnmarkCommand(tasks, input, ui, storage);
        } else if (input.toLowerCase().startsWith(TODO_COMMAND)) {
            executeTodoCommand(tasks, input, ui, storage);
        } else if (input.toLowerCase().startsWith(DEADLINE_COMMAND)) {
            executeDeadlineCommand(tasks, input, ui, storage);
        } else if (input.toLowerCase().startsWith(EVENT_COMMAND)) {
            executeEventCommand(tasks, input, ui, storage);
        } else if (input.toLowerCase().startsWith(DELETE_COMMAND)) {
            executeDeleteCommand(tasks, input, ui, storage);
        } else if (input.startsWith(FIND_COMMAND)) {
            executeFindCommand(tasks, input, ui);
        } else {
            ui.printError("Bevo does not understand the command.");
        }

        return false;
    }

    /**
     * Converts each line in the save file to a task.
     * 
     * @param line each task listed in the save file
     * @return the corresponding task type from the save file
     */
    public static Task parseTaskFromSaveFile(String line) {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task;
        switch (type) {
        case "T":
            task = new Todo(description);
            break;
        case "D":
            task = new Deadline(description, parts[3]);
            break;
        case "E":
            task = new Event(description, parts[3], parts[4]);
            break;
        default:
            return null;
        }

        if (isDone) {
            task.markAsDone();
        }

        return task;
    }

    /**
     * Executes the delete command by removing the
     * desired task from the task list.
     * 
     * @param tasks the list of all tasks
     * @param input the user's input
     * @param ui the object that communicates with the user
     * @param storage the save file to be updated
     */
    private static void executeDeleteCommand(TaskList tasks, String input, Ui ui, Storage storage) {
        String[] parts = input.split(" ");
        if (parts.length < 2) {
            ui.printError("Bevo says that you need to specify a task number to delete.");
            return;
        }

        int index = Integer.parseInt(parts[1]) - 1;

        if (index < 0 || index >= tasks.size()) {
            ui.printError("Bevo says that task number does not exist.");
            return;
        }

        Task removedTask = tasks.remove(index);
        ui.printDeleteCommand(tasks.size(), removedTask);
        storage.save(tasks.getAll());
    }

    /**
     * Executes the event command by creating an
     * Event and setting its description and timeline.
     * Then, it adds the Event to the task list.
     * 
     * @param tasks the list of all tasks
     * @param input the user's input
     * @param ui the object that communicates with the user
     * @param storage the save file to be updated
     */
    private static void executeEventCommand(TaskList tasks, String input, Ui ui, Storage storage) {
        String[] parts = input.length() > EVENT_COMMAND.length() + 1
                ? input.substring(EVENT_COMMAND.length() + 1).split("/from|/to")
                : new String[]{""};

        String description = parts[0].trim();
        if (description.isEmpty()) {
            ui.printError("Bevo says an event must have a description.");
            return;
        }

        String from = parts.length > 1 ? parts[1].trim() : UNSPECIFIED_MESSAGE;
        String to = parts.length > 2 ? parts[2].trim() : UNSPECIFIED_MESSAGE;
        Task event = new Event(description, from, to);

        ui.printAddCommand(event, tasks.size());
        storage.save(tasks.getAll());
    }

    /**
     * Executes the deadline command by creating a
     * Deadline and setting its description and end date.
     * Then, it adds the Deadline to the task list.
     * 
     * @param tasks the list of all tasks
     * @param input the user's input
     * @param ui the object that communicates with the user
     * @param storage the save file to be updated
     */
    private static void executeDeadlineCommand(TaskList tasks, String input, Ui ui, Storage storage) {
        String[] parts = input.length() > DEADLINE_COMMAND.length() + 1
                ? input.substring(DEADLINE_COMMAND.length() + 1).split("/by", 2)
                : new String[]{""};

        String description = parts[0].trim();
        if (description.isEmpty()) {
            ui.printError("Bevo says a deadline must have a description!");
            return;
        }

        String by = parts.length > 1 ? parts[1].trim() : UNSPECIFIED_MESSAGE;
        Task deadline = new Deadline(description, by);
        tasks.add(deadline);
        ui.printAddCommand(deadline, tasks.size());
        storage.save(tasks.getAll());
    }

    /**
     * Executes the todo command by creating a
     * Todo and setting its description.
     * Then, it adds the Deadline to the task list.
     * 
     * @param tasks the array of all tasks
     * @param input the user's input
     * @param ui the object that communicates with the user
     * @param storage the save file to be updated
     */
    private static void executeTodoCommand(TaskList tasks, String input, Ui ui, Storage storage) {
        String description = input.length() > TODO_COMMAND.length() + 1
                ? input.substring(TODO_COMMAND.length() + 1).trim()
                : "";
        
        if (description.isEmpty()) {
            ui.printError("Bevo says a todo must have a description.");
            return;
        }
        
        Task todo = new Todo(description);
        tasks.add(todo);
        ui.printAddCommand(todo, tasks.size());
        storage.save(tasks.getAll());
    }

    /**
     * Executes the unmark command by setting the user-requested
     * task to incomplete.
     * 
     * @param tasks the list of all tasks
     * @param input the user's input
     * @param ui the object that communicates with the user
     * @param storage the save file to be updated
     */
    private static void executeUnmarkCommand(TaskList tasks, String input, Ui ui, Storage storage) {
        String taskNumber = input.split(" ")[1];
        int index = Integer.parseInt(taskNumber) - 1;
        tasks.get(index).markAsNotDone();
        ui.printUnmarkCommand(tasks.get(index));
        storage.save(tasks.getAll());
    }

    /**
     * Executes the mark command by setting the user-requested
     * task to complete.
     * 
     * @param tasks the list of all tasks
     * @param input the user's input
     * @param ui the object that communicates with the user
     * @param storage the save file to be updated
     */
    private static void executeMarkCommand(TaskList tasks, String input, Ui ui, Storage storage) {
        String taskNumber = input.split(" ")[1];
        int index = Integer.parseInt(taskNumber) - 1;
        tasks.get(index).markAsDone();
        ui.printMarkCommand(tasks.get(index));
        storage.save(tasks.getAll());
    }

    /**
     * Executes the find command by listing all the tasks
     * with the specified keyword in the input.
     * 
     * @param tasks the list of all tasks
     * @param input the user's input
     * @param ui the object that communicates with the user
     */
    private static void executeFindCommand(TaskList tasks, String input, Ui ui) {
        String keyword = input.length() > FIND_COMMAND.length() + 1
            ? input.substring(FIND_COMMAND.length() + 1).trim()
            : "";
        
        if (keyword.isEmpty()) {
            ui.printError("Bevo says that a keyword must be provided.");
            return;
        }

        TaskList matches = tasks.findAll(keyword);
        ui.printFindCommand(matches);
    }
}
