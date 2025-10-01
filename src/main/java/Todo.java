public class Todo extends Task {

    /**
     * Creates a Todo with a specified description.
     * isDone is set to false by default.
     * 
     * @param description Description of task to be completed
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Displays a string that contains the metadata
     * of the todo to be added to the save file.
     */
    @Override
    public String toSaveFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Displays a String that contains the status and
     * description of the Todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
