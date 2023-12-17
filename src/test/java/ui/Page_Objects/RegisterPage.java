package ui.Page_Objects;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;

public class RegisterPage {
    /* Elements present on RegisterPage */
    private static final SelenideElement name_field = $x("//*[@placeholder='Input your name']");
    private static final SelenideElement email_field = $x("//*[@placeholder='Input your e-mail']");
    private static final SelenideElement password_field = $x("//*[@placeholder='Input your password']");
    private static final SelenideElement retypePassword_field = $x("//*[@placeholder='Retype your password']");
    private static final SelenideElement signup_button = $x("//*[text()='Sign up']");
    private static final SelenideElement regCompleted_popup = $x("//span[text()='Registration user is completed']");
    private static final SelenideElement regNotCompleted_popup = $x("//span[text()='Email admin@gmail.com is already registered.']");
    public static SelenideElement getRegCompleted_popup() {
        return regCompleted_popup;
    }
    public static SelenideElement getRegNotCompleted_popup() {
        return regNotCompleted_popup;
    }

    /* Actions performed on identified elements */
    public static void enterName(String name) {
        name_field.sendKeys(name);
    }
    public static void enterEmail(String email) {
        email_field.sendKeys(email);
    }
    public static void enterPassword(String password) {
        password_field.sendKeys(password);
    }
    public static void retypePassword(String password) {
        retypePassword_field.sendKeys(password);
    }
    public static void clickSignup_button() {
        signup_button.click();
    }
}
