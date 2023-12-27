package ui.BDDsteps;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.remote.DesiredCapabilities;
import readProperties.ConfigProvider;
import ui.Page_Objects.LoginPage;
import ui.Page_Objects.ProjectsPage;
import ui.Page_Objects.TasksPage;
import java.awt.*;
import java.util.HashMap;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;

public class MyStepdefs {
//    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    /*---------------------Global Cucumber set-ups and tear-downs.---------------------*/
    @AfterStep
    public void makeScreenshot() {
        Selenide.screenshot(System.currentTimeMillis() + " step");
    }
    @After
    public void tearDown() {
        Selenide.closeWebDriver();
    }
    @BeforeAll
    public static void setUpOnceBeforeAllScenarios() {
//        WebDriverManager.chromedriver().setup();

        if(ConfigProvider.BROWSER.equalsIgnoreCase("chrome")) {
            Configuration.browser = "chrome";
        }
        else if(ConfigProvider.BROWSER.equalsIgnoreCase("firefox")) {
            Configuration.browser = "firefox";
        }
        else if(ConfigProvider.BROWSER.equalsIgnoreCase("edge")) {
            Configuration.browser = "edge";
        }

        if(ConfigProvider.EXECUTION_MODE.equalsIgnoreCase("remote")) {
            Configuration.browserCapabilities = new DesiredCapabilities();
            Configuration.browserCapabilities.setCapability("selenoid:options", new HashMap<String, Object>() {{
                put("browserVersion", "120.0");
                put("sessionTimeout", "15m");
                put("enableVNC", true);
                put("enableVideo", false);
                put("screenResolution", "1920x1080");
                put("browserSize", "1920x1080");
                put("startMaximized", true);
            }});
            Configuration.remote = "http://localhost:4445/wd/hub";
        }
    }
    /*---------------------Steps before each scenario.---------------------*/
    @Given("The user navigated to the 'Main' page")
    public void theUserNavigatesToThePage() {
        Selenide.open(ConfigProvider.URL_UI);
    }

    @When("The user clicks 'Get started' button")
    public void theUserClicksGetStartedButton() {
        LoginPage.clickGetstarted_button();
    }

    /* Scenarios steps */
    @When("The user enters a valid email and password")
    public void theUserEntersAValidEmailAndPassword() {
        LoginPage.enterEmail(ConfigProvider.VALID_EMAIL);
        LoginPage.enterPassword(ConfigProvider.VALID_PASSWORD);
    }
    @And("The user clicks the 'Login' button")
    public void theUserClicksTheLoginButton() {
        LoginPage.clickLogin_button();
    }
    @And("The user clicks the 'Project' button")
    public void theUserClicksTheProjectButton() {
        TasksPage.clickProject_button();
    }
    @And("The user enters Project Name")
    public void theUserEntersProjectName() {
        ProjectsPage.inputProjectName_field();
    }
    @Then("The user should see the created project")
    public void theUserShouldSeeTheCreatedProject() {
        ProjectsPage.getProject_block().shouldBe(visible);
    }
    @And("The user clicks 'Save' button")
    public void theUserClicksSaveButton() {
        ProjectsPage.clickSave_button();
    }
    @And("The user enters Project Description")
    public void theUserEntersProjectDescription() {
        ProjectsPage.inputProjectDescription_field();
    }
    @When("The user clicks 'New Project' button")
    public void theUserClicksNewProjectButton() {
        ProjectsPage.clickNewProject_button();
    }
    @And("The user clicks 'Delete' button and confirms deletion")
    public void theUserClicksDeleteButton() {
        ProjectsPage.clickLatestDeleteProject_button();
        ProjectsPage.clickConfirm_button();
    }
    @Then("The user should be redirected to the 'Tasks' page")
    public void theUserShouldBeRedirectedToThePage() {
        TasksPage.getLogout_button().shouldBe(visible);
    }
    @When("The user enters an invalid email {string} and password {string}")
    public void theUserEntersAnInvalidEmailAndPassword(String email, String password) {
        LoginPage.enterEmail(email);
        LoginPage.enterPassword(password);
    }
    @Then("The user should see the project removed")
    public void theUserShouldSeeTheProjectRemoved() {
        ProjectsPage.getProject_block().shouldNotBe(exist);
    }
    @Then("The user should see the pop-up validation message")
    public void theUserShouldSeeThePopUpValidationMessage() {
        LoginPage.getValidation_popup().shouldBe(visible);
    }
    @Given("The user is logged in and on the 'Projects' page")
    public void theUserIsLoggedInAndOnTheProjectsPage() {
        LoginPage.login();
        TasksPage.clickProject_button();
    }
}
