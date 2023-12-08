package ui;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import io.qameta.allure.Owner;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import readProperties.ConfigProvider;
import ui.Page_Objects.LoginPage;
import ui.Page_Objects.ProjectsPage;
import ui.Page_Objects.TasksPage;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@ExtendWith(ScreenShooterExtension.class)
public class MainFunctionalityTestClass extends SelenideAbstractClass {
    @Owner("Paul")
    @Test
    public void test01_login() {
        Selenide.open(ConfigProvider.URL);

        LoginPage loginPage = new LoginPage();

        loginPage.clickGetstarted_button();

        loginPage.enterEmail(ConfigProvider.DEMO_EMAIL);

        loginPage.enterPassword(ConfigProvider.DEMO_PASSWORD);

        loginPage.clickLoginButton();

        TasksPage tasksPage = new TasksPage();
        tasksPage.clickProject_button();

        ProjectsPage projectsPage = new ProjectsPage();
        projectsPage.clickNewProject_button();
        projectsPage.inputProjectName_field();
        projectsPage.inputProjectDescription_field();
        projectsPage.clickSave_button();
        $x("//div[@class='bg-base-100 shadow-xl border-secondary border-solid border-2 rounded-md text-left p-3 mb-3']").shouldBe(visible);
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
        $x("//div[@class='bg-base-100 shadow-xl border-secondary border-solid border-2 rounded-md text-left p-3 mb-3']").shouldBe(visible);
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
        projectsPage.inputNewProjectName_field("Hello1");
        projectsPage.inputNewProjectDescription_field("Hello1");
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

        tasksPage.inputNewTaskTitle_field("Qwerty1");
        tasksPage.inputNewDueDate_field("23022020");
        tasksPage.inputNewDescription_textarea("Qwerty1");
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