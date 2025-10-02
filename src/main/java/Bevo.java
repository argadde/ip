public class Bevo {
    /** Specified file path to store task list and each task's metadata */
    private static final String FILE_PATH = "data/bevo.txt";

    /** Interface variables. */
    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    /**
     * Sets up the interface for task list tracking.
     */
    public Bevo() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        tasks = new TaskList(storage.load());
    }

    /**
     * Runs the application until user requests to exit.
     */
    public void run() {
        ui.printWelcomeMessage();

        boolean isExit = false;
        while (!isExit) {
            String input = ui.read();
            isExit = Parser.parse(input, tasks, ui, storage);
        }
    }

    /**
     * Main method to be run.
     * 
     * @param args arguments from the terminal
     */
    public static void main(String[] args) {
        new Bevo().run();
    }
}
