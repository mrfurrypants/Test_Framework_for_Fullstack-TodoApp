package ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import prepareTestEnvironment.PrepareTestEnvironment;
import readDataFromXLSX.tasks_providerPOJO1;
import readProperties.ConfigProvider;
import ui.Page_Objects.LoginPage;
import ui.Page_Objects.ProjectsPage;
import ui.Page_Objects.TasksPage;

import java.awt.*;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.$x;
import static readDataFromXLSX.XLSX_to_POJOs.xlsxDataToListOfPOJOs;

@ExtendWith(ScreenShooterExtension.class)
public class DataDrivenTests {
    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static boolean executed = false;
    @BeforeEach
    public void setUpEachParametrizedTestInvocation() {
        var jsonResponseAsMap = PrepareTestEnvironment.getJsonResponseAsMap();
        String jwtAccessToken = jsonResponseAsMap.get("access token");

        if (!executed) {
            PrepareTestEnvironment.emptyAppBeforeTests(jwtAccessToken);
        }

        Selenide.open(ConfigProvider.URL);

        PrepareTestEnvironment.setJwtToLocalStorage(jsonResponseAsMap);

        Selenide.open(ConfigProvider.URL + "#/tasks");
        Selenide.refresh();
    }
    @AfterEach
    public void tearDown() {
        Selenide.closeWebDriver();
    }
    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        Configuration.browserSize = screenSize.width + "x" + screenSize.height;
        Configuration.headless = false;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
    }
    public static Stream<tasks_providerPOJO1> streamForParameterizedTest() {
        return xlsxDataToListOfPOJOs("tasks_provider.xlsx", tasks_providerPOJO1.class).stream();
    }
    @ParameterizedTest(name = "Invocation N°{index}")
    @MethodSource("streamForParameterizedTest")
    @Tag("DataDrivenTest")
    @DisplayName("Simple Excel Test")
    public void exceltest(tasks_providerPOJO1 row){
        TasksPage.clickProject_button();

        if (!executed) {
            ProjectsPage.addProject();
            executed = true;
        }

        ProjectsPage.clickTask_button();

        TasksPage.clickNewTask_button();
        TasksPage.inputTaskTitle_field(row.getTask_title());
        TasksPage.inputDueDate_field(row.getDue_date());
        TasksPage.selectLatestProjectFrom_dropdown();
        TasksPage.inputDescription_textarea(row.getDescription());
        TasksPage.clickSave_button();
    }

}
