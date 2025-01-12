package api;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class ApiPartialUpdateBookingTest {

    @Before
    public void setup() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
    }

    @Test
    public void testPartialUpdateBooking() {
        String requestBody = "{\"firstname\": \"James\", \"lastname\": \"Brown\"}";

        given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .body(requestBody)
                .when()
                .patch("/booking/1")
                .then()
                .statusCode(200)
                .body("firstname", equalTo("James"))
                .body("lastname", equalTo("Brown"));
    }
}