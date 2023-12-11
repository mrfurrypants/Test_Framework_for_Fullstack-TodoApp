package ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Owner;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import readProperties.ConfigProvider;

import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

abstract public class SelenideAbstractClass { /* This class is to be only inherited and not instantiated! */
    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        /* By default Selenide launches the firefox browser. */
        /* No need in: @BeforeEach public void setUpEachTest() {driver = new ChromeDriver();} */
        Configuration.browserSize = screenSize.width + "x" + screenSize.height;
        Configuration.headless = false;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
    }
    @BeforeEach
    public void setUpEachTest() {

    }
    @AfterEach
    public void tearDown() {
        Selenide.closeWebDriver();/* To explicitly close the WebDriver instance. */
        /* Selenide by default creates the WebDriver instance for each test and closes only browser window after the test is done. */
    }
}