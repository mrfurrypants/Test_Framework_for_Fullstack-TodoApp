package ui;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Disabled;
import readProperties.ConfigProvider;
import ui.Page_Objects.LoginPage;
import ui.Page_Objects.ProjectsPage;
import ui.Page_Objects.TasksPage;
import org.junit.jupiter.api.Test;
public class MainFunctionalityTestClass extends SelenideAbstractClass {
    @Test
    public void test01_login() {
        LoginPage loginPage = new LoginPage();
        loginPage.enterEmail(ConfigProvider.DEMO_EMAIL);
        loginPage.enterPassword(ConfigProvider.DEMO_PASSWORD);
        loginPage.clickLoginButton();
    }

    @Test
    public void test02_addProject() {
        LoginPage loginPage = new LoginPage();
        loginPage.login(ConfigProvider.DEMO_EMAIL, ConfigProvider.DEMO_PASSWORD);

        TasksPage tasksPage = new TasksPage();
        tasksPage.clickProject_button();

        ProjectsPage projectsPage = new ProjectsPage();
        projectsPage.clickNewProject_button();
        projectsPage.inputProjectName_field();
        projectsPage.inputProjectDescription_field();
        projectsPage.clickSave_button();
    }

    @Test
    public void test03_editLatestProject() {
        LoginPage loginPage = new LoginPage();
        loginPage.login(ConfigProvider.DEMO_EMAIL, ConfigProvider.DEMO_PASSWORD);
        TasksPage tasksPage = new TasksPage();
        tasksPage.clickProject_button();
        ProjectsPage projectsPage = new ProjectsPage();
        projectsPage.addProject();
        projectsPage.clickLatestEditProject_button();
        projectsPage.inputNewProjectName_field("Hello1");
        projectsPage.inputNewProjectDescription_field("Hello1");
        projectsPage.clickSave_button();
    }

    @Test
    public void test04_deleteLatestProject() {
        LoginPage loginPage = new LoginPage();
        loginPage.login(ConfigProvider.DEMO_EMAIL, ConfigProvider.DEMO_PASSWORD);

        TasksPage tasksPage = new TasksPage();
        tasksPage.clickProject_button();

        ProjectsPage projectsPage = new ProjectsPage();
        projectsPage.addProject();

        projectsPage.clickLatestDeleteProject_button();
        projectsPage.clickConfirm_button();
    }

    @Test
    public void test05_addTaskToLatestProject() {
        LoginPage loginPage = new LoginPage();
        loginPage.login(ConfigProvider.DEMO_EMAIL, ConfigProvider.DEMO_PASSWORD);

        TasksPage tasksPage = new TasksPage();
        tasksPage.clickProject_button();

        ProjectsPage projectsPage = new ProjectsPage();
        projectsPage.addProject();
        projectsPage.clickTask_button();

        tasksPage.clickNewTask_button();
        tasksPage.inputTaskTitle_field();
        tasksPage.inputDueDate_field();
        tasksPage.selectLatestProjectFrom_dropdown();
        tasksPage.inputDescription_textarea();
        tasksPage.clickSave_button();
    }

    @Test
    public void test06_editLatestTask() {
        LoginPage loginPage = new LoginPage();
        loginPage.login(ConfigProvider.DEMO_EMAIL, ConfigProvider.DEMO_PASSWORD);

        TasksPage tasksPage = new TasksPage();
        tasksPage.clickProject_button();

        ProjectsPage projectsPage = new ProjectsPage();
        projectsPage.addProject();

        projectsPage.clickTask_button();

        tasksPage.addTaskToLatestProject();

        tasksPage.clickLatestEditTask_button();

        tasksPage.inputNewTaskTitle_field("Qwerty1");
        tasksPage.inputNewDueDate_field("23022020");
        tasksPage.inputNewDescription_textarea("Qwerty1");
        tasksPage.clickSave_button();
    }

    @Test
    public void test07_markLatestTaskAsDone() {
        LoginPage loginPage = new LoginPage();
        loginPage.login(ConfigProvider.DEMO_EMAIL, ConfigProvider.DEMO_PASSWORD);

        TasksPage tasksPage = new TasksPage();
        tasksPage.clickProject_button();

        ProjectsPage projectsPage = new ProjectsPage();

        projectsPage.addProject();

        projectsPage.clickTask_button();

        tasksPage.addTaskToLatestProject();

        tasksPage.clickLatestCheckmark_button();
    }

    @Test
    public void test08_filterTasksByProjects() {
        LoginPage loginPage = new LoginPage();
        loginPage.login(ConfigProvider.DEMO_EMAIL, ConfigProvider.DEMO_PASSWORD);

        TasksPage tasksPage = new TasksPage();
        tasksPage.clickProject_button();

        ProjectsPage projectsPage = new ProjectsPage();

        projectsPage.addProject();
        projectsPage.addProject();
        projectsPage.addProject();


        projectsPage.clickTask_button();

        tasksPage.addTasksToProjects(1);
        tasksPage.addTasksToProjects(2);
        tasksPage.addTasksToProjects(3);
        tasksPage.clickFilterProject_button(1);
        tasksPage.clickFilterProject_button(2);
        tasksPage.clickFilterProject_button(3);
    }

}