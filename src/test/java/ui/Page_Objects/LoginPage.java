package ui.Page_Objects;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import readProperties.ConfigProvider;
import ui.ListProjects_GET_Response;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$x;
import static io.restassured.RestAssured.given;

public class LoginPage {

    /* Elements present on WelcomePage */
    private final String getstarted_button = "//*[text()=\"Get Started\"]";

    /* Elements present on LoginPage */
    private final String email_field = "//*[@placeholder=\"example@gmail.com\"]";
    private final String password_field = "//*[@placeholder=\"Input password here\"]";
    private final String login_button = "//*[text()=\"Login\"]";
    private final String signup_link = "//*[text()=\"Sign up here\"]";

    /* Actions performed on identified elements */
    public void clickGetstarted_button() {
        $x(getstarted_button).click();
    }
    public void enterEmail(String email) {
        $x(email_field).sendKeys(email);
    }
    public void enterPassword(String password) {
        $x(password_field).sendKeys(password);
    }
    public void clickLoginButton() {
        $x(login_button).click();
    }

    public void login() {
        var jsonResponseAsMap = getJsonResponseAsMap();
        String jwtAccessToken = jsonResponseAsMap.get("access token");

        emptyAppBeforeTests(jwtAccessToken);

        Selenide.open(ConfigProvider.URL);

        setJwtToLocalStorage(jsonResponseAsMap);

        Selenide.open(ConfigProvider.URL + "#/tasks");
    }

    public static void setJwtToLocalStorage(Map<String, String> jsonResponseAsMap) {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonPayload = null;
        try {
            jsonPayload = objectMapper.writeValueAsString(jsonResponseAsMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Selenide.executeJavaScript("localStorage.setItem('loggedAppUser', '"+ jsonPayload +"');");
    }

    /* Preconditions for tests */
    public static Map<String, String> getJsonResponseAsMap() {
        return given()
                .baseUri(ConfigProvider.URL) /* set the base URI only for a single request. */
                .header("Content-Type", "application/json")
                .body("{\"email\":\"" +
                        ConfigProvider.DEMO_EMAIL +
                        "\",\"password\":\"" +
                        ConfigProvider.DEMO_PASSWORD +
                        "\"}")
                .when()
                .post("api/auth/login")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().body().jsonPath().getMap(""); /* Methods to access values using json path of specific "key": getMap, getInt, getBoolean... */
    }

    public static void deleteSingleProject(Integer projectID, String jwtAccessToken) {
        given()
                .baseUri(ConfigProvider.URL)
                .header("Authorization", "Bearer " + jwtAccessToken)
                .when()
                .delete("api/projects/" + projectID)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }
    public static void emptyAppBeforeTests(String jwtAccessToken) {
        java.util.List<ListProjects_GET_Response> projects =
                given()
                        .baseUri(ConfigProvider.URL)
                        .header("Authorization","Bearer " + jwtAccessToken)
                        .when()
                        .get("api/projects")
                        .then()
                        .statusCode(200)
                        .contentType(ContentType.JSON)
                        .extract().body().jsonPath().getList("data", ListProjects_GET_Response.class);

        List<Integer> projectIDs = projects.stream().map(x -> x.getId()).collect(Collectors.toList());
        projectIDs.stream().forEach(x -> deleteSingleProject(x, jwtAccessToken));
    }
}