import java.util.ArrayList;

public class TaskList {
    /** The internal list structure of all the tasks. */
    private ArrayList<Task> tasks;

    /**
     * Constructor for TaskList that creates a list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor for TaskList that converts from an existing list.
     * @param tasks the list of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds the task into the task list.
     * 
     * @param task the task to be added to the list
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes the task (at that index) from the task list.
     * 
     * @param index the index of the task to be removed
     * @return the task that is removed
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Returns the task (at that index) from the task list.
     * 
     * @param index the index of the task to be retrieved
     * @return the task that is retrieved
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the size of the task list.
     * 
     * @return the size of the task list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the task list (in ArrayList format).
     * 
     * @return the task list
     */
    public ArrayList<Task> getAll() {
        return tasks;
    }

    /**
     * Finds all the tasks within the task list that contains
     * a specific keyword in their descriptions.
     * 
     * @param keyword the identifying keyword
     * @return the matching task list
     */
    public TaskList findAll(String keyword) {
        ArrayList<Task> matches = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matches.add(task);
            }
        }
        return new TaskList(matches);
    }
}
