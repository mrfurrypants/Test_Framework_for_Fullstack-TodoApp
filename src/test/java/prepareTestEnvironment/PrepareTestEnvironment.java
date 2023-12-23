package prepareTestEnvironment;

import com.codeborne.selenide.WebDriverRunner;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;

import io.restassured.response.Response;
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

    public static Response getResponse(int attempt) {
        final int MAX_ATTEMPTS = 4;
        if (attempt > MAX_ATTEMPTS) {
            // Throw an exception or return an error response
            throw new RuntimeException("Maximum number of attempts exceeded, choose other credentials.");
        }
        Response response = given()
                .baseUri(ConfigProvider.URL)
                .header("Content-Type", "application/json")
                .body("{\"email\": \""+ ConfigProvider.VALID_EMAIL +"\", \"password\": \""+ ConfigProvider.VALID_PASSWORD +"\"}")
                .when()
                .post("api/auth/login")
                .then()
//                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().response();

        int statusCode = response.getStatusCode();
        if(statusCode == 200) {
            return response;
        } else {
            System.out.println("User under credentials from .conf is not registered yet. Registration...");
            registerNewUser();
            return getResponse(attempt + 1);
        }
    }

    public static void registerNewUser() {
        given()
                .baseUri(ConfigProvider.URL)
                .header("Content-Type", "application/json")
                .body("{\"name\": \""+ ConfigProvider.USER_NAME +"\", \"email\": \""+ ConfigProvider.VALID_EMAIL +"\", \"password\": \""+ ConfigProvider.VALID_PASSWORD +"\"}")
                .when()
                .post("api/auth/register")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }

    public static Map<String,String> getJsonResponseAsMap() {
        return getResponse(1).jsonPath().getMap("");
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
