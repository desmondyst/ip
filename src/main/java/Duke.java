import java.util.Scanner;

public class Duke {
    private TasksList tasksList;
    private boolean hasEnded = false;
    private static final WelcomeRequest welcomeRequest = new WelcomeRequest();
    private static final GoodbyeRequest goodbyeRequest = new GoodbyeRequest();
    private static String TASKS_STORAGE_PATH = "./data/duke.txt";

    public Duke() {
        this.tasksList = new TasksList(Duke.TASKS_STORAGE_PATH);
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        greet();
        while (!hasEnded) {
            try {
                String userInput = getInput(sc);
                String[] inputArray = userInput.trim().split("\\s+", 2);

                //parse to the corresponding Command
                Command command = Command.parseToCommand(inputArray[0].toLowerCase());

                switch (command) {
                    case BYE:
                        goodBye();
                        sc.close();
                        hasEnded = true;
                        break;
                    case LIST:
                        ListRequest listRequest = new ListRequest(this.tasksList);
                        listRequest.execute();
                        break;
                    case MARK:
                        MarkRequest markRequest = new MarkRequest(this.tasksList, inputArray);
                        markRequest.execute();
                        break;
                    case UNMARK:
                        UnmarkRequest unmarkRequest = new UnmarkRequest(this.tasksList, inputArray);
                        unmarkRequest.execute();
                        break;
                    case TODO:
                        TodoRequest todoRequest = new TodoRequest(this.tasksList, inputArray);
                        todoRequest.execute();
                        break;
                    case DEADLINE:
                        DeadlineRequest deadlineRequest = new DeadlineRequest(this.tasksList, inputArray);
                        deadlineRequest.execute();
                        break;
                    case EVENT:
                        EventRequest eventRequest = new EventRequest(this.tasksList, inputArray);
                        eventRequest.execute();
                        break;
                    case DELETE:
                        DeleteRequest deleteRequest = new DeleteRequest(this.tasksList, inputArray);
                        deleteRequest.execute();
                        break;
                }
                tasksList.saveTasks();
            } catch (DukeException exception) {
                InvalidRequest invalidRequest = new InvalidRequest(exception.getMessage());
                invalidRequest.execute();
            }
        }
    }

    public String getInput(Scanner sc) {
        System.out.println();
        return sc.nextLine();
    }

    public void greet() {
        Duke.welcomeRequest.execute();
    }

    public void goodBye() {
        Duke.goodbyeRequest.execute();
    }
}

