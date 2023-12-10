package ui.Page_Objects;

import org.openqa.selenium.support.ui.Select;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.$x;

public class TasksPage {
    /**
     * Method used to handle sync. issues.
     */
    public static void delay() {
        Random rand = new Random();
        int delayInSeconds = rand.nextInt(2) + 1; // This will generate a random number between 2 and 4.
        try {
            TimeUnit.SECONDS.sleep(delayInSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /* Elements present in the header of TasksPage */
    private static final String dropdown = "//*[@class=\"dropdown\"]";
    private static final String logoPacToDo_button = "//*[text()=\"PacToDo\"]";
    private static final String project_button = "(//*[text()=\"Project\"])[2]";
    private static final String logout_button = "//*[text()=\"Logout\"]";
    private static final String newTask_button = "//*[text()=\"NEW TASK\"]";

    /* Elements present in the "Add task" and "Edit task" modal widow */
    private static final String taskTitle_field = "//*[@placeholder=\"Input task title\"]";
    private static final String dueDate_field = "//input[@type=\"date\"]";
    private static final String selectProject_dropdown = "//select";
    private static final String description_textarea = "//textarea";
    private static final String save_button = "//*[text()=\"Save\"]";

    /* Elements present in the TasksPage */
    private static final String latestEditTask_button = "((//div[@class=\"bg-base-100 shadow-xl border-secondary border-solid border-2 rounded-md text-left p-3 mb-3 \"])[last()]//button)[2]";
    private static final String latestCheckmark_button = "((//div[@class=\"bg-base-100 shadow-xl border-secondary border-solid border-2 rounded-md text-left p-3 mb-3 \"])[last()]//button)[1]";
    /* Actions performed on identified elements */
    public static void clickProject_button() {
        $x(project_button).click();
    }
    public static void clickNewTask_button() {
        $x(newTask_button).click();
    }
    private static int tasksCounter1 = 1;
    public static void inputTaskTitle_field() {
        $x(taskTitle_field).sendKeys("Task " + tasksCounter1);
        tasksCounter1++;
    }
    public static void inputTaskTitle_field(String title) {
        $x(taskTitle_field).clear();
        $x(taskTitle_field).sendKeys(title);
    }
    public static void inputDueDate_field() {
        $x(dueDate_field).sendKeys("23022019");
    }
    public static void inputDueDate_field(String date) {
        $x(dueDate_field).clear();
        $x(dueDate_field).sendKeys(date);
    }
    public static void selectLatestProjectFrom_dropdown() {
        Select selectProjectDropdown = new Select($x(selectProject_dropdown));
        int optionsNumber = selectProjectDropdown.getOptions().size();
        selectProjectDropdown.selectByIndex(optionsNumber - 1);
        /* Get methods are used to retrieve information about the dropdown, not to change the state of the dropdown. */
        /* Select methods are used to change the state of the dropdown by selecting options. */
    }
    private static int tasksCounter2 = 1;
    public static void inputDescription_textarea() {
        $x(description_textarea).sendKeys("This is description for task " + tasksCounter2);
        tasksCounter2++;
    }
    public static void inputDescription_textarea(String description) {
        $x(description_textarea).clear();
        $x(description_textarea).sendKeys(description);
    }
    public static void clickSave_button(){
        $x(save_button).click();
    }
    public static void addTaskToLatestProject(){
        clickNewTask_button();
        inputTaskTitle_field();
        inputDueDate_field();
        selectLatestProjectFrom_dropdown();
        inputDescription_textarea();
        clickSave_button();
    }

    public static void clickLatestEditTask_button(){
        $x(latestEditTask_button).click();
    }
    public static void clickLatestCheckmark_button(){
        $x(latestCheckmark_button).click();
    }
    public static void selectProjectByNumberFrom_dropdown(int number) {
        Select selectProjectDropdown = new Select($x(selectProject_dropdown));
        selectProjectDropdown.selectByIndex(number);
    }
    public static void addTasksToProjects(int number) {
        clickNewTask_button();
        inputTaskTitle_field();
        inputDueDate_field();
        selectProjectByNumberFrom_dropdown(number);
        inputDescription_textarea();
        clickSave_button();
    }
    public static void clickFilterProject_button(int project) {
        String filterProject_button = "(//div[@class=\"flex justify-center flex-wrap gap-2 my-2 sm:p-3\"]/button)[" + project + "]";
        $x(filterProject_button).click();
    }
}