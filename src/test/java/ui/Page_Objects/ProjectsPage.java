package ui.Page_Objects;

import com.codeborne.selenide.SelenideElement;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.$x;

public class ProjectsPage {
    /* Elements present in the header of ProjectsPage */
    private static final SelenideElement task_button = $x("(//*[text()='Task'])[2]");
    private static final SelenideElement newProject_button = $x("//*[text()='NEW PROJECT']");

    /* Elements present in the ProjectsPage */
    private static final SelenideElement projectName_field = $x("//*[@placeholder='Input project title']");
    private static final SelenideElement projectDescription_field = $x("//*[@placeholder='Input description']");
    private static final SelenideElement save_button = $x("//*[text()='Save']");
    private static final String project_block_xpath = "//div[@class='bg-base-100 shadow-xl border-secondary border-solid border-2 rounded-md text-left p-3 mb-3']";
    public static String getProject_block_xpath() {
        return project_block_xpath;
    }
    private static final SelenideElement project_block = $x(project_block_xpath);
    public static SelenideElement getProject_block() {
        return project_block;
    }
    private static final SelenideElement latestEditProject_button = $x(project_block_xpath + "[last()]//button[1]");
    private static final SelenideElement latestDeleteProject_button = $x(project_block_xpath + "[last()]//button[2]");
    private static final SelenideElement confirm_button = $x("//*[text()='Confirm']");

    /* Actions performed on identified elements */
    public static void clickNewProject_button() {
        newProject_button.click();
    }
    private static int projectsCounter1 = 1;
    public static void inputProjectName_field() {
        projectName_field.sendKeys("Project " + projectsCounter1);
        projectsCounter1++;
    }
    private static int projectsCounter2 = 1;
    public static void inputProjectDescription_field() {
        projectDescription_field.sendKeys("This is description for project " + projectsCounter2);
        projectsCounter2++;
    }
    public static void clickSave_button() {
        save_button.click();
    }
    public static void addProject() {
        clickNewProject_button();
        inputProjectName_field();
        inputProjectDescription_field();
        clickSave_button();
    }
    public static void clickLatestEditProject_button() {
        latestEditProject_button.click();
    }
    public static void inputProjectName_field(String name) {
        projectName_field.clear();
        projectName_field.sendKeys(name);
    }
    public static void inputProjectDescription_field(String name) {
        projectDescription_field.clear();
        projectDescription_field.sendKeys(name);
    }
    public static void clickLatestDeleteProject_button() {
        latestDeleteProject_button.click();
    }
    public static void clickConfirm_button() {
        confirm_button.click();
    }
    public static void clickTask_button() {
        task_button.click();
    }
}
