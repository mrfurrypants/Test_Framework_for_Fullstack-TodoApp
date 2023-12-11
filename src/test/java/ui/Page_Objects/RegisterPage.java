package ui.Page_Objects;

import static com.codeborne.selenide.Selenide.$x;

public class RegisterPage {
    /* Elements present on RegisterPage */
    private static final String name_field = "//*[@placeholder='Input your name']";
    private static final String email_field = "//*[@placeholder='Input your e-mail']";
    private static final String password_field = "//*[@placeholder='Input your password']";
    private static final String retypePassword_field = "//*[@placeholder='Retype your password']";
    private static final String signup_button = "//*[text()='Sign up']";
    private static final String regCompleted_popup =  "//span[text()='Registration user is completed']";
    private static final String regNotCompleted_popup = "//span[text()='Email admin@gmail.com is already registered.']";
    public static String getRegCompleted_popup() {
        return regCompleted_popup;
    }
    public static String getRegNotCompleted_popup() {
        return regNotCompleted_popup;
    }

    /* Actions performed on identified elements */
    public static void enterName(String name) {
        $x(name_field).sendKeys(name);
    }
    public static void enterEmail(String email) {
        $x(email_field).sendKeys(email);
    }
    public static void enterPassword(String password) {
        $x(password_field).sendKeys(password);
    }
    public static void retypePassword(String password) {
        $x(retypePassword_field).sendKeys(password);
    }
    public static void clickSignup_button() {
        $x(signup_button).click();
    }
}
