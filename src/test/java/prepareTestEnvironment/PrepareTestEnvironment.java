package prepareTestEnvironment;

import com.codeborne.selenide.WebDriverRunner;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;

import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import readProperties.ConfigProvider;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class PrepareTestEnvironment {
    public static void setJwtToLocalStorage(Map<String,String> jsonResponseAsMap) {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonPayload = null;
        try {
            jsonPayload = objectMapper.writeValueAsString(jsonResponseAsMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        LocalStorage localStorage = ((WebStorage) WebDriverRunner.getWebDriver()).getLocalStorage();
        localStorage.setItem("loggedAppUser", jsonPayload);
    }

    public static Map<String,String> getJsonResponseAsMap() {
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
        java.util.List<ListProjects_GET_ResponsePOJO> projects =
                given()
                        .baseUri(ConfigProvider.URL)
                        .header("Authorization","Bearer " + jwtAccessToken)
                        .when()
                        .get("api/projects")
                        .then()
                        .statusCode(200)
                        .contentType(ContentType.JSON)
                        .extract().body().jsonPath().getList("data", ListProjects_GET_ResponsePOJO.class);

        List<Integer> projectIDs = projects.stream().map(x -> x.getId()).collect(Collectors.toList());
        projectIDs.stream().forEach(x -> deleteSingleProject(x, jwtAccessToken));
    }
}
