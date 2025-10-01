public class Deadline extends Task {
    
    /** Deadline time */
    protected String by;

    /**
     * Creates a Deadline with a specified
     * description and deadline time.
     * isDone is set to false by default.
     * 
     * @param description Description of task to be completed
     * @param by Deadline time
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    
    /**
     * Displays a string that contains the metadata
     * of the deadline to be added to the save file.
     */
    @Override
    public String toSaveFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }

    /**
     * Displays a String that contains the status,
     * description, and deadline time of the Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
