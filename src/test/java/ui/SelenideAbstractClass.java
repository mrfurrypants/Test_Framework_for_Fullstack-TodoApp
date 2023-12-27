package ui;

import com.codeborne.selenide.*;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import com.codeborne.selenide.logevents.SelenideLogger;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Owner;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.RemoteWebDriver;
import readProperties.ConfigProvider;
import java.awt.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@ExtendWith(ScreenShooterExtension.class)
abstract public class SelenideAbstractClass {
//    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    @BeforeAll
    public static void setUp() {
//        WebDriverManager.chromedriver().setup();
//        Configuration.headless = true;

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

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
    }
    @BeforeEach
    public void setUpEachTest() {

    }
    @AfterEach
    public void tearDown() {
        Selenide.closeWebDriver();
    }

    public static void delay() {
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}