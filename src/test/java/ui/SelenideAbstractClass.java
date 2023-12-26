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
import org.openqa.selenium.remote.DesiredCapabilities;


abstract public class SelenideAbstractClass {
    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    @BeforeAll
    public static void setUp() {
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

        Configuration.browserSize = screenSize.width + "x" + screenSize.height;
        Configuration.headless = false;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
    }
    @BeforeEach
    public void setUpEachTest() {

    }
    @AfterEach
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}