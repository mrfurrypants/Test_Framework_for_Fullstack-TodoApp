package ui;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import static org.junit.jupiter.api.Assertions.*;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;
import readProperties.ConfigProvider;
import ui.Page_Objects.LoginPage;
import ui.Page_Objects.ProjectsPage;
import ui.Page_Objects.RegisterPage;
import ui.Page_Objects.TasksPage;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(ScreenShooterExtension.class)
public class MainFunctionalityTests extends SelenideAbstractClass {
    @Disabled
    @Order(1)
    @DisplayName("01. Verify that the user can sign-up using free credentials")
    @Tag("PositiveTest")
    @Test
    public void test01_signupWithFreeCredentials() {
        Selenide.open(ConfigProvider.URL);
        LoginPage.clickGetstarted_button();
        LoginPage.clickSignup_button();
        RegisterPage.enterName(ConfigProvider.USER_NAME);
        RegisterPage.enterEmail(ConfigProvider.VALID_EMAIL);
        RegisterPage.enterPassword(ConfigProvider.VALID_PASSWORD);
        RegisterPage.retypePassword(ConfigProvider.VALID_PASSWORD);
        RegisterPage.clickSignup_button();
        $x(RegisterPage.getRegCompleted_popup()).shouldBe(visible);
    }

    @DisplayName("02. Verify that the user can't sign-up using already taken credentials")
    @Tag("NegativeTest")
    @Test
    public void test02_signupWithTakenCredentials() {
        Selenide.open(ConfigProvider.URL);
        LoginPage.clickGetstarted_button();
        LoginPage.clickSignup_button();
        RegisterPage.enterName(ConfigProvider.USER_NAME);
        RegisterPage.enterEmail(ConfigProvider.VALID_EMAIL);
        RegisterPage.enterPassword(ConfigProvider.VALID_PASSWORD);
        RegisterPage.retypePassword(ConfigProvider.VALID_PASSWORD);
        RegisterPage.clickSignup_button();
        $x(RegisterPage.getRegNotCompleted_popup()).shouldBe(visible);
    }

    @DisplayName("03. Verify that the user can log-in using valid credentials")
    @Tag("PositiveTest")
    @Test
    public void test03_loginWithValidCredentials() {
        Selenide.open(ConfigProvider.URL);
        LoginPage.clickGetstarted_button();
        LoginPage.enterEmail(ConfigProvider.VALID_EMAIL);
        LoginPage.enterPassword(ConfigProvider.VALID_PASSWORD);
        LoginPage.clickLogin_button();
        $x(TasksPage.getLogout_button()).shouldBe(visible);
    }

    @DisplayName("04. Verify that the user can't log-in using invalid email")
    @Tag("NegativeTest")
    @Test
    public void test04_loginWithInvalidEmail() {
        Selenide.open(ConfigProvider.URL);
        LoginPage.clickGetstarted_button();
        LoginPage.enterEmail(ConfigProvider.INVALID_EMAIL);
        LoginPage.enterPassword(ConfigProvider.VALID_PASSWORD);
        LoginPage.clickLogin_button();
        $x(LoginPage.getValidation_popup()).shouldBe(visible);
    }

    @DisplayName("05. Verify that the user can't log-in using invalid password")
    @Tag("NegativeTest")
    @Test
    public void test05_loginWithInvalidPassword() {
        Selenide.open(ConfigProvider.URL);
        LoginPage.clickGetstarted_button();
        LoginPage.enterEmail(ConfigProvider.VALID_EMAIL);
        LoginPage.enterPassword(ConfigProvider.INVALID_PASSWORD);
        LoginPage.clickLogin_button();
        $x(LoginPage.getValidation_popup()).shouldBe(visible);
    }

    @DisplayName("06. Verify that the user can't log-in using invalid credentials")
    @Tag("NegativeTest")
    @Test
    public void test06_loginWithInvalidCredentials() {
        Selenide.open(ConfigProvider.URL);
        LoginPage.clickGetstarted_button();
        LoginPage.enterEmail(ConfigProvider.INVALID_EMAIL);
        LoginPage.enterPassword(ConfigProvider.INVALID_PASSWORD);
        LoginPage.clickLogin_button();
        $x(LoginPage.getValidation_popup()).shouldBe(visible);
    }

    @DisplayName("07. Verify that the user can create new “Project”")
    @Tag("PositiveTest")
    @Test
    public void test07_addProject() {
        LoginPage.login();
        TasksPage.clickProject_button();
        ProjectsPage.clickNewProject_button();
        ProjectsPage.inputProjectName_field();
        ProjectsPage.inputProjectDescription_field();
        ProjectsPage.clickSave_button();
        $x(ProjectsPage.getProject_block()).shouldBe(visible);
    }

    @DisplayName("08. Verify that the user can make changes to existing “Project”")
    @Tag("PositiveTest")
    @Test
    public void test08_editLatestProject() {
        LoginPage.login();
        TasksPage.clickProject_button();
        ProjectsPage.addProject();
        ProjectsPage.clickLatestEditProject_button();
        ProjectsPage.inputProjectName_field("Hello1");
        ProjectsPage.inputProjectDescription_field("Hello1");
        ProjectsPage.clickSave_button();
        String actualProjectTitle = $x(ProjectsPage.getProject_block() + "/h3").getText();
        String actualProjectDescription = $x(ProjectsPage.getProject_block() + "/p").getText();
        assertAll( /* If single assertion fails within the assertAll block, the subsequent assertions will still be executed. */
                () -> assertEquals("Hello1", actualProjectTitle),
                () -> assertEquals("Hello1", actualProjectDescription)
        );
    }

    @DisplayName("09. Verify that the user can delete existing “Project”")
    @Tag("PositiveTest")
    @Test
    public void test09_deleteLatestProject() {
        LoginPage.login();
        TasksPage.clickProject_button();
        ProjectsPage.addProject();
        ProjectsPage.clickLatestDeleteProject_button();
        ProjectsPage.clickConfirm_button();
        $x(ProjectsPage.getProject_block()).shouldNotBe(exist);
    }

    @DisplayName("10. Verify that the user can add “Task” to existing “Project”")
    @Tag("PositiveTest")
    @Test
    public void test10_addTaskToLatestProject() {
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
        $x(TasksPage.getTask_block()).shouldBe(visible);
    }

    @DisplayName("11. Verify that the user can delete existing “Task”")
    @Tag("PositiveTest")
    @Test
    public void test11_deleteLatestTask() {
        LoginPage.login();
        TasksPage.clickProject_button();
        ProjectsPage.addProject();
        ProjectsPage.clickTask_button();
        TasksPage.addTaskToLatestProject();
        TasksPage.clickLatestDeleteTask_button();
        TasksPage.clickConfirm_button();
        $x(TasksPage.getTask_block()).shouldNotBe(exist);
    }

    @DisplayName("12. Verify that the user can make changes to existing “Task”")
    @Tag("PositiveTest")
    @Test
    public void test12_editLatestTask() {
        LoginPage.login();
        TasksPage.clickProject_button();
        ProjectsPage.addProject();
        ProjectsPage.clickTask_button();
        TasksPage.addTaskToLatestProject();
        TasksPage.clickLatestEditTask_button();
        TasksPage.inputTaskTitle_field("Qwerty1");
        TasksPage.inputDueDate_field("23022020");
        TasksPage.inputDescription_textarea("Qwerty");
        TasksPage.clickSave_button();
        String actualTaskTitle = $x(TasksPage.getTask_block() + "/h3").getText().split("\n")[0];
        String actualTaskDate = $x(TasksPage.getTask_block() + "/p[1]").getText().replaceAll("(\\D+)", "");
        String actualTaskDescription = $x(TasksPage.getTask_block() + "/p[2]").getText();
        assertAll( /* If single assertion fails within the assertAll block, the subsequent assertions will still be executed. */
                () -> assertEquals("Qwerty1", actualTaskTitle),
                () -> assertEquals("23022020", actualTaskDate),
                () -> assertEquals("Qwerty", actualTaskDescription)
        );
    }

    @DisplayName("13. Verify that the user can mark existing “Task” as done")
    @Tag("PositiveTest")
    @Test
    public void test13_markLatestTaskAsDone() {
        LoginPage.login();
        TasksPage.clickProject_button();
        ProjectsPage.addProject();
        ProjectsPage.clickTask_button();
        TasksPage.addTaskToLatestProject();
        TasksPage.clickLatestCheckmark_button();
        String attribute = $x(TasksPage.getTask_block()).getAttribute("class");
        String classTag = "bg-base-100 shadow-xl border-secondary border-solid border-2 rounded-md text-left p-3 mb-3";
        assertEquals(attribute, classTag + " line-through", "Task wasn't crossed out");
    }

    @DisplayName("14. Verify that the user can filter “Tasks” by “Projects”")
    @Tag("PositiveTest")
    @Test
    public void test14_filterTasksByProjects() {
        LoginPage.login();
        TasksPage.clickProject_button();
        ProjectsPage.addProject();
        ProjectsPage.addProject();
        ProjectsPage.addProject();
        ProjectsPage.clickTask_button();
        TasksPage.addTasksToProjects(1);
        TasksPage.addTasksToProjects(2);
        TasksPage.addTasksToProjects(3);
        assertEquals(3, $$x(TasksPage.getTask_block()).size());
        TasksPage.clickFilterProject_button(1);
        assertEquals(1, $$x(TasksPage.getTask_block()).size());
        TasksPage.clickFilterProject_button(2);
        assertEquals(1, $$x(TasksPage.getTask_block()).size());
        TasksPage.clickFilterProject_button(3);
        assertEquals(1, $$x(TasksPage.getTask_block()).size());
    }
}