import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Storage {
    /** File path of the save file. */
    private String filePath;

    /**
     * Constuctor to build the storage with the specified file path.
     * 
     * @param filePath the file path of the save file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the task list and each task's metadata from a
     * save file to an array for further modifications.
     * 
     * @return the loaded task list from the save file
     */
    public ArrayList<Task> load() {
        ArrayList<Task> taskList = new ArrayList<>();

        File f = new File(filePath);
        try {
            if (!f.exists()) {
                f.getParentFile().mkdirs();
                f.createNewFile();
            }

            Scanner scanner = new Scanner(f);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                Task task = Parser.parseTaskFromSaveFile(line);
                if (task != null) {
                    taskList.add(task);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Bevo cannot find the save file.");
        } catch (IOException e) {
            System.out.println("Bevo cannot initialize storage: " + e.getMessage());
        }

        return taskList;
    }

    /**
     * Saves the existing task list and metadata into the save file.
     * 
     * @param taskList the current task list and metadata
     */
    public void save(ArrayList<Task> taskList) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task : taskList) {
                fw.write(task.toSaveFileFormat() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Bevo could not save tasks: " + e.getMessage());
        }
    }
}
