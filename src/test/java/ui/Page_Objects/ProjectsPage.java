package ui.Page_Objects;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.$x;

public class ProjectsPage {
    public static void delay() {
        Random rand = new Random();
        int delayInSeconds = rand.nextInt(2) + 1; // This will generate a random number between 2 and 4.
        try {
            TimeUnit.SECONDS.sleep(delayInSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /* Elements present in the header of ProjectsPage */
    private static final String dropdown = "//*[@class=\"dropdown\"]";
    private static final String logoPacToDo_button = "//*[text()=\"PacToDo\"]";
    private static final String task_button = "(//*[text()=\"Task\"])[2]";
    private static final String logout_button = "//*[text()=\"Logout\"]";
    private static final String newProject_button = "//*[text()=\"NEW PROJECT\"]";

    /* Elements present in the ProjectsPage */
    private static final String projectName_field = "//*[@placeholder=\"Input project title\"]";
    private static final String projectDescription_field = "//*[@placeholder=\"Input description\"]";
    private static final String save_button = "//*[text()=\"Save\"]";
    private static final String latestEditProject_button = "((//*[@class=\"bg-base-100 shadow-xl border-secondary border-solid border-2 rounded-md text-left p-3 mb-3\"])[last()]//button)[1]";
    private static final String latestDeleteProject_button = "((//*[@class=\"bg-base-100 shadow-xl border-secondary border-solid border-2 rounded-md text-left p-3 mb-3\"])[last()]//button)[2]";
    private static final String confirm_button = "//*[text()=\"Confirm\"]";

    /* Actions performed on identified elements */
    public static void clickNewProject_button() {
        $x(newProject_button).click();
    }
    private static int projectsCounter1 = 1;
    public static void inputProjectName_field() {
        $x(projectName_field).sendKeys("Project " + projectsCounter1);
        projectsCounter1++;
    }
    private static int projectsCounter2 = 1;
    public static void inputProjectDescription_field() {
        $x(projectDescription_field).sendKeys("This is description for project " + projectsCounter2);
        projectsCounter2++;
    }
    public static void clickSave_button() {
        $x(save_button).click();
    }
    public static void addProject() {
        clickNewProject_button();
        inputProjectName_field();
        inputProjectDescription_field();
        clickSave_button();
    }
    public static void clickLatestEditProject_button() {
        $x(latestEditProject_button).click();
    }
    public static void inputProjectName_field(String name) {
        $x(projectName_field).clear();
        $x(projectName_field).sendKeys(name);
    }
    public static void inputProjectDescription_field(String name) {
        $x(projectDescription_field).clear();
        $x(projectDescription_field).sendKeys(name);
    }
    public static void clickLatestDeleteProject_button() {
        $x(latestDeleteProject_button).click();
    }
    public static void clickConfirm_button() {
        $x(confirm_button).click();
    }
    public static void clickTask_button() {
        $x(task_button).click();
    }
}
