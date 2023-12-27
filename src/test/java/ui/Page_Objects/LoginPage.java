package ui.Page_Objects;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import readProperties.ConfigProvider;
import static com.codeborne.selenide.Selenide.$x;
import prepareTestEnvironment.PrepareTestEnvironment;

import java.util.Map;

public class LoginPage {

    /* Elements present on WelcomePage */
    private static final SelenideElement getstarted_button = $x("//*[text()='Get Started']");

    /* Elements present on LoginPage */
    private static final SelenideElement email_field = $x("//*[@placeholder='example@gmail.com']");
    private static final SelenideElement password_field = $x("//*[@placeholder='Input password here']");
    private static final SelenideElement login_button = $x("//*[text()='Login']");
    private static final SelenideElement signup_link = $x("//*[text()='Sign up here']");
    private static final SelenideElement validation_popup = $x("//span[text()='Email or password is invalid']");

    public static SelenideElement getValidation_popup() {
        return validation_popup;
    }

    /* Actions performed on identified elements */
    public static void clickGetstarted_button() {
        getstarted_button.click();
    }
    public static void enterEmail(String email) {
        email_field.sendKeys(email);
    }
    public static void enterPassword(String password) {
        password_field.sendKeys(password);
    }
    public static void clickLogin_button() {
        login_button.click();
    }
    public static void clickSignup_button() {
        signup_link.click();
    }


    public static void login() {
        Map<String,String> jsonResponseAsMap = PrepareTestEnvironment.getJsonResponseAsMap();
        String jwtAccessToken = jsonResponseAsMap.get("access token");

        PrepareTestEnvironment.emptyAppBeforeTests(jwtAccessToken);

        Selenide.open(ConfigProvider.URL_UI);

        PrepareTestEnvironment.setJwtToLocalStorage(jsonResponseAsMap);

        Selenide.open(ConfigProvider.URL_UI + "#/tasks");
        Selenide.refresh();
    }
}