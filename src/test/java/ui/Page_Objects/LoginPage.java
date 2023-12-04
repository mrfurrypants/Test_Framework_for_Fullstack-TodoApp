package ui.Page_Objects;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import readProperties.ConfigProvider;

import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {
    public LoginPage() {
        Selenide.open(ConfigProvider.URL);
        SelenideElement el = $x(getstarted_button);
        el.click();
    }

    /* Elements present on WelcomePage */
    private final String getstarted_button = "//*[text()=\"Get Started\"]";

    /* Elements present on LoginPage */
    private final String email_field = "//*[@placeholder=\"example@gmail.com\"]";
    private final String password_field = "//*[@placeholder=\"Input password here\"]";
    private final String login_button = "//*[text()=\"Login\"]";
    private final String signup_link = "//*[text()=\"Sign up here\"]";

    /* Actions performed on identified elements */
    public void enterEmail(String email) {
        $x(email_field).sendKeys(email);
    }
    public void enterPassword(String password) {
        $x(password_field).sendKeys(password);
    }
    public void clickLoginButton() {
        $x(login_button).click();
    }
    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
    }
}