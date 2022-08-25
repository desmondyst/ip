package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TasksList;
import duke.task.Todo;

import java.util.List;

/**
 * Represents a command to find a task by searching for a keyword.
 */
public class FindCommand extends Command {
    private String[] inputArray;
    private TasksList tasksList;

    private static final String FOUND_MSG = "Here are the matching tasks in your list:\n\n";
    private static final String NOT_FOUND_MSG = "There are no matching tasks in your list.\n";

    /**
     * Creates a new FindCommand instance.
     * @param tasksList The TasksList to search in.
     * @param inputArray The array that represents the user input.
     */
    public FindCommand(TasksList tasksList, String[] inputArray) {
        this.tasksList = tasksList;
        this.inputArray = inputArray;
    }

    /**
     * Finds the tasks that match the keyword.
     * @return The list of matching Tasks represented in a single string.
     * @throws DukeException If the input array is invalid.
     */
    @Override
    public String execute() throws DukeException {
        if (this.inputArray.length < 2) {
            throw new DukeException("Missing keyword!");
        }

        String keyword = this.inputArray[1];
        List<Task> matchingTasks = this.tasksList.findMatchingTasks(keyword);
        int size = matchingTasks.size();

        if (size == 0) {
            return FindCommand.NOT_FOUND_MSG;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(FindCommand.FOUND_MSG);
            for (int i = 1; i <= size; i++) {
                sb.append(String.format("%d. %s\n", i, matchingTasks.get(i - 1)));
            }
            return sb.toString();
        }
    }
}
