package ui.Page_Objects;

import org.openqa.selenium.support.ui.Select;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.$x;

public class TasksPage {
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
    String dropdown = "//*[@class=\"dropdown\"]";
    String logoPacToDo_button = "//*[text()=\"PacToDo\"]";
    String project_button = "(//*[text()=\"Project\"])[2]";
    String logout_button = "//*[text()=\"Logout\"]";
    String newTask_button = "//*[text()=\"NEW TASK\"]";
    /* Elements present in the "Add task" and "Edit task" modal widow */
    String taskTitle_field = "//*[@placeholder=\"Input task title\"]";
    String dueDate_field = "//input[@type=\"date\"]";
    String selectProject_dropdown = "//select";
    String description_textarea = "//textarea";
    String save_button = "//*[text()=\"Save\"]";
    /* Elements present in the TasksPage */
    String latestEditTask_button = "((//div[@class=\"bg-base-100 shadow-xl border-secondary border-solid border-2 rounded-md text-left p-3 mb-3 \"])[last()]//button)[2]";
    String latestCheckmark_button = "((//div[@class=\"bg-base-100 shadow-xl border-secondary border-solid border-2 rounded-md text-left p-3 mb-3 \"])[last()]//button)[1]";
    /* Actions performed on identified elements */
    public void clickProject_button() {
        $x(project_button).click();
    }
    public void clickNewTask_button() {
        $x(newTask_button).click();
    }
    public int tasksCounter1 = 1;
    public void inputTaskTitle_field() {
        $x(taskTitle_field).sendKeys("Task " + tasksCounter1);
        tasksCounter1++;
    }
    public void inputDueDate_field() {
        $x(dueDate_field).sendKeys("23022019");
    }
    public void selectLatestProjectFrom_dropdown() {
        Select selectProjectDropdown = new Select($x(selectProject_dropdown));
        int optionsNumber = selectProjectDropdown.getOptions().size();
        selectProjectDropdown.selectByIndex(optionsNumber - 1);
        /* Get methods are used to retrieve information about the dropdown, not to change the state of the dropdown. */
        /* Select methods are used to change the state of the dropdown by selecting options. */
    }
    public int tasksCounter2 = 1;
    public void inputDescription_textarea() {
        $x(description_textarea).sendKeys("This is description for task " + tasksCounter2);
        tasksCounter2++;
    }
    public void clickSave_button(){
        $x(save_button).click();
    }
    public void addTaskToLatestProject(){
        clickNewTask_button();
        delay();
        inputTaskTitle_field();
        delay();
        inputDueDate_field();
        delay();
        selectLatestProjectFrom_dropdown();
        delay();
        inputDescription_textarea();
        delay();
        clickSave_button();
        delay();
    }
    public void clickLatestEditTask_button(){
        $x(latestEditTask_button).click();
    }
    public void inputNewTaskTitle_field(){
        $x(taskTitle_field).clear();
        $x(taskTitle_field).sendKeys("Qwerty1");
    }
    public void inputNewDueDate_field(){
        $x(dueDate_field).clear();
        $x(dueDate_field).sendKeys("23022020");
    }
    public void inputNewDescription_textarea(){
        $x(description_textarea).clear();
        $x(description_textarea).sendKeys("Qwerty1");
    }
    public void clickLatestCheckmark_button(){
        $x(latestCheckmark_button).click();
    }
    public void selectProjectByNumberFrom_dropdown(int number) {
        Select selectProjectDropdown = new Select($x(selectProject_dropdown));
        selectProjectDropdown.selectByIndex(number);
    }
    public void addTasksToProjects(int number) {
        clickNewTask_button();
        delay();
        inputTaskTitle_field();
        delay();
        inputDueDate_field();
        delay();
        selectProjectByNumberFrom_dropdown(number);
        delay();
        inputDescription_textarea();
        delay();
        clickSave_button();
        delay();
    }
    public void clickFilterProject_button(int project) {
        String filterProject_button = "(//div[@class=\"flex justify-center flex-wrap gap-2 my-2 sm:p-3\"]/button)[" + project + "]";
        $x(filterProject_button).click();
    }
}