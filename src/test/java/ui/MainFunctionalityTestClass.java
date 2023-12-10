package ui;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import io.qameta.allure.Owner;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.Test;

import readDataFromXLSX.tasks_providerPOJO1;
import readProperties.ConfigProvider;
import ui.Page_Objects.LoginPage;
import ui.Page_Objects.ProjectsPage;
import ui.Page_Objects.TasksPage;


import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;
import static readDataFromXLSX.XLSX_to_POJOs.xlsxDataToListOfPOJOs;

@ExtendWith(ScreenShooterExtension.class)
public class MainFunctionalityTestClass extends SelenideAbstractClass {
    @DisplayName("Simple Excel Test")
    @Test
    public void test01_login() {
        Selenide.open(ConfigProvider.URL);

        LoginPage.clickGetstarted_button();

        LoginPage.enterEmail(ConfigProvider.DEMO_EMAIL);

        LoginPage.enterPassword(ConfigProvider.DEMO_PASSWORD);

        LoginPage.clickLoginButton();


        TasksPage.clickProject_button();
        ProjectsPage.clickNewProject_button();
        ProjectsPage.inputProjectName_field();
        ProjectsPage.inputProjectDescription_field();
        ProjectsPage.clickSave_button();
        $x("//div[@class='bg-base-100 shadow-xl border-secondary border-solid border-2 rounded-md text-left p-3 mb-3']").shouldBe(visible);
    }

    @DisplayName("Simple Excel Test")
    @Test
    public void test02_addProject() {
        LoginPage.login();
        TasksPage.clickProject_button();
        ProjectsPage.clickNewProject_button();
        ProjectsPage.inputProjectName_field();
        ProjectsPage.inputProjectDescription_field();
        ProjectsPage.clickSave_button();
        $x("//div[@class='bg-base-100 shadow-xl border-secondary border-solid border-2 rounded-md text-left p-3 mb-3']").shouldBe(visible);
    }

    @DisplayName("Simple Excel Test")
    @Test
    public void test03_editLatestProject() {
        LoginPage.login();
        TasksPage.clickProject_button();
        ProjectsPage.addProject();
        ProjectsPage.clickLatestEditProject_button();
        ProjectsPage.inputProjectName_field("Hello1");
        ProjectsPage.inputProjectDescription_field("Hello1");
        ProjectsPage.clickSave_button();
    }

    @DisplayName("Simple Excel Test")
    @Test
    public void test04_deleteLatestProject() {
        LoginPage.login();
        TasksPage.clickProject_button();
        ProjectsPage.addProject();
        ProjectsPage.clickLatestDeleteProject_button();
        ProjectsPage.clickConfirm_button();
    }

    @DisplayName("Simple Excel Test")
    @Test
    public void test05_addTaskToLatestProject() {
        LoginPage.login();
        TasksPage.clickProject_button();
        ProjectsPage.addProject();
        ProjectsPage.clickTask_button();
        TasksPage.clickNewTask_button();
        TasksPage.inputTaskTitle_field();
        TasksPage.inputDueDate_field();
        TasksPage.selectLatestProjectFrom_dropdown();
        TasksPage.inputDescription_textarea();
        TasksPage.clickSave_button();
    }

    @DisplayName("Simple Excel Test")
    @Test
    public void test06_editLatestTask() {
        LoginPage.login();
        TasksPage.clickProject_button();
        ProjectsPage.addProject();
        ProjectsPage.clickTask_button();
        TasksPage.addTaskToLatestProject();
        TasksPage.clickLatestEditTask_button();
        TasksPage.inputTaskTitle_field("Qwerty1");
        TasksPage.inputDueDate_field("23022020");
        TasksPage.inputDescription_textarea("Qwerty1");
        TasksPage.clickSave_button();
    }

    @DisplayName("Simple Excel Test")
    @Test
    public void test07_markLatestTaskAsDone() {
        LoginPage.login();
        TasksPage.clickProject_button();
        ProjectsPage.addProject();
        ProjectsPage.clickTask_button();
        TasksPage.addTaskToLatestProject();
        TasksPage.clickLatestCheckmark_button();
    }

    @DisplayName("Simple Excel Test")
    @Test
    public void test08_filterTasksByProjects() {
        LoginPage.login();
        TasksPage.clickProject_button();
        ProjectsPage.addProject();
        ProjectsPage.addProject();
        ProjectsPage.addProject();
        ProjectsPage.clickTask_button();
        TasksPage.addTasksToProjects(1);
        TasksPage.addTasksToProjects(2);
        TasksPage.addTasksToProjects(3);
        TasksPage.clickFilterProject_button(1);
        TasksPage.clickFilterProject_button(2);
        TasksPage.clickFilterProject_button(3);
    }
}