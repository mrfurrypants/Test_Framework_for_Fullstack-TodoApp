package ui;

import org.junit.jupiter.api.Disabled;
import ui.Page_Objects.LoginPage;
import ui.Page_Objects.ProjectsPage;
import ui.Page_Objects.TasksPage;
import org.junit.jupiter.api.Test;
public class MainFunctionalityTestClass extends SelenideAbstractClass {
    @Test
    public void test01_login() {
        LoginPage loginPage = new LoginPage();
        loginPage.enterEmail();
        loginPage.enterPassword();
        loginPage.clickLoginButton();
    }

    @Test
    public void test02_addProject() {
        LoginPage loginPage = new LoginPage();
        loginPage.login();

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
        loginPage.login();

        TasksPage tasksPage = new TasksPage();
        tasksPage.clickProject_button();

        ProjectsPage projectsPage = new ProjectsPage();
        projectsPage.addProject();

        projectsPage.clickLatestEditProject_button();
        projectsPage.inputNewProjectName_field();
        projectsPage.inputNewProjectDescription_field();
        projectsPage.clickSave_button();
    }

    @Test
    public void test04_deleteLatestProject() {
        LoginPage loginPage = new LoginPage();
        loginPage.login();

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
        loginPage.login();

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
        loginPage.login();

        TasksPage tasksPage = new TasksPage();
        tasksPage.clickProject_button();

        ProjectsPage projectsPage = new ProjectsPage();
        projectsPage.addProject();

        projectsPage.clickTask_button();

        tasksPage.addTaskToLatestProject();

        tasksPage.clickLatestEditTask_button();

        tasksPage.inputNewTaskTitle_field();
        tasksPage.inputNewDueDate_field();
        tasksPage.inputNewDescription_textarea();
        tasksPage.clickSave_button();
    }

    @Test
    public void test07_markLatestTaskAsDone() {
        LoginPage loginPage = new LoginPage();
        loginPage.login();

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
        loginPage.login();

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