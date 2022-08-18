public class MarkRequest extends Request {
    private String[] inputArray;
    private TasksList tasksList;
    private static final String MARK_MSG = "Nice! I've marked this task as done:\n";

    public MarkRequest(TasksList tasksList, String[] inputArray) {
        this.tasksList = tasksList;
        this.inputArray = inputArray;
    }

    @Override
    public void execute() throws DukeException {
        if (this.inputArray.length < 2) {
            throw new DukeException("Missing Task Number!");
        }
        try {
            int taskNumber = Integer.parseInt(this.inputArray[1]);
            Task markedTask = this.tasksList.markAsDone(taskNumber);
            super.printResponse(MarkRequest.MARK_MSG + markedTask);
        //exception due to parsing
        } catch (NumberFormatException exception) {
            throw new DukeException("Please enter a integer for task number!");
        }
    }
}



