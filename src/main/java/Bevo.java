public class Bevo {
    /** Specified file path to store task list and each task's metadata */
    private static final String FILE_PATH = "data/bevo.txt";

    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    public Bevo() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        tasks = new TaskList(storage.load());
    }

    public void run() {
        ui.printWelcomeMessage();

        boolean isExit = false;
        while (!isExit) {
            String input = ui.read();
            isExit = Parser.parse(input, tasks, ui, storage);
        }
    }

    public static void main(String[] args) {
        new Bevo().run();
    }
}
