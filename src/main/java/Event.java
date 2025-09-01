public class Event extends Task {
    
    /** Start time of the timeline */
    protected String from;

    /** End time of the timeline */
    protected String to;

    /**
     * Creates an Event with a specified
     * description and timeline.
     * isDone is set to false by default.
     * 
     * @param description Description of task to be completed
     * @param from Start time
     * @param to End time
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Displays a String that contains the status,
     * description, and event timeline of the Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
