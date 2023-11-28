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
    String getstarted_button = "//*[text()=\"Get Started\"]";

    /* Elements present on LoginPage */
    String email_field = "//*[@placeholder=\"example@gmail.com\"]";
    String password_field = "//*[@placeholder=\"Input password here\"]";
    String login_button = "//*[text()=\"Login\"]";
    String signup_link = "//*[text()=\"Sign up here\"]";

    /* Actions performed on identified elements */
    public void enterEmail() {
        $x(email_field).sendKeys(ConfigProvider.DEMO_LOGIN);
    }
    public void enterPassword() {
        $x(password_field).sendKeys(ConfigProvider.DEMO_PASSWORD);
    }
    public void clickLoginButton() {
        $x(login_button).click();
    }
    public void login() {
        enterEmail();
        enterPassword();
        clickLoginButton();
    }
}