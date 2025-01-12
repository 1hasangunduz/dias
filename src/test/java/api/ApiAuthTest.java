package api;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class ApiAuthTest {
    @Before
    public void setup() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
    }

    @Test
    public void testCreateToken() {
        String requestBody = "{\"username\": \"admin\", \"password\": \"password123\"}";

        given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/auth")
                .then()
                .statusCode(200)
                .body("token", notNullValue());
    }
}
