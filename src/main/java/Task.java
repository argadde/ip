public class Task {
    /** Description of task */
    protected String description;

    /** Completion status of task (done or not done) */
    protected boolean isDone;

    /**
     * Creates a Task with a specified description.
     * isDone is set to false by default.
     * 
     * @param description Description of task to be completed
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Determines completion icom based on status
     * to be used in the toString() method when displayed.
     * 
     * @return Icon that represents completion status.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as complete.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as incomplete.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Displays a String that contains the status
     * and the description of the Task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
