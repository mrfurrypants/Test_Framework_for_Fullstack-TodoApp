package api;
import api.domain.ListUsers_GET_Response; /* There is need to import it explicitly, as Class is located in another package. */
import org.junit.jupiter.api.Test;

import java.util.List;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
public class ReqresTests {
    private final static String _URL = "https://reqres.in";
    @Test
    public void test01_checkAvatarAndId() {
        /* Applicable in @BeforeAll method as well. */
        Specifications.installSpecification(Specifications.requestSpecs(_URL),Specifications.responseSpecs200());
        /*
        The MOST modular approach with a bit more setup compared to:
        • RequestSpecification requestSpec = new RequestSpecBuilder().setBaseUri("http://localhost:80/").build();
          +
          given(). ... .spec(requestSpec)... - returns a new RequestSpecification instance including predefined specifications and preceding supplemented specifications.
          Supplemented specifications following .spec() can override predefined specifications.
        or
        • RestAssured.baseUri = "http://localhost:80/"; - initialization of RestAssured STATIC field, scope - "test class" (applied once in single test method for whole test class)
          +
          given()...
        */

        List<ListUsers_GET_Response> users =
                /* GWT syntax [Given (state of the system), When (performed action) and Then (expected outcome)] is common in BDD. */
                given()/* Returns RequestSpecification instance. */
                        /* Set up the request headers and body. */
                        .when() /* Separate the request setup from the actual action like .get, .post... */
                        /* Set up the request line */
                        .get("/api/users?page=2")
                        .then()
                        /* Assert HTTP status line; deserialize and assert response body */
                        .log().all()/* Invoking RIGHT after given(), when(), then() - useful tool for debugging to see exactly what is being sent in the HTTP request*/
                        .extract().body().jsonPath()
                        .getList("data", ListUsers_GET_Response.class);
        /* Deserialize JSON payload to POJO class. "data" or "." - Groovy's Gpath to key, which value is array of objects. */
        /* Groovy's Gpath "." for JSON like: [{"name": "John", "age": 25}, {"name": "Alice", "age": 23}] or {"name": "John", "age": 25}. */
        users.forEach(listElement -> assertTrue(listElement.getAvatar().contains(listElement.getId().toString())));
    }
}
