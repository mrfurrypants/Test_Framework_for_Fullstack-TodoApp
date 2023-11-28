package api;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specifications {
    /* "Specifications" class static methods set up a common template (shared setups) for different requests and responses,
    additional test-specific setups are specified within respective test methods. */
    public static RequestSpecification requestSpecs(String url) {
        return new RequestSpecBuilder()
                /* ".set..." methods are used to configure all requests in single test method, not the individual requests. */
                .setBaseUri(url)
                .setContentType(ContentType.JSON)
                .build();
    }
    public static ResponseSpecification responseSpecs200() {
        return new ResponseSpecBuilder()
                /* ".expect..." methods are used to configure expectations (assertions) of all requests in single test method, not the individual requests. */
                .expectStatusCode(200)
                .build();
    }
    public static ResponseSpecification responseSpecs(int expectedStatusCode) {
        return new ResponseSpecBuilder()
                .expectStatusCode(expectedStatusCode)/* Assertion */
                .build();
    }
    public static void installSpecification(RequestSpecification request, ResponseSpecification response) {
        RestAssured.requestSpecification = request;
        RestAssured.responseSpecification = response;
    }
}
