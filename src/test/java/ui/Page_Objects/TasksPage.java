package ui.Page_Objects;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class TasksPage {
    /* Elements present in the header of TasksPage */
    private static final SelenideElement project_button = $x("(//*[text()='Project'])[2]");
    private static final SelenideElement logout_button = $x("(//*[text()='Logout'])[2]");
    public static SelenideElement getLogout_button() {
        return logout_button;
    }
    private static final SelenideElement newTask_button = $x("//*[text()='NEW TASK']");
    private static final SelenideElement confirm_button = $x("//*[text()='Confirm']");

    /* Elements present in the "Add task" and "Edit task" modal widow */
    private static final SelenideElement taskTitle_field = $x("//*[@placeholder='Input task title']");
    private static final SelenideElement dueDate_field = $x("//input[@type='date']");
    private static final SelenideElement selectProject_dropdown = $x("//select");
    private static final SelenideElement description_textarea = $x("//textarea");
    private static final SelenideElement save_button = $x("//*[text()='Save']");

    /* Elements present in the TasksPage */
    private static final String task_block_xpath = "//div[contains(@class, 'bg-base-100 shadow-xl border-secondary border-solid border-2 rounded-md text-left p-3 mb-3 ')]";
    public static String getTask_block_xpath() {
        return task_block_xpath;
    }
    private static final SelenideElement task_block = $x(task_block_xpath);
    public static SelenideElement getTask_block() {
        return task_block;
    }
    private static final SelenideElement latestDeleteTask_button = $x(task_block_xpath + "[last()]/div/button[3]");
    private static final SelenideElement latestEditTask_button = $x(task_block_xpath + "[last()]/div/button[2]");
    private static final SelenideElement latestCheckmark_button = $x(task_block_xpath + "[last()]/div/button[1]");

    /* Actions performed on identified elements */
    public static void clickProject_button() {
        project_button.click();
    }
    public static void clickNewTask_button() {
        newTask_button.click();
    }
    private static int tasksCounter1 = 1;
    public static void inputTaskTitle_field() {
        taskTitle_field.clear();
        taskTitle_field.sendKeys("Task " + tasksCounter1);
        tasksCounter1++;
    }
    public static void inputTaskTitle_field(String title) {
        taskTitle_field.clear();
        taskTitle_field.sendKeys(title);
    }
    public static void inputDueDate_field() {
        dueDate_field.clear();
        dueDate_field.sendKeys("27122023");
        dueDate_field.sendKeys(Keys.ENTER);
//        executeJavaScript("arguments[0].value = arguments[1]", dueDate_field, "2023-12-27");
    }
    public static void inputDueDate_field(String date) {
        dueDate_field.clear();
        dueDate_field.sendKeys(date);
    }
    public static void selectLatestProjectFrom_dropdown() {
        Select selectProjectDropdown = new Select(selectProject_dropdown);
        int optionsNumber = selectProjectDropdown.getOptions().size();
        selectProjectDropdown.selectByIndex(optionsNumber - 1);
    }
    private static int tasksCounter2 = 1;
    public static void inputDescription_textarea() {
        description_textarea.clear();
        description_textarea.sendKeys("This is description for task " + tasksCounter2);
        tasksCounter2++;
    }
    public static void inputDescription_textarea(String description) {
        description_textarea.clear();
        description_textarea.sendKeys(description);
    }
    public static void clickSave_button(){
        save_button.click();
    }
    public static void addTaskToLatestProject(){
        clickNewTask_button();
        inputTaskTitle_field();
        inputDueDate_field();
        selectLatestProjectFrom_dropdown();
        inputDescription_textarea();
        clickSave_button();
    }

    public static void clickLatestDeleteTask_button(){
        latestDeleteTask_button.click();
    }
    public static void clickLatestEditTask_button(){
        latestEditTask_button.click();
    }
    public static void clickLatestCheckmark_button(){
        latestCheckmark_button.click();
    }
    public static void selectProjectByNumberFrom_dropdown(int number) {
        Select selectProjectDropdown = new Select(selectProject_dropdown);
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
    public static void clickConfirm_button() {
        confirm_button.click();
    }
}