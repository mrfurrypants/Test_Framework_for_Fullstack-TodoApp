package ui;

import readProperties.ConfigProvider;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import java.awt.*;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import static io.restassured.RestAssured.given;

abstract public class SelenideAbstractClass { /* This class is to be only inherited and not instantiated! */
    protected static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        /* By default Selenide launches the firefox browser. */
        /* Selenide automatically opens the browser for each test and closes it after the test is done. */
        /* No need in: @BeforeEach public void setUpEachTest() {driver = new ChromeDriver();} */
        Configuration.browserSize = screenSize.width + "x" + screenSize.height;
        Configuration.headless = false;
    }
    @BeforeEach
    public void setUpEachTest() {
        deleteAllProjects();
    }
    @AfterEach
    public void tearDown() {
        Selenide.closeWebDriver();
    }
    /* Helpers */
    public static void delay() {
        Random rand = new Random();
        int delayInSeconds = rand.nextInt(2)/* From 0 to 2, excl. 3 */ + 1; // This will generate a random number between 2 and 4.
        try {
            TimeUnit.SECONDS.sleep(delayInSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /* Preconditions for tests */
    public static String getJwtAccessToken() {
        return given()
                .baseUri(ConfigProvider.URL) /* set the base URI only for a single request. */
                .header("Content-Type", "application/json")
                .body("{\"email\":\"" +
                        ConfigProvider.DEMO_LOGIN +
                        "\",\"password\":\"" +
                        ConfigProvider.DEMO_PASSWORD +
                        "\"}")
                .when()
                .post("api/auth/login")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().body().jsonPath().getString("\"access token\""); /* Methods to access values using json path of specific "key": getInt, getBoolean... */
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
    public static void deleteAllProjects() {
        String jwtAccessToken = getJwtAccessToken();
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