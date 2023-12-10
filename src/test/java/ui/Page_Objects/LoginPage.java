package ui.Page_Objects;

import com.codeborne.selenide.Selenide;
import readProperties.ConfigProvider;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.$x;
import static io.restassured.RestAssured.given;
import prepareTestEnvironment.PrepareTestEnvironment;

public class LoginPage {

    /* Elements present on WelcomePage */
    private static final String getstarted_button = "//*[text()=\"Get Started\"]";

    /* Elements present on LoginPage */
    private static final String email_field = "//*[@placeholder=\"example@gmail.com\"]";
    private static final String password_field = "//*[@placeholder=\"Input password here\"]";
    private static final String login_button = "//*[text()=\"Login\"]";
    private static final String signup_link = "//*[text()=\"Sign up here\"]";

    /* Actions performed on identified elements */
    public static void clickGetstarted_button() {
        $x(getstarted_button).click();
    }
    public static void enterEmail(String email) {
        $x(email_field).sendKeys(email);
    }
    public static void enterPassword(String password) {
        $x(password_field).sendKeys(password);
    }
    public static void clickLoginButton() {
        $x(login_button).click();
    }

    public static void login() {
        var jsonResponseAsMap = PrepareTestEnvironment.getJsonResponseAsMap();
        String jwtAccessToken = jsonResponseAsMap.get("access token");

        PrepareTestEnvironment.emptyAppBeforeTests(jwtAccessToken);

        Selenide.open(ConfigProvider.URL);

        PrepareTestEnvironment.setJwtToLocalStorage(jsonResponseAsMap);

        Selenide.open(ConfigProvider.URL + "#/tasks");
        Selenide.refresh();
    }
    public static void delay() {
        Random rand = new Random();
        int delayInSeconds = rand.nextInt(2)/* From 0 to 2, excl. 3 */ + 1; // This will generate a random number between 2 and 4.
        try {
            TimeUnit.SECONDS.sleep(delayInSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}