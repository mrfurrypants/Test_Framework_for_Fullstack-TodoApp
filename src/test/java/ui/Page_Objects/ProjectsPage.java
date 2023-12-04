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
    private final String dropdown = "//*[@class=\"dropdown\"]";
    private final String logoPacToDo_button = "//*[text()=\"PacToDo\"]";
    private final String task_button = "(//*[text()=\"Task\"])[2]";
    private final String logout_button = "//*[text()=\"Logout\"]";
    private final String newProject_button = "//*[text()=\"NEW PROJECT\"]";

    /* Elements present in the ProjectsPage */
    private final String projectName_field = "//*[@placeholder=\"Input project title\"]";
    private final String projectDescription_field = "//*[@placeholder=\"Input description\"]";
    private final String save_button = "//*[text()=\"Save\"]";
    private final String latestEditProject_button = "((//*[@class=\"bg-base-100 shadow-xl border-secondary border-solid border-2 rounded-md text-left p-3 mb-3\"])[last()]//button)[1]";
    private final String latestDeleteProject_button = "((//*[@class=\"bg-base-100 shadow-xl border-secondary border-solid border-2 rounded-md text-left p-3 mb-3\"])[last()]//button)[2]";
    private final String confirm_button = "//*[text()=\"Confirm\"]";

    /* Actions performed on identified elements */
    public void clickNewProject_button() {
        $x(newProject_button).click();
    }
    private int projectsCounter1 = 1;
    public void inputProjectName_field() {
        $x(projectName_field).sendKeys("Project " + projectsCounter1);
        projectsCounter1++;
    }
    private int projectsCounter2 = 1;
    public void inputProjectDescription_field() {
        $x(projectDescription_field).sendKeys("This is description for project " + projectsCounter2);
        projectsCounter2++;
    }
    public void clickSave_button() {
        $x(save_button).click();
    }
    public void addProject() {
        clickNewProject_button();
        delay();
        inputProjectName_field();
        delay();
        inputProjectDescription_field();
        delay();
        clickSave_button();
        delay();
    }
    public void clickLatestEditProject_button() {
        $x(latestEditProject_button).click();
    }
    public void inputNewProjectName_field(String name) {
        $x(projectName_field).clear();
        $x(projectName_field).sendKeys(name);
    }
    public void inputNewProjectDescription_field(String name) {
        $x(projectDescription_field).clear();
        $x(projectDescription_field).sendKeys(name);
    }
    public void clickLatestDeleteProject_button() {
        $x(latestDeleteProject_button).click();
    }
    public void clickConfirm_button() {
        $x(confirm_button).click();
    }
    public void clickTask_button() {
        $x(task_button).click();
    }
}
