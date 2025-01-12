package api;

import io.restassured.RestAssured;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ApiPingHealthCheckTest {


    @Test
    public void testPingHealthCheck() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";

        given()
                .when()
                .get("/ping")
                .then()
                .statusCode(201);
    }
}
